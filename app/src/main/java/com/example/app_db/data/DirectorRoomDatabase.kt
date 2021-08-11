package com.example.app_db.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Director::class], version = 1)
abstract class DirectorRoomDatabase : RoomDatabase() {

    abstract fun directorDao(): DirectorDAO

    companion object {
        @Volatile
        private var INSTANCE: DirectorRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DirectorRoomDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DirectorRoomDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                .addCallback(DirectorDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DirectorDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.directorDao())
                }
            }
        }

        suspend fun populateDatabase(directorDao: DirectorDAO) {
            directorDao.deleteAll()
            directorDao.insert(Director(0, "Quentin Tarantino"))
        }
    }
    
}