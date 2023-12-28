package com.example.breakingbadquotes.ui.quoteScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.ui.components.QuoteItem

@Composable
fun QuoteScreen() {
    val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModel.Factory)
    val uiState = quoteViewModel.quoteState
    when (uiState) {
        QuoteState.Loading -> {
            Text(text = stringResource(id = R.string.loading))
        }
        is QuoteState.Success -> {
            // Pass the quote data to the QuoteItem composable
            Text(text = "quotes")
            QuoteItem(quote = uiState.quote)
        }
        QuoteState.Error -> {
            // Handle error state
            Text(text = stringResource(id = R.string.error_message))
        }
        QuoteState.NoInternet -> {
            // Handle no internet state
            Text(text = stringResource(id = R.string.no_internet))
        }
    }
}
