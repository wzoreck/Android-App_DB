package com.example.app_db.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.example.app_db.data.director.Director
import com.example.app_db.data.movie.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class DirectorWithMovies (
    @Embedded val director: Director,
    @Relation(parentColumn = "id", entityColumn = "directorId")
    val movies: List<Movie>
): Parcelable{}