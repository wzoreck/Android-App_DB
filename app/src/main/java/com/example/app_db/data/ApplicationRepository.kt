package com.example.app_db.data

import androidx.annotation.WorkerThread
import com.example.app_db.data.director.Director
import com.example.app_db.data.director.DirectorDAO
import com.example.app_db.data.movie.Movie
import com.example.app_db.data.movie.MovieDAO
import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val directorDao: DirectorDAO, private val movieDao: MovieDAO) {

    val allDirectors: Flow<List<Director>> = directorDao.getDirectors()
    val allMovies: Flow<List<Movie>> = movieDao.getMovies()
    val allDirectorsWithMovies: Flow<List<DirectorWithMovies>> = directorDao.getDirectorWithMovies()
    val allMoviesWithDirector: Flow<List<MovieWithDirector>> = movieDao.getMovieWithDirector()

    // Director
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDirector(director: Director) {
        directorDao.insert(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateDirector(director: Director) {
        directorDao.update(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteDirector(director: Director) {
        directorDao.delete(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllDirectors() {
        directorDao.deleteAll()
    }

    // Movie
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateMovie(movie: Movie) {
        movieDao.update(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllMovies() {
        movieDao.deleteAll()
    }
}