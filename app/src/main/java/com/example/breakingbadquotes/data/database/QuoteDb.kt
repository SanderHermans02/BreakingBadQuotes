package com.example.breakingbadquotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbQuote::class], version = 2)
abstract class QuoteDb : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}
