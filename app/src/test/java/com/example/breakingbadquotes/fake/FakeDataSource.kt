package com.example.breakingbadquotes.fake

import com.example.breakingbadquotes.network.ApiQuote

object FakeDataSource {
    val quote1 = "I am the one who knocks"
    val quote2 = "Just let me die in peace"
    val author1 = "Walter White"
    val author2 = "Mike Ehrmantraut"

    val quotes = listOf(
        ApiQuote(quote1, author1, false),
        ApiQuote(quote2, author2, true),
    )
}
