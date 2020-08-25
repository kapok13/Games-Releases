package com.vb.gamesreleases.ui.fragments

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.presenters.YourGamesPresenter
import com.vb.gamesreleases.ui.adapters.YourGamesRecycle
import com.vb.gamesreleases.ui.views.YourGamesView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YourGamesFragment : Fragment(), YourGamesView{

    override fun contextProvider(): Context? {
        return activity?.applicationContext
    }

    private lateinit var yourGamesRecycle: YourGamesRecycle
    private lateinit var yourGamesPresenter: YourGamesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yourGamesRecycle = YourGamesRecycle(activity!!.applicationContext)
        yourGamesPresenter = YourGamesPresenter(this, yourGamesRecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_your_games, container, false)
        getDataFromBd(root)
        return root
    }

    fun getDataFromBd(view: View){
        val rep = RepositoryProvider.provideRepository()
        Observable.fromCallable({
            yourGamesRecycle.setMygamesList(rep.getAllGames(activity!!.applicationContext))
            yourGamesPresenter.setAdapterToRecycler(recyclerView = view.findViewById(R.id.your_games_recycler))
            hideProgressBar(view)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun hideProgressBar(view: View){
        val prgs: ProgressBar = view.findViewById(R.id.lyour_games_loading_prgs)
        prgs.visibility = View.GONE
    }

}