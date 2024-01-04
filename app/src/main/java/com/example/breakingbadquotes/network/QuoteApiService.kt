package com.example.breakingbadquotes.network

import retrofit2.http.GET

/**
 * Retrofit interface for making API calls to retrieve quotes.
 * This service defines the endpoints for the Breaking Bad Quotes API.
 */
interface QuoteApiService {
    /**
     * Retrieves a list of quotes from the API.
     * This endpoint is a GET request to the "quotes" path of the API.
     *
     * @return A list of [ApiQuote] objects containing the quote data from the API response.
     */
    @GET("quotes")
    suspend fun getQuotes(): List<ApiQuote>
}
