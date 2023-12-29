package com.example.breakingbadquotes.data

import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.network.QuoteApiService
import com.example.breakingbadquotes.network.asDomainObjects

interface QuoteRepository {
    suspend fun getQuote(): List<Quote>
}

class ApiQuoteRepository(
    private val quoteApiService: QuoteApiService
): QuoteRepository {
    override suspend fun getQuote(): List<Quote> {
        return quoteApiService.getQuotes().map { it.asDomainObjects() }
    }
}
