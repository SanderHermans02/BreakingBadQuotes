package com.example.breakingbadquotes.network

import com.example.breakingbadquotes.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class ApiQuote(
    val quote: String,
    val author: String,
    val isFavorite: Boolean = true,
)

fun ApiQuote.asDomainObjects(): Quote {
    return Quote(quote = this.quote, author = this.author, isFavorite = this.isFavorite)
}
