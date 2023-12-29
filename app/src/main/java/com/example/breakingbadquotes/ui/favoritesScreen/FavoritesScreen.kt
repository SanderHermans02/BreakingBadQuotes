package com.example.breakingbadquotes.ui.favoritesScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.breakingbadquotes.ui.quoteScreen.QuoteViewModel
import com.example.breakingbadquotes.ui.states.QuoteApiState
import com.example.breakingbadquotes.ui.states.QuoteDbState

@Composable
fun FavoritesScreen() {
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
    val uiState = favoritesViewModel.quoteDbState
    when (uiState) {
        is QuoteDbState.Loading -> {
            Text(text = "loading")
        }
        is QuoteDbState.Success -> {
            Text(text = "success")
        }
        is QuoteDbState.Error -> {
            Text(text = "error")
        }
        is QuoteDbState.NoInternet -> {
            Text(text = "no internet")
        }
    }
    Text(text = "favorites")
}
