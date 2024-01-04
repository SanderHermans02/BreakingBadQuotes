package com.example.breakingbadquotes.network

import com.example.breakingbadquotes.model.Quote
import kotlinx.serialization.Serializable

/**
 * Data class that represents a quote retrieved from the network API.
 * This class is used for parsing JSON response into objects using serialization.
 *
 * @property quote The text of the quote.
 * @property author The author or character who said the quote.
 * @property isFavorite A flag indicating whether the quote is marked as a favorite.
 */
@Serializable
data class ApiQuote(
    val quote: String,
    val author: String,
    val isFavorite: Boolean = true,
)

/**
 * Converts an [ApiQuote] instance to a [Quote] domain object that can be used throughout the app.
 *
 * @return [Quote] Returns a domain object representing the quote.
 */
fun ApiQuote.asDomainObjects(): Quote {
    return Quote(quote = this.quote, author = this.author, isFavorite = this.isFavorite)
}
