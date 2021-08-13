package com.example.app_db.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.app_db.data.director.Director
import com.example.app_db.data.movie.Movie

data class DirectorWithMovies (
    @Embedded val director: Director,
    @Relation(parentColumn = "id", entityColumn = "directorId")
    val movies: List<Movie>
)