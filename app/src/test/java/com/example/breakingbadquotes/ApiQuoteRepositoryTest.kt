package com.example.breakingbadquotes

import com.example.breakingbadquotes.data.CachingQuotesRepository
import com.example.breakingbadquotes.fake.FakeDataSource
import com.example.breakingbadquotes.fake.FakeQuoteApiService
import com.example.breakingbadquotes.fake.FakeQuoteDao
import com.example.breakingbadquotes.network.QuoteApiService
import com.example.breakingbadquotes.network.asDomainObjects
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApiQuoteRepositoryTest {

    private lateinit var quoteApiService: QuoteApiService
    private lateinit var repository: CachingQuotesRepository

    @Before
    fun init() {
        quoteApiService = FakeQuoteApiService()
        val fakeQuoteDao = FakeQuoteDao()
        repository = CachingQuotesRepository(fakeQuoteDao, quoteApiService)
    }

    @Test
    fun getQuotes_returnsExpectedData() = runBlocking {
        val quotes = repository.getQuote()
        assertEquals(FakeDataSource.quotes.map { it.asDomainObjects() }, quotes)
    }
}
