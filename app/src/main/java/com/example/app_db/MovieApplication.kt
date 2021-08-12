package com.example.app_db

import android.app.Application
import com.example.app_db.data.MovieRepository
import com.example.app_db.data.MovieRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MovieApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MovieRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { MovieRepository(database.movieDao()) }
}