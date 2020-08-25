package com.vb.gamesreleases.presenters

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.ui.adapters.YourGamesRecycle
import com.vb.gamesreleases.ui.views.YourGamesView

class YourGamesPresenter(val yourGamesView: YourGamesView,
                         val yourGamesRecycler: YourGamesRecycle) {

    fun setAdapterToRecycler(recyclerView: RecyclerView){
        recyclerView.adapter = yourGamesRecycler
        val layoutManager = LinearLayoutManager(yourGamesView.contextProvider(),
            LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(yourGamesView.contextProvider()
        , layoutManager.orientation))
    }
}