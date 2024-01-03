package com.example.breakingbadquotes.fake

import com.example.breakingbadquotes.data.QuoteRepository
import com.example.breakingbadquotes.model.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiQuoteRepository : QuoteRepository {

    private val favoriteQuotes = mutableListOf<Quote>()
    private val quotes = mutableListOf<Quote>()
    override suspend fun getFavoriteQuotes(): Flow<List<Quote>> {
        return flowOf(favoriteQuotes)
    }

    override suspend fun getQuote(): List<Quote> {
        return quotes
    }

    override suspend fun insertQuote(quote: Quote) {
        // do nothing
    }

    override suspend fun deleteQuote(quote: Quote) {
        // do nothing
    }
}
