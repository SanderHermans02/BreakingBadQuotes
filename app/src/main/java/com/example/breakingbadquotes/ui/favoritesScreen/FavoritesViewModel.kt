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

class FavoritesViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    var quoteDbState: QuoteDbState by mutableStateOf(QuoteDbState.Loading)
        private set

    private val _favoriteQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val favoriteQuotes: StateFlow<List<Quote>> = _favoriteQuotes.asStateFlow()

    init {
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
        /* Log.i("vm inspection", "FavoritesViewModel init")*/
    }
    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            quoteRepository.deleteQuote(quote)
        }
    }

    companion object {
        private var Instance: FavoritesViewModel? = null
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
