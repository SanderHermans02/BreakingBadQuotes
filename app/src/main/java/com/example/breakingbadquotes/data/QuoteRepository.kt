package com.example.breakingbadquotes.data

import android.content.Context
import com.example.breakingbadquotes.data.database.QuoteDao
import com.example.breakingbadquotes.data.database.asDbQuote
import com.example.breakingbadquotes.data.database.asDomainQuotes
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.network.QuoteApiService
import com.example.breakingbadquotes.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface QuoteRepository {
    fun getFavoriteQuotes(): Flow<List<Quote>>
    suspend fun getQuote(): List<Quote>

    suspend fun insertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)
}
class CachingQuotesRepository(
    private val quoteDao: QuoteDao,
    private val quoteApiService: QuoteApiService,
    context: Context,
) : QuoteRepository {
    override fun getFavoriteQuotes(): Flow<List<Quote>> {
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

/*class ApiQuoteRepository(
    private val quoteApiService: QuoteApiService
): QuoteRepository {
    override suspend fun getQuote(): List<Quote> {
        return quoteApiService.getQuotes().map { it.asDomainObjects() }
    }
}*/
