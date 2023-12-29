package com.example.breakingbadquotes.ui.quoteScreen

import android.content.ContentValues.TAG
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class QuoteViewModel(val quoteRepository: QuoteRepository) : ViewModel() {

    var quoteState: QuoteState by mutableStateOf(QuoteState.Loading)
        private set

    init {
        getQuote()
        Log.i("vm inspection", "QuoteViewModel init")
    }
    fun getQuote() {
        viewModelScope.launch {
            quoteState = try {
                val quotes = quoteRepository.getQuote() // This is now a List<Quote>
                if (quotes.isNotEmpty()) {
                    // If the list is not empty, take the first quote
                    QuoteState.Success(quotes.first())
                } else {
                    // Handle the case where the list is empty
                    QuoteState.Error // You might want to introduce a specific empty state
                }
            } catch (ex: IOException) {
                QuoteState.NoInternet
            } catch (e: Exception) {
                Log.e("QuoteViewModel", e.message.toString())
                QuoteState.Error
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
