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
    suspend fun insert(item: DbQuote)

    @Delete
    suspend fun delete(item: DbQuote)

    @Query("SELECT * from favoriteQuotes")
    fun getAllItems(): Flow<List<DbQuote>>
}
