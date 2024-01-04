package com.example.breakingbadquotes.ui.states

import com.example.breakingbadquotes.model.Quote

/**
 * Represents the possible states of a Quote API call within the application.
 * Used for managing and representing different states of the API request lifecycle.
 */
sealed interface QuoteApiState {
    /**
     * State representing a successful API response with a quote.
     *
     * @property quote The successfully retrieved quote.
     */
    data class Success(val quote: Quote) : QuoteApiState

    /**
     * State representing an error during the API request.
     */
    object Error : QuoteApiState

    /**
     * State representing the loading phase of the API request.
     */
    object Loading : QuoteApiState

    /**
     * State representing a no internet connection scenario during the API request.
     */
    object NoInternet : QuoteApiState
}

/**
 * Represents the possible states of the Quote database operations within the application.
 * Used for managing and representing different states of database interactions.
 */
sealed interface QuoteDbState {
    /**
     * State representing a successful database operation with a list of quotes.
     *
     * @property quotes The list of quotes retrieved from the database.
     */
    data class Success(val quote: List<Quote>) : QuoteDbState

    /**
     * State representing an error in database operations.
     */
    object Error : QuoteDbState

    /**
     * State representing the loading phase of the database operation.
     */
    object Loading : QuoteDbState
}
