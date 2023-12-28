package com.example.breakingbadquotes.ui.quoteScreen

import com.example.breakingbadquotes.model.Quote

sealed interface QuoteState {
    data class Success(val quote: Quote) : QuoteState
    object Error : QuoteState
    object Loading : QuoteState
    object NoInternet : QuoteState
}