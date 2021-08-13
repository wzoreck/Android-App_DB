package com.example.app_db.data.movie

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "movies_table",
//    foreignKeys = [ForeignKey(
//        entity = Director::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("directorId"),
//        onDelete = ForeignKey.NO_ACTION
//    )]
)
@Parcelize
data class Movie (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val directorId: Int,
) : Parcelable{}