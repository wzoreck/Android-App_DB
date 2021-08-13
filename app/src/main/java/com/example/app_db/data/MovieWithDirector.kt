package com.example.app_db.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.app_db.data.director.Director
import com.example.app_db.data.movie.Movie

data class MovieWithDirector(
    @Embedded val movie: Movie,
    @Relation(parentColumn = "directorId", entityColumn = "id")
    val director: Director
)
