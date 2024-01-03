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
    fun setUp() {
        // Initialize the FakeQuoteApiService before each test
        quoteApiService = FakeQuoteApiService()
        val fakeQuoteDao = FakeQuoteDao()
        // Note: You would also need to create a fake QuoteDao implementation
        // For this example, we're just focusing on the API part
        repository = CachingQuotesRepository(fakeQuoteDao, quoteApiService)
    }

    @Test
    fun getQuotes_returnsExpectedData() = runBlocking {
        // When we ask the repository for quotes
        val quotes = repository.getQuote()

        // Then the expected data is returned
        assertEquals(FakeDataSource.quotes.map { it.asDomainObjects() }, quotes)
    }
}
