package com.example.breakingbadquotes.ui.quoteScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.components.QuoteItem
import com.example.breakingbadquotes.ui.states.QuoteApiState

/**
 * Composable function that displays the quote screen for the Breaking Bad Quotes application.
 * It observes the UI state for quotes and manages the display of quotes, loading indicators,
 * or error messages accordingly. This screen also handles user interactions to fetch new quotes
 * and to mark quotes as favorites.
 */
@Composable
fun QuoteScreen() {
    // ViewModel for managing quote data and interactions
    val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModel.Factory)
    // State holder for favorite quotes, observed from the ViewModel
    val favoriteQuotes by quoteViewModel.favoriteQuotes.collectAsState()
    // The current UI state that dictates the content display
    val uiState = quoteViewModel.quoteApiState

    // Constraints for adaptive UI based on screen size
    BoxWithConstraints {
        val maxWidth = maxWidth
        val isCompact = maxWidth < dimensionResource(id = R.dimen.min_width)
        // Handle UI state to show Loading, Success, Error, or No Internet message
        when (uiState) {
            is QuoteApiState.Loading -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.quote_screen_title),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                    Text(
                        text = stringResource(id = R.string.loading),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                }
            }
            is QuoteApiState.Success -> {
                val isCurrentQuoteFavorite = favoriteQuotes.any { it.quote == uiState.quote.quote }

                // Display the successful retrieval of a quote
                // Handle compact and expanded screen layouts
                if (isCompact) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(id = R.string.quote_screen_title),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                        )
                        QuoteItem(
                            quote = uiState.quote,
                            isFavorite = isCurrentQuoteFavorite,
                            onFavoriteClick = {
                                if (isCurrentQuoteFavorite) {
                                    quoteViewModel.removeFavorite(uiState.quote)
                                } else {
                                    quoteViewModel.addFavorite(uiState.quote)
                                }
                            },
                        )
                        Row(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.button_space))
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Button(onClick = { quoteViewModel.getQuote() }) {
                                Text(text = stringResource(id = R.string.get_quote))
                            }
                        }
                    }
                } else {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimensionResource(id = R.dimen.button_space)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(id = R.string.quote_screen_title),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                            )
                            Button(
                                onClick = { quoteViewModel.getQuote() },
                            ) {
                                Text(text = stringResource(id = R.string.get_quote))
                            }
                        }
                        QuoteItem(
                            quote = uiState.quote,
                            isFavorite = isCurrentQuoteFavorite,
                            onFavoriteClick = {
                                if (isCurrentQuoteFavorite) {
                                    quoteViewModel.removeFavorite(uiState.quote)
                                } else {
                                    quoteViewModel.addFavorite(uiState.quote)
                                }
                            },
                        )
                    }
                }
            }
            is QuoteApiState.Error -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.quote_screen_title),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                    Text(
                        text = stringResource(id = R.string.error_message),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                }
            }
            is QuoteApiState.NoInternet -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.quote_screen_title),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                    Text(
                        text = stringResource(id = R.string.no_internet),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)),
                    )
                }
            }
        }
    }
}
