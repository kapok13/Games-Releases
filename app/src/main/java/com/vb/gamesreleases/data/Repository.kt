package com.vb.gamesreleases.data

import android.content.Context
import com.vb.gamesreleases.data.database.GamesDb
import com.vb.gamesreleases.data.database.entities.MyGamesEntities
import com.vb.gamesreleases.data.network.RawgService
import com.vb.gamesreleases.data.network.models.upcomingreleases.Upcoming
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository(val rawgService: RawgService) {


    fun getUpcoming(date: String, ordering: String): Observable<Upcoming>{
        return rawgService.getUpcomingGames(date, ordering)
    }

    fun getAllGames(context: Context): List<MyGamesEntities>{
        return GamesDb.getGamesDb(context)!!.myGamesDao().getGames()
    }

    fun insertGame(myGamesEntities: MyGamesEntities, context: Context){
       GamesDb.getGamesDb(context)!!.myGamesDao().insertGame(myGamesEntities)
    }

    fun deleteGame(myGamesEntities: MyGamesEntities, context: Context){
       GamesDb.getGamesDb(context)!!.myGamesDao().deleteGame(myGamesEntities)
    }

}