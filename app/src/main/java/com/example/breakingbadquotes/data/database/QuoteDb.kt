package com.example.breakingbadquotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database class for the application.
 * It serves as the main access point to the persisted quote data.
 *
 * @property entities A list of all entities that belong to the database. Here, [DbQuote] is the entity.
 * @property version The version of the database. If you change the schema, you'll need to increase the version number.
 */
@Database(entities = [DbQuote::class], version = 2)
abstract class QuoteDb : RoomDatabase() {

    /**
     * Provides access to the [QuoteDao] for database operations related to quotes.
     * @return The [QuoteDao] implementation.
     */
    abstract fun quoteDao(): QuoteDao
}
