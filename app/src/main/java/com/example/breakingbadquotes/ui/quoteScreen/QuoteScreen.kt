package com.example.breakingbadquotes.ui.quoteScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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

@Composable
fun QuoteScreen(windowSizeClass: WindowSizeClass) {
    val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModel.Factory)
    val favoriteQuotes by quoteViewModel.favoriteQuotes.collectAsState()
    val uiState = quoteViewModel.quoteApiState
    when (uiState) {
        is QuoteApiState.Loading -> {
            Text(text = stringResource(id = R.string.loading))
        }
        is QuoteApiState.Success -> {
            val isCurrentQuoteFavorite = favoriteQuotes.any { it.quote == uiState.quote.quote }

            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
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
            Text(text = stringResource(id = R.string.error_message))
        }
        is QuoteApiState.NoInternet -> {
            Text(text = stringResource(id = R.string.no_internet))
        }
    }
}
