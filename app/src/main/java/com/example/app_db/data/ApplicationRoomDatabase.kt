package com.example.app_db.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_db.data.director.Director
import com.example.app_db.data.movie.Movie
import com.example.app_db.data.director.DirectorDAO
import com.example.app_db.data.movie.MovieDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Director::class, Movie::class], version = 1)
abstract class ApplicationRoomDatabase : RoomDatabase() {

    abstract fun directorDao(): DirectorDAO
    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: ApplicationRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ApplicationRoomDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationRoomDatabase::class.java,
                    "database"
                ).fallbackToDestructiveMigration()
                .addCallback(DatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.directorDao(), database.movieDao())
                }
            }
        }

        suspend fun populateDatabase(directorDao: DirectorDAO, movieDao: MovieDAO) {
            directorDao.deleteAll()
            directorDao.insert(Director(0, "Quentin Tarantino"))
            directorDao.insert(Director(1, "Peter Jackson"))
            directorDao.insert(Director(2, "David Fincher"))
            directorDao.insert(Director(3, "George Lucas"))

            movieDao.insert(Movie(0, "Pulp Fiction"))
            movieDao.insert(Movie(1, "Kill Bill - Volume 1"))
            movieDao.insert(Movie(2, "Kill Bill - Volume 2"))
            movieDao.insert(Movie(3, "Senhor dos Anéis"))
            movieDao.insert(Movie(3, "Clube da Luta"))
            movieDao.insert(Movie(3, "Star Wars"))
        }
    }
    
}