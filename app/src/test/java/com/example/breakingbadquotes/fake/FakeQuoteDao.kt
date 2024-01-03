package com.example.breakingbadquotes.fake

import com.example.breakingbadquotes.data.database.DbQuote
import com.example.breakingbadquotes.data.database.QuoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeQuoteDao : QuoteDao {
    private val quoteList = mutableListOf<DbQuote>()
    private val quotesFlow = MutableStateFlow<List<DbQuote>>(emptyList())

    override suspend fun insert(item: DbQuote) {
        quoteList.add(item)
        updateQuotesFlow()
    }

    override suspend fun delete(item: DbQuote) {
        quoteList.remove(item)
        updateQuotesFlow()
    }

    override fun getAllItems(): Flow<List<DbQuote>> = quotesFlow.asStateFlow()

    private fun updateQuotesFlow() {
        quotesFlow.value = quoteList.toList()
    }
}
