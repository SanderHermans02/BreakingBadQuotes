package com.example.breakingbadquotes.network

import retrofit2.http.GET

interface QuoteApiService {
    @GET("quote")
    suspend fun getQuote(): ApiQuote
}