package com.example.breakingbadquotes.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breakingbadquotes.ui.components.BBQBottomAppBar
import com.example.breakingbadquotes.ui.components.BBQNavigationRail
import com.example.breakingbadquotes.ui.components.BBQTopBar
import com.example.breakingbadquotes.ui.navigation.QuoteOverviewScreen
import com.example.breakingbadquotes.ui.navigation.navComponent
import com.example.breakingbadquotes.ui.util.QuoteNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BBQApp(
    windowSizeClass: WindowSizeClass,
    navigationType: QuoteNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val goToQuotes: () -> Unit = { navController.popBackStack(QuoteOverviewScreen.Quote.name, inclusive = false) }
    val goToFavorites = { navController.navigate(QuoteOverviewScreen.Favorites.name) { launchSingleTop = true } }

    if (navigationType == QuoteNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                BBQTopBar()
            },
            bottomBar = {
                BBQBottomAppBar(goToQuotes, goToFavorites)
            },
        ) { innerPadding ->
            navComponent(windowSizeClass, navController, modifier = Modifier.padding(innerPadding))
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == QuoteNavigationType.NAVIGATION_RAIL) {
                BBQNavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                topBar = {
                    BBQTopBar()
                },
            ) { innerPadding ->
                navComponent(windowSizeClass, navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
