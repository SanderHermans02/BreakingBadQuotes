package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.model.Quote

@Composable
fun QuoteItem(quote: Quote, isFavorite: Boolean, favoriteQuote: () -> Unit, unfavoriteQuote: () -> Unit) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_space))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.card_inside_space))
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    Text(
                        text = quote.quote,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.quote_space))
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if (isFavorite)
                        IconButton(onClick = { unfavoriteQuote() }) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = stringResource(id = R.string.unfavorite_this_quote),
                            )
                        }
                    else
                        IconButton(onClick = { favoriteQuote() }) {
                            Icon(
                                Icons.Default.FavoriteBorder,
                                contentDescription = stringResource(id = R.string.favorite_this_quote),
                            )
                        }
                    Text(
                        text = quote.author,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }