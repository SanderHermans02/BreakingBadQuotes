package com.example.breakingbadquotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.breakingbadquotes.model.Quote
import com.example.breakingbadquotes.ui.components.QuoteItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteItemTest {
    private var quote by mutableStateOf(Quote("quote", "author", false))

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            QuoteItem(
                quote = quote,
                isFavorite = quote.isFavorite,
                onFavoriteClick = { quote = it.copy(isFavorite = !it.isFavorite) },
            )
        }
    }

    @Test
    fun isQuoteDisplayed() {
        composeTestRule.onNodeWithText("quote").assertIsDisplayed()
    }

    @Test
    fun isAuthorDisplayed() {
        composeTestRule.onNodeWithText("author").assertIsDisplayed()
    }

    @Test
    fun toggleFavorite() {
        composeTestRule.onNodeWithContentDescription("favorite this quote").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("favorite this quote").performClick()
        composeTestRule.onNodeWithContentDescription("unfavorite this quote").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("unfavorite this quote").performClick()
        composeTestRule.onNodeWithContentDescription("favorite this quote").assertIsDisplayed()
    }
}
