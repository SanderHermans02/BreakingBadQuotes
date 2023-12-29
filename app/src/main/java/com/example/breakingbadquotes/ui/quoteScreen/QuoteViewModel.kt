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
import com.example.breakingbadquotes.ui.states.QuoteState
import kotlinx.coroutines.launch
import java.io.IOException

class QuoteViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    var quoteApiState: QuoteApiState by mutableStateOf(QuoteApiState.Loading)
        private set
    var quoteState: QuoteState by mutableStateOf(QuoteState(false))
        private set
    var listOfQuotes: List<Quote> by mutableStateOf(mutableListOf())
        private set
    init {
        getQuote()
        Log.i("vm inspection", "QuoteViewModel init")
    }

    fun addFavorite(quote: Quote) {
        viewModelScope.launch {
            try {
                listOfQuotes = listOfQuotes.updated(listOfQuotes.indexOf(quote), Quote(quote.quote, quote.author, true))
                quoteRepository.insertQuote(quote)
            } catch (ex: Exception) {
                Log.e("QuoteViewModel", ex.message.toString())
            }
        }
    }

    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            try {
                listOfQuotes = listOfQuotes.updated(listOfQuotes.indexOf(quote), Quote(quote.quote, quote.author, false))
                quoteRepository.deleteQuote(quote)
            } catch (ex: Exception) {
                Log.e("QuoteViewModel", ex.message.toString())
            }
        }
    }

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
        private var Instance: QuoteViewModel? = null
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

fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }
