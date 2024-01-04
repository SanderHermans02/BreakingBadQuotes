package com.example.breakingbadquotes.ui.quoteScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.breakingbadquotes.QuoteApplication
import com.example.breakingbadquotes.data.QuoteRepository
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.ui.states.QuoteApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel responsible for managing quote data for the UI layer.
 * It handles the retrieval of quotes, maintaining a list of favorite quotes, and updating the UI state.
 *
 * @property quoteRepository The repository that this ViewModel will use to perform data operations.
 */
class QuoteViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    /**
     * Represents the current state of the quote API call.
     * UI components can observe this state and change their state accordingly.
     */
    var quoteApiState: QuoteApiState by mutableStateOf(QuoteApiState.Loading)
        private set

    private val _favoriteQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val favoriteQuotes: StateFlow<List<Quote>> = _favoriteQuotes.asStateFlow()
    init {
        loadFavoriteQuotes()
        getQuote()
    }

    /**
     * Loads favorite quotes from the repository and updates the UI state.
     */
    private fun loadFavoriteQuotes() {
        viewModelScope.launch {
            quoteRepository.getFavoriteQuotes().collect { quotes ->
                _favoriteQuotes.value = quotes
            }
        }
    }

    /**
     * Adds a quote to the list of favorites.
     *
     * @param quote The quote to be added to favorites.
     */
    fun addFavorite(quote: Quote) {
        viewModelScope.launch {
            try {
                quoteRepository.insertQuote(quote)
                _favoriteQuotes.value = _favoriteQuotes.value + quote.copy(isFavorite = true)
            } catch (ex: Exception) {
                Log.e("QuoteViewModel", ex.message.toString())
            }
        }
    }

    /**
     * Removes a quote from the list of favorites.
     *
     * @param quote The quote to be removed from favorites.
     */
    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            quoteRepository.deleteQuote(quote)
            _favoriteQuotes.value = _favoriteQuotes.value.filter { it.quote != quote.quote }
        }
    }

    /**
     * Fetches a random quote and updates the UI state.
     */
    fun getQuote() {
        viewModelScope.launch {
            quoteApiState = try {
                val quotes = quoteRepository.getQuote()
                if (quotes.isNotEmpty()) {
                    QuoteApiState.Success(quotes.first())
                } else {
                    QuoteApiState.Error
                }
            } catch (ex: IOException) {
                QuoteApiState.NoInternet
            } catch (e: Exception) {
                Log.e("QuoteViewModel", e.message.toString())
                QuoteApiState.Error
            }
        }
    }

    companion object {
        // Ensures a single instance of QuoteViewModel is used throughout the app
        private var Instance: QuoteViewModel? = null

        /**
         * Factory for creating instances of [QuoteViewModel].
         * Ensures that [QuoteViewModel] is a singleton by reusing the existing instance.
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[APPLICATION_KEY] as QuoteApplication)
                    val quoteRepository = application.container.quoteRepository
                    Instance = QuoteViewModel(quoteRepository = quoteRepository)
                }
                Instance!!
            }
        }
    }
}
