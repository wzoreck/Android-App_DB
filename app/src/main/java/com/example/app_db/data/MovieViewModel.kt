package com.example.app_db.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    val allMovies: LiveData<List<Movie>> = movieRepository.allMovies.asLiveData()

    fun insert(movie: Movie) = viewModelScope.launch {
        movieRepository.insert(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch {
        movieRepository.update(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        movieRepository.delete(movie)
    }

    fun deleteAll() = viewModelScope.launch {
        movieRepository.deleteAll()
    }

    class MovieViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(movieRepository) as T
            }
            throw IllegalAccessException("Unknown ViewModel class")
        }
    }

}