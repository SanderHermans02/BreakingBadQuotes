package com.example.breakingbadquotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the quotes database.
 * Defines the standard CRUD operations to be performed on the [DbQuote] entity.
 */
@Dao
interface QuoteDao {
    /**
     * Inserts a quote into the database. If the quote already exists, it replaces the existing entry.
     *
     * @param item The [DbQuote] object to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbQuote)

    /**
     * Deletes a quote from the database.
     *
     * @param item The [DbQuote] object to be deleted from the database.
     */
    @Delete
    suspend fun delete(item: DbQuote)

    /**
     * Retrieves all favorite quotes as a [Flow] which can be observed for changes.
     * This allows the UI to automatically update when the data changes.
     *
     * @return A [Flow] list of [DbQuote] objects from the database.
     */
    @Query("SELECT * from favoriteQuotes")
    fun getAllItems(): Flow<List<DbQuote>>
}
