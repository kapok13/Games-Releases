package com.vb.gamesreleases.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vb.gamesreleases.data.network.models.upcomingreleases.Genre

@Entity
data class MyGamesEntities(
    val poster: String,
    val name: String,
    val ganres: List<Genre>,
    @ColumnInfo(name = "release")
    val releaseDate: String
    ) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}