package com.example.app_db.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDAO) {
    
    val allMovies: Flow<List<Movie>> = movieDao.getMovies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(movie: Movie) {
        movieDao.update(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(movie: Movie) {
        movieDao.delete(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        movieDao.deleteAll()
    }
}