package com.example.app_db.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "directors_table") // Para utilizar o ORM
@Parcelize
data class Director (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
) : Parcelable {
}