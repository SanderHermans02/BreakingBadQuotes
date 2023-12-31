package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.breakingbadquotes.R
import com.example.breakingbadquotes.model.Quote

/**
 * Composable function that creates a card item displaying a quote.
 * It shows the quote text and author, along with an icon to mark the quote as favorite.
 * The favorite state of the quote can be toggled by clicking the icon.
 *
 * @param quote The quote to be displayed in this card.
 * @param isFavorite Indicates whether the quote is marked as a favorite.
 * @param onFavoriteClick Function to be invoked when the favorite icon is clicked.
 * @param modifier Modifier to be applied to the card (optional).
 */
@Composable
fun QuoteItem(quote: Quote, isFavorite: Boolean, onFavoriteClick: (Quote) -> Unit, modifier: Modifier = Modifier) {
    Card(
        // Card appearance configuration
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.card_space)),
    ) {
        Column(
            // Layout configuration and content
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.card_inside_space)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = quote.quote,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.quote_space)),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onFavoriteClick(quote) }) {
                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = if (isFavorite) {
                            stringResource(R.string.unfavorite_this_quote)
                        } else {
                            stringResource(R.string.favorite_this_quote)
                        },
                    )
                }
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
