package com.example.app_db.data.director

import androidx.room.*
import com.example.app_db.data.DirectorWithMovies
import kotlinx.coroutines.flow.Flow

@Dao
public interface DirectorDAO {

    @Query ("SELECT * FROM directors_table")
    fun getDirectors(): Flow<List<Director>> // Com o flow toda modificacao no banco de dados vai vir para ele

    @Transaction
    @Query ("SELECT * FROM directors_table")
    fun getDirectorWithMovies(): Flow<List<DirectorWithMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(director: Director)

    @Update
    suspend fun update(director: Director)

    @Delete
    suspend fun delete(director: Director)

    @Query ("DELETE FROM directors_table")
    suspend fun deleteAll()
}