package com.example.breakingbadquotes

import android.app.Application
import com.example.breakingbadquotes.data.AppContainer
import com.example.breakingbadquotes.data.DefaultAppContainer

/**
 * The main Application class for the Breaking Bad Quotes application.
 */
class QuoteApplication : Application() {
    /**
     * The container that holds shared dependencies for the application.
     */
    lateinit var container: AppContainer

    /**
     * Initializes the application and the application container.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
