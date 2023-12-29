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
import com.example.breakingbadquotes.ui.states.QuoteApiState
import com.example.breakingbadquotes.ui.states.QuoteDbState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    lateinit var uiListState: StateFlow<List<Quote>>
    var quoteApiState: QuoteApiState by mutableStateOf(QuoteApiState.Loading)
        private set

    var quoteDbState: QuoteDbState by mutableStateOf(QuoteDbState.Loading)
        private set
    var listOfQuotes: List<Quote> by mutableStateOf(mutableListOf())
        private set

    init {
        getFavoritesQuotes()
        Log.i("vm inspection", "FavoritesViewModel init")
    }
    fun getFavoritesQuotes() {
        viewModelScope.launch {
            quoteDbState = try {
                uiListState = quoteRepository.getFavoriteQuotes()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                QuoteDbState.Success(listOfQuotes)
            } catch (e: Exception) {
                QuoteDbState.Error
            }
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
