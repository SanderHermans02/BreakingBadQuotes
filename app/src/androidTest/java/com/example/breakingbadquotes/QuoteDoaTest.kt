package com.example.breakingbadquotes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.breakingbadquotes.data.database.QuoteDao
import com.example.breakingbadquotes.data.database.QuoteDb
import com.example.breakingbadquotes.data.database.asDbQuote
import com.example.breakingbadquotes.data.database.asDomainQuote
import com.example.breakingbadquotes.model.Quote
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class QuoteDoaTest {
    private lateinit var quoteDao: QuoteDao
    private lateinit var quoteDb: QuoteDb

    private var quote1 = Quote("quote1", "author1", true)
    private var quote2 = Quote("quote2", "author2", true)

    private suspend fun addOneQuoteToDb() {
        quoteDao.insert(quote1.asDbQuote())
    }

    private suspend fun addTwoQuotesToDb() {
        quoteDao.insert(quote1.asDbQuote())
        quoteDao.insert(quote2.asDbQuote())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        quoteDb = Room.inMemoryDatabaseBuilder(context, QuoteDb::class.java)
            .allowMainThreadQueries()
            .build()
        quoteDao = quoteDb.quoteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        quoteDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertQuoteIntoDB() = runBlocking {
        addOneQuoteToDb()
        val allItems = quoteDao.getAllItems().first()
        assertEquals(allItems[0].asDomainQuote().quote, quote1.quote)
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertTwoQuotesIntoDB() = runBlocking {
        addTwoQuotesToDb()
        val allItems = quoteDao.getAllItems().first()
        assertEquals(allItems[0].asDomainQuote().quote, quote1.quote)
        assertEquals(allItems[1].asDomainQuote().quote, quote2.quote)
    }
}
