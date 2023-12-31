package com.example.breakingbadquotes.data

import android.content.Context
import androidx.room.Room
import com.example.breakingbadquotes.data.database.QuoteDb
import com.example.breakingbadquotes.network.NetworkConnectionInterceptor
import com.example.breakingbadquotes.network.QuoteApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val quoteRepository: QuoteRepository
}

class DefaultAppContainer(applicationContext: Context) : AppContainer {

    private val networkCheck = NetworkConnectionInterceptor(applicationContext)
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    private val baseUrl = "https://api.breakingbadquotes.xyz/v1/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val retrofitService: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }

    /*override val quoteRepository: QuoteRepository by lazy {
        ApiQuoteRepository(retrofitService)
    }*/

    private val quoteDatabase: QuoteDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            QuoteDb::class.java,
            "database-name",
        ).build()
    }

    override val quoteRepository: QuoteRepository by lazy {
//        ApiTasksRepository(retrofitService)
        CachingQuotesRepository(quoteDatabase.quoteDao(), retrofitService)
    }
}
