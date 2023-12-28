package com.example.breakingbadquotes.network

import retrofit2.http.GET

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuotes(): List<ApiQuote>
}