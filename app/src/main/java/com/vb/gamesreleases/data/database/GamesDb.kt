package com.vb.gamesreleases.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vb.gamesreleases.data.database.daos.MyGamesDao
import com.vb.gamesreleases.data.database.entities.MyGamesEntities

@Database(entities = [MyGamesEntities::class], version = 1)
abstract class GamesDb : RoomDatabase() {
    abstract fun myGamesDao(): MyGamesDao
    companion object{
        var INSTANCE: GamesDb? = null
        fun getGamesDb(context: Context): GamesDb?{
            if (INSTANCE == null){
                synchronized(GamesDb::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, GamesDb::class.java, "gamesDb")
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyDb(){
            INSTANCE = null
        }
    }
}