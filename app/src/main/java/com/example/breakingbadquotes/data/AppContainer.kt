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

/**
 * Interface for the app's dependency container, which provides access to the repository.
 */
interface AppContainer {
    /**
     * A repository for handling quote data operations.
     */
    val quoteRepository: QuoteRepository
}

/**
 * The default implementation of [AppContainer] that sets up the application's dependencies.
 * It includes the initialization of network components, the Retrofit service, and the Room database.
 *
 * @property applicationContext The context of the application used to initialize various components.
 */
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

    private val quoteDatabase: QuoteDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            QuoteDb::class.java,
            "database-name",
        ).build()
    }

    /**
     * Provides a singleton instance of [QuoteRepository] that is lazily initialized.
     * It uses a [CachingQuotesRepository] to provide caching functionality for quotes.
     */
    override val quoteRepository: QuoteRepository by lazy {
        CachingQuotesRepository(quoteDatabase.quoteDao(), retrofitService)
    }
}
