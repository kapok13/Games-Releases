package com.vb.gamesreleases.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vb.gamesreleases.R
import com.vb.gamesreleases.presenters.TopGamesPresenter
import com.vb.gamesreleases.ui.adapters.TopGamesRecycler
import com.vb.gamesreleases.ui.views.TopGamesView

class TopGamesByGenreFragment : Fragment(), TopGamesView{

    private lateinit var topGamesPresenter: TopGamesPresenter
    private lateinit var topGamesRecycler: TopGamesRecycler

    override fun contextProvider(): Context {
        return activity!!.applicationContext
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topGamesRecycler = TopGamesRecycler(activity!!.applicationContext)
        topGamesPresenter = TopGamesPresenter(this, topGamesRecycler)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_top_games, container, false)
        return root
    }
}