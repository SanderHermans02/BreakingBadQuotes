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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.quote_screen_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.title_space)))
                QuoteItem(quote = uiState.quote)
                Row (
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.button_space))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = stringResource(id = R.string.add_to_favorites))
                    }
                    Button(onClick = { quoteViewModel.getQuote() }) {
                        Text(text = stringResource(id = R.string.get_quote))

                    }
                }
            }
        }
        QuoteState.Error -> {
            Text(text = stringResource(id = R.string.error_message))
        }
        QuoteState.NoInternet -> {
            Text(text = stringResource(id = R.string.no_internet))
        }
    }
}