package com.example.breakingbadquotes.fake

import com.example.breakingbadquotes.network.ApiQuote
import com.example.breakingbadquotes.network.QuoteApiService

class FakeQuoteApiService : QuoteApiService {
    override suspend fun getQuotes(): List<ApiQuote> {
        return FakeDataSource.quotes
    }
}
