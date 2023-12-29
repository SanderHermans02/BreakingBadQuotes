package com.example.breakingbadquotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [dbQuote::class], version = 2, exportSchema = false)
abstract class QuoteDb : RoomDatabase(){

        abstract fun quoteDao(): QuoteDao

        companion object {
            @Volatile
            private var Instance: QuoteDb? = null

            fun getDatabase(context: Context): QuoteDb {
                // if the Instance is not null, return it, otherwise create a new database instance.
                return Instance ?: synchronized(this) {
                    androidx.room.Room.databaseBuilder(context, QuoteDb::class.java, "quote_database")
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { Instance = it }
                }
            }
        }
}