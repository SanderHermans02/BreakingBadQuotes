package com.example.breakingbadquotes.ui.favoritesScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.breakingbadquotes.QuoteApplication
import com.example.breakingbadquotes.data.QuoteRepository
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.ui.states.QuoteDbState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the state and data for the favorites screen.
 * It handles the interactions related to the favorite quotes such as fetching from
 * and removing quotes from the repository.
 *
 * @property quoteRepository The repository through which favorite quotes operations are performed.
 */
class FavoritesViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    /**
     * Represents the current state of the favorite quotes database operations.
     * UI components can observe this state to update their presentations accordingly.
     */
    var quoteDbState: QuoteDbState by mutableStateOf(QuoteDbState.Loading)
        private set

    private val _favoriteQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val favoriteQuotes: StateFlow<List<Quote>> = _favoriteQuotes.asStateFlow()

    init {
        // Fetch favorite quotes on initialization
        viewModelScope.launch {
            try {
                quoteRepository.getFavoriteQuotes().collect { quotes ->
                    _favoriteQuotes.value = quotes
                    quoteDbState = QuoteDbState.Success(quotes)
                }
            } catch (e: Exception) {
                quoteDbState = QuoteDbState.Error
                Log.e("FavoritesViewModel", "Error fetching favorite quotes", e)
            }
        }
    }

    /**
     * Removes a quote from the list of favorite quotes.
     *
     * @param quote The quote to be removed from favorites.
     */
    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            quoteRepository.deleteQuote(quote)
        }
    }

    companion object {
        // Ensures a single instance of FavoritesViewModel is used throughout the app
        private var Instance: FavoritesViewModel? = null

        /**
         * Factory for creating instances of [FavoritesViewModel].
         * This ensures that [FavoritesViewModel] reuses the existing instance if available.
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuoteApplication)
                    val quoteRepository = application.container.quoteRepository
                    Instance = FavoritesViewModel(quoteRepository = quoteRepository)
                }
                Instance!!
            }
        }
    }
}
