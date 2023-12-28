package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.breakingbadquotes.R

@Composable
fun QuoteItem(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_space)) // Adjust padding for mobile screen
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.card_inside_space)) // Padding inside the card
            ) {
                Text(
                    text = "The fun’s over. From here on out, I’m Mr. Low Profile. Just another douche bag with a job and three pairs of Dockers. If I’m lucky, month from now, best-case scenario, I’m managing a Cinnabon in Omaha.",
                    style = MaterialTheme.typography.bodyLarge, // Adjust text style for readability
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.quote_space)) // Spacing between the quote and the author
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "Walter White",
                        style = MaterialTheme.typography.bodyMedium // Adjust text style for the author
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuoteItemPreview(){
    QuoteItem()
}