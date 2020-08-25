package com.vb.gamesreleases.presenters

import android.icu.util.Calendar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.data.Repository
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.data.network.models.upcomingreleases.Upcoming
import com.vb.gamesreleases.ui.adapters.UpcomingReleasesRecycer
import com.vb.gamesreleases.ui.views.UpcomingReleasesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class UpcomingReleasesPresenter(val upcomingReleasesView: UpcomingReleasesView,
                                val upcomingReleasesRecycer: UpcomingReleasesRecycer) {

    fun makeReleasesRequest(repository: Repository, date: String, ordering: String, recyclerView: RecyclerView,
                            progressBar: ProgressBar){

        repository.getUpcoming(date, ordering)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result ->
                unpackUpcomingResultsForAdapter(result)
                setAdapterToRecycler(recyclerView)
                progressBar.visibility = View.GONE
                Log.d("RequestResult", result.count.toString())
            }, { error -> error.printStackTrace()})
    }

    fun unpackUpcomingResultsForAdapter(upcoming: Upcoming){
        upcomingReleasesRecycer.setReleases(upcoming.results)
    }

    fun setDateForQuery(): String{
        val calendar: java.util.Calendar = java.util.Calendar.getInstance()
        calendar.time = Date()
        val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(Date())
        val currentMonth: String = SimpleDateFormat("MM", Locale.getDefault()).format(Date())
        if (currentMonth.equals("12")){
            calendar.add(java.util.Calendar.YEAR, 1)
            calendar.set(java.util.Calendar.MONTH, 1)
            val finalDate = "$currentDate+,+${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(calendar.time)}"
            return finalDate
        }
        calendar.add(java.util.Calendar.MONTH, 1)
        val finDate: String = "$currentDate,${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(calendar.time)}"
        Log.d("DATE", finDate)
        return finDate
    }

    fun setAdapterToRecycler(recyclerView: RecyclerView){
        recyclerView.adapter = upcomingReleasesRecycer
        val layoutManager = LinearLayoutManager(upcomingReleasesView.contextProvider(),
            LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(upcomingReleasesView.contextProvider(),
            layoutManager.orientation))
    }

}