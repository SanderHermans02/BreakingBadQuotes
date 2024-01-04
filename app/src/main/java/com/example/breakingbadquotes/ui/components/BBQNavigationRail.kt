package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.outlined.FormatQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.navigation.Page

/**
 * Composable function that creates a navigation rail for the Breaking Bad Quotes application.
 * It provides navigation items for quotes and favorites. This component is typically used
 * for larger screen sizes where a navigation rail is more suitable than a bottom navigation bar.
 *
 * @param currentActivePage Indicates the current active page in the app.
 * @param goToQuotes Function to be invoked when the quotes navigation item is clicked.
 * @param goToFavorites Function to be invoked when the favorites navigation item is clicked.
 */
@Composable
fun BBQNavigationRail(currentActivePage: Page, goToQuotes: () -> Unit, goToFavorites: () -> Unit) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        // NavigationRailItem for Quotes
        NavigationRailItem(
            selected = false,
            onClick = goToQuotes,
            icon = {
                val quoteIcon = if (currentActivePage == Page.QUOTES) {
                    Icons.Default.FormatQuote
                } else {
                    Icons.Outlined.FormatQuote
                }
                Icon(
                    quoteIcon,
                    contentDescription = stringResource(id = R.string.navigate_to_quotes),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        // NavigationRailItem for Favorites
        NavigationRailItem(
            selected = false,
            onClick = goToFavorites,
            icon = {
                val favoriteIcon = if (currentActivePage == Page.FAVORITES) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                }
                Icon(
                    favoriteIcon,
                    contentDescription = stringResource(id = R.string.navigate_to_favorites),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
