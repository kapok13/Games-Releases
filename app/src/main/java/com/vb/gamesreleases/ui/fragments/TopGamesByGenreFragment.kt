package com.vb.gamesreleases.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.Repository
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.presenters.TopGamesPresenter
import com.vb.gamesreleases.ui.adapters.TopGamesRecycler
import com.vb.gamesreleases.ui.views.TopGamesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopGamesByGenreFragment : Fragment(), TopGamesView{

    private lateinit var topGamesPresenter: TopGamesPresenter
    private val ordering: String = "-rating"

    override fun contextProvider(): Context {
        return activity!!.applicationContext
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topGamesPresenter = TopGamesPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_top_games, container, false)
        val repository = RepositoryProvider.provideRepository()
        getTopByGenre(repository, ordering, "rpg", root.findViewById(R.id.rpg_recycler))
        getTopByGenre(repository, ordering, "action", root.findViewById(R.id.action_recycler))
        getTopByGenre(repository, ordering, "strategy", root.findViewById(R.id.strategy_recycler))
        getTopByGenre(repository, ordering, "shooter", root.findViewById(R.id.shooter_recycler))
        return root
    }

    fun getTopByGenre(repository: Repository,ordering: String, genre: String, recyclerView: RecyclerView){
        repository.getTop(ordering, genre)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val currentAdapter = TopGamesRecycler(activity!!.applicationContext)
                topGamesPresenter.unpackUpcomingResultsForAdapter(it, currentAdapter)
                topGamesPresenter.setAdapterToRecycler(recyclerView, currentAdapter)
            })
    }
}