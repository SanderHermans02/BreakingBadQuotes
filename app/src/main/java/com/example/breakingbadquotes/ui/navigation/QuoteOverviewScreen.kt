package com.example.breakingbadquotes.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.breakingbadquotes.R

enum class QuoteOverviewScreen(@StringRes val title: Int, val icon: ImageVector) {
    Quote(title = R.string.quote_screen_title, icon = Icons.Filled.FormatQuote),
    Favorites(title = R.string.favorites_screen_title, Icons.Filled.Favorite),
}
