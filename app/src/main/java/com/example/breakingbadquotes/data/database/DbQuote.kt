package com.example.breakingbadquotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingbadquotes.model.Quote

@Entity(tableName = "favoriteQuotes")
data class DbQuote(
    @PrimaryKey
    val quote: String,
    val author: String,
)

fun DbQuote.asDomainQuote(): Quote {
    return Quote(
        this.quote,
        this.author,
    )
}

fun Quote.asDbQuote(): DbQuote {
    return DbQuote(
        quote = this.quote,
        author = this.author,
    )
}

fun List<DbQuote>.asDomainQuotes(): List<Quote> {
    var quoteList = this.map {
        Quote(it.quote, it.author)
    }
    return quoteList
}
