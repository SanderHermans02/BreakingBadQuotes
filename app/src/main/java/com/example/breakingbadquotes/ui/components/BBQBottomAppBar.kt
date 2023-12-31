package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.outlined.FormatQuote
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.navigation.Page

/**
 * Composable function that creates a bottom app bar for the Breaking Bad Quotes application.
 * It contains navigation icons to switch between different pages of the app.
 *
 * @param currentActivePage Indicates the current active page in the app.
 * @param goToQuotes Function to be invoked when the quotes icon is clicked.
 * @param goToFavorites Function to be invoked when the favorites icon is clicked.
 */
@Composable
fun BBQBottomAppBar(currentActivePage: Page, goToQuotes: () -> Unit, goToFavorites: () -> Unit) {
    // BottomAppBar implementation with IconButton for quotes and favorites
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = goToQuotes, modifier = Modifier.weight(1f)) {
                    val quoteIcon = if (currentActivePage == Page.QUOTES) {
                        Icons.Default.FormatQuote
                    } else {
                        Icons.Outlined.FormatQuote
                    }
                    Icon(
                        quoteIcon,
                        contentDescription = stringResource(id = R.string.navigate_to_quotes),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    )
                }
                IconButton(onClick = goToFavorites, modifier = Modifier.weight(1f)) {
                    val favoriteIcon = if (currentActivePage == Page.FAVORITES) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    }
                    Icon(
                        favoriteIcon,
                        contentDescription = stringResource(id = R.string.navigate_to_favorites),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    )
                }
            }
        },
    )
}


