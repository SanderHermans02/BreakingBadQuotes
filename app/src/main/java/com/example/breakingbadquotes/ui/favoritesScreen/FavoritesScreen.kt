package com.example.breakingbadquotes.ui.favoritesScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoritesScreen(vm: FavoritesViewModel = viewModel()) {
    Text(text = "favorites")
}
