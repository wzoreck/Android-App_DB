package com.example.app_db

import android.app.Application
import com.example.app_db.data.DirectorRepository
import com.example.app_db.data.DirectorRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DirectorApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {DirectorRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {DirectorRepository(database.directorDao())}
}