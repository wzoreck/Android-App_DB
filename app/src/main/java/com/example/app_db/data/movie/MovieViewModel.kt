package com.example.app_db.data.movie

import androidx.lifecycle.*
import com.example.app_db.data.ApplicationRepository
import com.example.app_db.data.MovieWithDirector
import kotlinx.coroutines.launch

class MovieViewModel(private val applicationRepository: ApplicationRepository): ViewModel() {

    val allMovies: LiveData<List<Movie>> = applicationRepository.allMovies.asLiveData()
    val allMoviesWithDirector: LiveData<List<MovieWithDirector>> = applicationRepository.allMoviesWithDirector.asLiveData()

    fun insert(movie: Movie) = viewModelScope.launch {
        applicationRepository.insertMovie(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch {
        applicationRepository.updateMovie(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        applicationRepository.deleteMovie(movie)
    }

    fun deleteAll() = viewModelScope.launch {
        applicationRepository.deleteAllMovies()
    }

    class MovieViewModelFactory(private val movieRepository: ApplicationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(movieRepository) as T
            }
            throw IllegalAccessException("Unknown ViewModel class")
        }
    }

}