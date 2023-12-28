package com.example.breakingbadquotes.data

import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.network.QuoteApiService
import com.example.breakingbadquotes.network.asDomainObjects

interface QuoteRepository {
    suspend fun getQuote(): Quote
}

class ApiQuoteRepository(
    private val quoteApiService: QuoteApiService
): QuoteRepository{
    override suspend fun getQuote(): Quote {
        return quoteApiService.getQuote().asDomainObjects()
    }
}