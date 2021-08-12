package com.example.app_db.data


import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ApplicationRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ApplicationRepository(database.directorDao(), database.movieDao()) }

}