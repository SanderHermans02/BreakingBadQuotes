package com.example.breakingbadquotes.ui.favoritesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.components.QuoteItem
import com.example.breakingbadquotes.ui.states.QuoteDbState

@Composable
fun FavoritesScreen() {
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
    val uiState = favoritesViewModel.quoteDbState
    val favoriteQuotes by favoritesViewModel.favoriteQuotes.collectAsState()
    when (uiState) {
        is QuoteDbState.Loading -> {
            Text(text = stringResource(id = R.string.loading))
        }
        is QuoteDbState.Success -> {
            Column {
                Text(
                    text = stringResource(id = R.string.favorites_screen_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                )
                when {
                    favoriteQuotes.isEmpty() -> {
                        Text(
                            text = stringResource(id = R.string.no_favorites),
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.title_space)),
                        )
                    }
                    favoriteQuotes.isNotEmpty() -> {
                        LazyColumn {
                            items(favoriteQuotes) { quote ->
                                QuoteItem(quote = quote, isFavorite = true, onFavoriteClick = { favoritesViewModel.removeFavorite(quote) })
                            }
                        }
                    }
                    else -> {
                        Text(text = stringResource(id = R.string.error_message))
                    }
                }
            }
        }
        is QuoteDbState.Error -> {
            Text(text = stringResource(id = R.string.error_message))
        }
    }
}
