package com.example.breakingbadquotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingbadquotes.model.Quote

@Entity(tableName = "favoriteQuotes")
data class DbQuote(
    @PrimaryKey
    val quote: String,
    val author: String,
    val isFavorite: Boolean,
)

fun DbQuote.asDomainQuote(): Quote {
    return Quote(
        quote,
        author,
        isFavorite,
    )
}

fun Quote.asDbQuote(): DbQuote {
    return DbQuote(
        quote = quote,
        author = author,
        isFavorite = isFavorite,
    )
}

fun List<DbQuote>.asDomainQuotes(): List<Quote> {
    var quoteList = this.map {
        Quote(it.quote, it.author, it.isFavorite)
    }
    return quoteList
}
