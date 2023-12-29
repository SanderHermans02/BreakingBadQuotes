package com.example.breakingbadquotes.model

data class Quote(
    val quote: String,
    val author: String,
    var isFavorite: Boolean = false,
)
