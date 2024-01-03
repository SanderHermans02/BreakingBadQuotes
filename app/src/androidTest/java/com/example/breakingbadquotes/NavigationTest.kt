package com.example.breakingbadquotes

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.breakingbadquotes.ui.BBQApp
import com.example.breakingbadquotes.ui.util.QuoteNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest() {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BBQApp(navController = navController, navigationType = QuoteNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Generate A Quote")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToFavorites() {
        composeTestRule
            .onNodeWithContentDescription("navigate to favorited quotes")
            .performClick()
        composeTestRule
            .onNodeWithText("Favorite Quotes")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToFavoritesAndBack() {
        composeTestRule
            .onNodeWithContentDescription("navigate to favorited quotes")
            .performClick()
        composeTestRule
            .onNodeWithText("Favorite Quotes")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("navigate to quote generator")
            .performClick()
        composeTestRule
            .onNodeWithText("Generate A Quote")
            .assertIsDisplayed()
    }
}
