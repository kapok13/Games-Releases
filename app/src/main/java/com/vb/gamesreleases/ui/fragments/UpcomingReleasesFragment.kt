package com.vb.gamesreleases.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.presenters.UpcomingReleasesPresenter
import com.vb.gamesreleases.ui.adapters.UpcomingReleasesRecycer
import com.vb.gamesreleases.ui.views.UpcomingReleasesView
import java.text.SimpleDateFormat
import java.util.*

class UpcomingReleasesFragment : Fragment(), UpcomingReleasesView {

    override fun contextProvider(): Context? {
        return activity?.applicationContext
    }

    private lateinit var upcomingReleasesPresenter: UpcomingReleasesPresenter
    private lateinit var upcomingReleasesRecycer: UpcomingReleasesRecycer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upcomingReleasesRecycer = UpcomingReleasesRecycer(activity!!.applicationContext)
        upcomingReleasesPresenter = UpcomingReleasesPresenter(this, upcomingReleasesRecycer)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_upcoming_releases, container, false)
        val repository = RepositoryProvider.provideRepository()
        upcomingReleasesPresenter.makeReleasesRequest(
            repository,
            upcomingReleasesPresenter.setDateForQuery(),
            "released",
            root.findViewById(R.id.releases_recycler),
            root.findViewById(R.id.lupcoming_loading_prgs))
        return root
    }


}