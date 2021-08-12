package com.example.app_db.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
public interface MovieDAO   {

    @Query ("SELECT * FROM directors_table")
    fun getMovies(): Flow<List<Movie>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query ("DELETE FROM movies_table")
    suspend fun deleteAll()
}