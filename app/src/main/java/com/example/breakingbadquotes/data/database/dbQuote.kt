package com.example.breakingbadquotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingbadquotes.model.Quote

@Entity(tableName = "favoriteQuotes")
data class dbQuote(
    @PrimaryKey
    val quote: String,
    val author: String,
)

fun dbQuote.asDomainQuote(): Quote {
    return Quote(
        this.quote,
        this.author,
    )
}

fun Quote.asDbQuote(): dbQuote {
    return dbQuote(
        quote = this.quote,
        author = this.author,
    )
}

fun List<dbQuote>.asDomainQuotes(): List<Quote> {
    var quoteList = this.map {
        Quote(it.quote, it.author)
    }
    return quoteList
}