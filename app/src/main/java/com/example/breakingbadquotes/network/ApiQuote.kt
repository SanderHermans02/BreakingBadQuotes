package com.example.breakingbadquotes.network

import com.example.breakingbadquotes.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class ApiQuote(
    val quote: String,
    val author: String,
)

fun ApiQuote.asDomainObjects(): Quote {
    return Quote(quote = this.quote, author = this.author)
}
