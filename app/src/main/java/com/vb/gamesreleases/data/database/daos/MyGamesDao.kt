package com.vb.gamesreleases.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vb.gamesreleases.data.database.entities.MyGamesEntities

@Dao
interface MyGamesDao {

    @Query("SELECT * FROM MyGamesEntities")
    fun getGames(): List<MyGamesEntities>

    @Delete
    fun deleteGame(game: MyGamesEntities)

    @Insert
    fun insertGame(game: MyGamesEntities)
}