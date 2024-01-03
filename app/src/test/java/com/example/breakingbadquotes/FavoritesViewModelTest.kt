package com.example.breakingbadquotes

import com.example.breakingbadquotes.fake.FakeApiQuoteRepository
import com.example.breakingbadquotes.fake.FakeDataSource
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.ui.favoritesScreen.FavoritesViewModel
import com.example.breakingbadquotes.ui.states.QuoteDbState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
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

class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var fakeRepository: FakeApiQuoteRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun init() {
        fakeRepository = FakeApiQuoteRepository()

        fakeRepository.favoriteQuotes.add(
            Quote(
                FakeDataSource.quotes.first().quote,
                FakeDataSource.quotes.first().author,
                true,
            ),
        )
        fakeRepository.favoriteQuotes.add(
            Quote(
                FakeDataSource.quotes.last().quote,
                FakeDataSource.quotes.last().author,
                true,
            ),
        )

        viewModel = FavoritesViewModel(fakeRepository)
    }

    @Test
    fun getFavoriteQuotesTest() = runTest {
        val state = viewModel.quoteDbState
        val favoriteQuotes = viewModel.favoriteQuotes.first()

        assertTrue(state is QuoteDbState.Success)
        assertEquals(2, favoriteQuotes.size)
    }

    @Test
    fun removeFavoriteTest() = runTest {
        val initialFavorites = viewModel.favoriteQuotes.value
        val quoteToRemove = initialFavorites.first()

        viewModel.removeFavorite(quoteToRemove)

        val updatedFavorites = viewModel.favoriteQuotes.value
        assertFalse(updatedFavorites.contains(quoteToRemove))
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
