package com.example.breakingbadquotes.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.components.BBQBottomAppBar
import com.example.breakingbadquotes.ui.components.BBQNavigationRail
import com.example.breakingbadquotes.ui.components.BBQTopBar
import com.example.breakingbadquotes.ui.components.Page
import com.example.breakingbadquotes.ui.favoritesScreen.FavoritesScreen
import com.example.breakingbadquotes.ui.quoteScreen.QuoteScreen
import com.example.breakingbadquotes.ui.util.QuoteNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BBQApp(
    navigationType: QuoteNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val currentPage = when (currentRoute) {
        Destinations.Quote.name -> Page.QUOTES
        Destinations.Favorites.name -> Page.FAVORITES
        else -> Page.QUOTES
    }

    val goToQuotes = { if (canNavigate(currentBackStack, Destinations.Quote.name)) navController.navigate(Destinations.Quote.name) }
    val goToFavorites = { if (canNavigate(currentBackStack, Destinations.Favorites.name)) navController.navigate(Destinations.Favorites.name) }

    if (navigationType == QuoteNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                BBQTopBar()
            },
            bottomBar = {
                BBQBottomAppBar(currentPage, goToQuotes, goToFavorites)
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Destinations.Quote.name,
                modifier = Modifier.padding(innerPadding).padding(horizontal = dimensionResource(R.dimen.padding_screen_borders)),
            ) {
                composable(Destinations.Quote.name) {
                    QuoteScreen()
                }
                composable(Destinations.Favorites.name) {
                    FavoritesScreen()
                }
            }
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == QuoteNavigationType.NAVIGATION_RAIL) {
                BBQNavigationRail(
                    goToQuotes = { if (canNavigate(currentBackStack, Destinations.Quote.name)) navController.navigate(Destinations.Quote.name) },
                    goToFavorites = { if (canNavigate(currentBackStack, Destinations.Favorites.name)) navController.navigate(Destinations.Favorites.name) },
                )
            }
            Scaffold(
                topBar = {
                    BBQTopBar()
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Destinations.Quote.name,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable(
                        Destinations.Quote.name,
                    ) {
                        QuoteScreen()
                    }
                    composable(
                        Destinations.Favorites.name,
                    ) {
                        FavoritesScreen()
                    }
                }
            }
        }
    }
}

enum class Destinations {
    Quote,
    Favorites,
}

private fun canNavigate(current: NavBackStackEntry?, destination: String): Boolean {
    val currentDest = current?.destination?.route

    return if (current != null) currentDest != destination else true
}
