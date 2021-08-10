package com.example.app_db.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Director::class], version = 1)
abstract class DirectorRoomDatabase : RoomDatabase() {

    abstract fun directorDao(): DirectorDAO

    companion object {
        @Volatile
        private var INSTANCE: DirectorRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): DirectorRoomDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DirectorRoomDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}