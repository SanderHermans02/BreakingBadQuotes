package com.example.breakingbadquotes.model

/**
 * Data class representing a single quote. It holds information about the text of the quote,
 * the author who said it, and a flag indicating whether it has been marked as a favorite by the user.
 *
 * @property quote The text of the quote.
 * @property author The name of the author or the character from the series who said the quote.
 * @property isFavorite Boolean flag indicating if the quote is marked as a favorite.
 */
data class Quote(
    val quote: String,
    val author: String,
    var isFavorite: Boolean,
)
