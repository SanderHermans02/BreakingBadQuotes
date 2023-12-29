package com.example.breakingbadquotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbQuote)

    @Delete
    suspend fun delete(item: dbQuote)

    @Query("SELECT * from favoriteQuotes")
    fun getAllItems(): Flow<List<dbQuote>>
}