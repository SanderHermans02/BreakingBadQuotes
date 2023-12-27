package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.breakingbadquotes.R

@Composable
fun BBQBottomAppBar(goToQuotes: () -> Unit, goToFavorites: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = goToQuotes, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.FormatQuote,
                        contentDescription = "navigate to quotes",
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    )
                }
                IconButton(onClick = goToFavorites, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "navigate to favorites",
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    )
                }
            }
        },
    )
}
