package com.example.breakingbadquotes.ui.quoteScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuoteScreen(vm: QuoteViewModel = viewModel()) {
    Text(text = "quotes")
}
