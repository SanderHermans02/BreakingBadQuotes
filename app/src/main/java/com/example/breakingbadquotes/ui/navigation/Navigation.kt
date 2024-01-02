package com.example.breakingbadquotes.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breakingbadquotes.ui.favoritesScreen.FavoritesScreen
import com.example.breakingbadquotes.ui.quoteScreen.QuoteScreen

@Composable
fun navComponent(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = QuoteOverviewScreen.Quote.name,
        modifier = modifier,
    ) {
        composable(route = QuoteOverviewScreen.Quote.name) {
            QuoteScreen(windowSizeClass)
        }
        composable(route = QuoteOverviewScreen.Favorites.name) {
            FavoritesScreen()
        }
    }
}
