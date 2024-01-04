package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.breakingbadquotes.R

/**
 * Composable function that creates a navigation rail for the Breaking Bad Quotes application.
 * It provides navigation items for quotes and favorites. This component is typically used
 * for larger screen sizes where a navigation rail is more suitable than a bottom navigation bar.
 *
 * @param goToQuotes Function to be invoked when the quotes navigation item is clicked.
 * @param goToFavorites Function to be invoked when the favorites navigation item is clicked.
 */
@Composable
fun BBQNavigationRail(goToQuotes: () -> Unit, goToFavorites: () -> Unit) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        // NavigationRailItem for Quotes
        NavigationRailItem(
            selected = false,
            onClick = goToQuotes,
            icon = {
                Icon(
                    Icons.Default.FormatQuote,
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
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.navigate_to_favorites),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
