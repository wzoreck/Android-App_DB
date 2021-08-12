package com.example.app_db.data.movie

import androidx.lifecycle.*
import com.example.app_db.data.ApplicationRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: ApplicationRepository): ViewModel() {

    val allMovies: LiveData<List<Movie>> = movieRepository.allMovies.asLiveData()

    fun insert(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch {
        movieRepository.updateMovie(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }

    fun deleteAll() = viewModelScope.launch {
        movieRepository.deleteAllMovies()
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