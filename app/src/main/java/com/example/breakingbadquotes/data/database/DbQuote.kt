package com.example.breakingbadquotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingbadquotes.model.Quote

/**
 * Room entity representing a quote in the database.
 * It defines the schema for a single favorite quote saved in the app's database.
 *
 * @property quote The text of the quote, which acts as a unique identifier.
 * @property author The author of the quote.
 * @property isFavorite Indicates whether the quote is marked as a favorite.
 */
@Entity(tableName = "favoriteQuotes")
data class DbQuote(
    @PrimaryKey
    val quote: String,
    val author: String,
    val isFavorite: Boolean,
)

/**
 * Extension function to convert a [DbQuote] to a [Quote] domain model.
 *
 * @return A [Quote] domain model instance.
 */
fun DbQuote.asDomainQuote(): Quote {
    return Quote(
        quote,
        author,
        isFavorite,
    )
}

/**
 * Extension function to convert a [Quote] domain model to a [DbQuote] entity.
 *
 * @return A [DbQuote] entity instance.
 */
fun Quote.asDbQuote(): DbQuote {
    return DbQuote(
        quote = quote,
        author = author,
        isFavorite = isFavorite,
    )
}

/**
 * Extension function to convert a list of [DbQuote] entities to a list of [Quote] domain models.
 *
 * @return A list of [Quote] domain models.
 */
fun List<DbQuote>.asDomainQuotes(): List<Quote> {
    var quoteList = this.map {
        Quote(it.quote, it.author, it.isFavorite)
    }
    return quoteList
}
