package com.example.breakingbadquotes

import com.example.breakingbadquotes.fake.FakeApiQuoteRepository
import com.example.breakingbadquotes.fake.FakeDataSource
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.ui.quoteScreen.QuoteViewModel
import com.example.breakingbadquotes.ui.states.QuoteApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class QuoteViewModelTest {

    private lateinit var viewModel: QuoteViewModel
    private lateinit var fakeRepository: FakeApiQuoteRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun init() {
        fakeRepository = FakeApiQuoteRepository()
        viewModel = QuoteViewModel(fakeRepository)
    }

    @Test
    fun getQuoteTest() = runTest {
        val expectedQuote = Quote(FakeDataSource.quotes.first().quote, FakeDataSource.quotes.first().author, false)
        fakeRepository.quotes.add(expectedQuote)

        viewModel.getQuote()

        val state = viewModel.quoteApiState
        assertTrue(state is QuoteApiState.Success)
        assertEquals(expectedQuote, (state as QuoteApiState.Success).quote)
    }

    @Test
    fun addFavoriteTest() = runTest {
        val quoteToAdd = Quote(FakeDataSource.quotes.first().quote, FakeDataSource.quotes.first().author, false)

        viewModel.addFavorite(quoteToAdd)

        assertTrue(viewModel.favoriteQuotes.value.any { it == quoteToAdd.copy(isFavorite = true) })
    }

    @Test
    fun removeFavoriteTest() = runTest {
        val quoteToRemove = Quote(FakeDataSource.quotes.first().quote, FakeDataSource.quotes.first().author, true)
        viewModel.addFavorite(quoteToRemove)

        viewModel.removeFavorite(quoteToRemove)

        assertFalse(viewModel.favoriteQuotes.value.any { it == quoteToRemove })
    }

    class TestDispatcherRule(
        val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}
