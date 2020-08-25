package com.vb.gamesreleases.data

import android.content.Context
import com.vb.gamesreleases.data.database.GamesDb
import com.vb.gamesreleases.data.network.RawgService

object RepositoryProvider {
    fun provideRepository(): Repository{
        return Repository(RawgService.createService())
    }
}