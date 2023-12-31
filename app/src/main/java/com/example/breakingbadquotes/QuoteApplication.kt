package com.example.breakingbadquotes

import android.app.Application
import com.example.breakingbadquotes.data.AppContainer
import com.example.breakingbadquotes.data.DefaultAppContainer

class QuoteApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
