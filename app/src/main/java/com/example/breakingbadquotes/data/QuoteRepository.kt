package com.example.breakingbadquotes.data

import com.example.breakingbadquotes.data.database.QuoteDao
import com.example.breakingbadquotes.data.database.asDbQuote
import com.example.breakingbadquotes.data.database.asDomainQuotes
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.network.QuoteApiService
import com.example.breakingbadquotes.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Interface defining the repository operations for fetching and managing quotes.
 */
interface QuoteRepository {
    /**
     * Retrieves a flow of favorite quotes from the database.
     * @return A flow emitting a list of [Quote] representing favorite quotes.
     */
    suspend fun getFavoriteQuotes(): Flow<List<Quote>>

    /**
     * Fetches a random quote from the API.
     * @return A list of [Quote] fetched from the API.
     */
    suspend fun getQuote(): List<Quote>

    /**
     * Inserts a quote into the database as a favorite.
     * @param quote The [Quote] to be inserted.
     */
    suspend fun insertQuote(quote: Quote)

    /**
     * Deletes a quote from the database.
     * @param quote The [Quote] to be deleted.
     */
    suspend fun deleteQuote(quote: Quote)
}

/**
 * A concrete implementation of [QuoteRepository] that provides caching functionality.
 * This repository handles the retrieval and update of quotes in the local database,
 * as well as fetching them from the API when needed.
 *
 * @property quoteDao The DAO for accessing the quote database.
 * @property quoteApiService The service for accessing the quote API.
 */
class CachingQuotesRepository(
    private val quoteDao: QuoteDao,
    private val quoteApiService: QuoteApiService,
) : QuoteRepository {
    // Implementations of the QuoteRepository interface
    override suspend fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao.getAllItems().map { it.asDomainQuotes() }
    }

    override suspend fun getQuote(): List<Quote> {
        return quoteApiService.getQuotes().map { it.asDomainObjects() }
    }

    override suspend fun insertQuote(quote: Quote) {
        quoteDao.insert(quote.asDbQuote())
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteDao.delete(quote.asDbQuote())
    }
}
