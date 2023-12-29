package com.example.breakingbadquotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.breakingbadquotes.model.Quote

@Composable
fun QuoteItem(quote: Quote){
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
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = quote.author,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

@Preview
@Composable
fun QuoteItemPreview(){
    val sampleQuote = Quote(
        quote = "I am the one who knocks!",
        author = "Walter White"
    )
    QuoteItem(quote = sampleQuote)
}