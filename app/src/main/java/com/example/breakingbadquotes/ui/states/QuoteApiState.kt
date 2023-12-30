package com.example.breakingbadquotes.ui.states

import com.example.breakingbadquotes.model.Quote

data class QuoteState(
    val isFavorite: Boolean,
)
sealed interface QuoteApiState {
    data class Success(val quote: Quote) : QuoteApiState
    object Error : QuoteApiState
    object Loading : QuoteApiState
    object NoInternet : QuoteApiState
}

sealed interface QuoteDbState {
    data class Success(val quote: List<Quote>) : QuoteDbState
    object Error : QuoteDbState
    object Loading : QuoteDbState
}
