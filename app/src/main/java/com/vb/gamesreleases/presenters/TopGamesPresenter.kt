package com.vb.gamesreleases.presenters

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.data.network.models.upcomingreleases.Upcoming
import com.vb.gamesreleases.ui.adapters.TopGamesRecycler
import com.vb.gamesreleases.ui.views.TopGamesView

class TopGamesPresenter(val topGamesView: TopGamesView) {

    fun unpackUpcomingResultsForAdapter(upcoming: Upcoming, thisTopGamesRecycler: TopGamesRecycler){
        thisTopGamesRecycler.setTopGamesList(upcoming.results)
    }

    fun setAdapterToRecycler(recyclerView: RecyclerView, topGamesRecycler: TopGamesRecycler){
        recyclerView.adapter = topGamesRecycler
        val layoutManager = LinearLayoutManager(topGamesView.contextProvider(),
            LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(topGamesView.contextProvider(),
                layoutManager.orientation)
        )
    }
}