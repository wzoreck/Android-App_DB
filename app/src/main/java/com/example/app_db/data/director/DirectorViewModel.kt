package com.example.app_db.data.director

import androidx.lifecycle.*
import com.example.app_db.data.ApplicationRepository
import com.example.app_db.data.DirectorWithMovies
import kotlinx.coroutines.launch

class DirectorViewModel(private val applicationRepository: ApplicationRepository): ViewModel() {

    val allDirectors: LiveData<List<Director>> = applicationRepository.allDirectors.asLiveData()
    val allDirectorsWithMovies: LiveData<List<DirectorWithMovies>> = applicationRepository.allDirectorsWithMovies.asLiveData()

    fun insert(director: Director) = viewModelScope.launch {
        applicationRepository.insertDirector(director)
    }

    fun update(director: Director) = viewModelScope.launch {
        applicationRepository.updateDirector(director)
    }

    fun delete(director: Director) = viewModelScope.launch {
        applicationRepository.deleteDirector(director)
    }

    fun deleteAll() = viewModelScope.launch {
        applicationRepository.deleteAllDirectors()
    }

    class DirectorViewModelFactory(private val directorRepository: ApplicationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DirectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DirectorViewModel(directorRepository) as T
            }
            throw IllegalAccessException("Unknown ViewModel class")
        }

    }
}