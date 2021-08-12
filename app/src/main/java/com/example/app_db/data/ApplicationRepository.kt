package com.example.app_db.data

import androidx.annotation.WorkerThread
import com.example.app_db.data.director.Director
import com.example.app_db.data.director.DirectorDAO
import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val directorDao: DirectorDAO) {

    val allDirector: Flow<List<Director>> = directorDao.getDirectors()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(director: Director) {
        directorDao.insert(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(director: Director) {
        directorDao.update(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(director: Director) {
        directorDao.delete(director)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        directorDao.deleteAll()
    }
}