package com.example.app_db.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class DirectorViewModel(private val directorRepository: DirectorRepository): ViewModel() {

    val allDirectors: LiveData<List<Director>> = directorRepository.allDirector.asLiveData()

    fun insert(director: Director) = viewModelScope.launch {
        directorRepository.insert(director)
    }

    fun update(director: Director) = viewModelScope.launch {
        directorRepository.update(director)
    }

    fun delete(director: Director) = viewModelScope.launch {
        directorRepository.delete(director)
    }

    fun deleteAll() = viewModelScope.launch {
        directorRepository.deleteAll()
    }

    class DirectorViewModelFactory(private val directorRepository: DirectorRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DirectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DirectorViewModel(directorRepository) as T
            }
            throw IllegalAccessException("Unknown ViewModel class")
        }

    }
}