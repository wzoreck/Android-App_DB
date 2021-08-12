package com.example.app_db.data.director

import androidx.lifecycle.*
import com.example.app_db.data.ApplicationRepository
import kotlinx.coroutines.launch

class DirectorViewModel(private val directorRepository: ApplicationRepository): ViewModel() {

    val allDirectors: LiveData<List<Director>> = directorRepository.allDirectors.asLiveData()

    fun insert(director: Director) = viewModelScope.launch {
        directorRepository.insertDirector(director)
    }

    fun update(director: Director) = viewModelScope.launch {
        directorRepository.updateDirector(director)
    }

    fun delete(director: Director) = viewModelScope.launch {
        directorRepository.deleteDirector(director)
    }

    fun deleteAll() = viewModelScope.launch {
        directorRepository.deleteAllDirectors()
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