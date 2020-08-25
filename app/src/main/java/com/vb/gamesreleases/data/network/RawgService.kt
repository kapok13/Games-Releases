package com.vb.gamesreleases.data.network


import com.vb.gamesreleases.data.network.models.upcomingreleases.Upcoming
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgService {

    @GET("api/games")
    fun getUpcomingGames(@Query("dates") date: String,
                         @Query("ordering") order: String): Observable<Upcoming>

    companion object Factory{
        fun createService(): RawgService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.rawg.io/")
                .build()

            return retrofit.create(RawgService::class.java)
        }
    }
}