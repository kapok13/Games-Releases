package com.vb.gamesreleases.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vb.gamesreleases.R

class TopGamesRecycler(val context: Context)
    : RecyclerView.Adapter<TopGamesRecycler.TopGamesViewHolder>() {

    private lateinit var topGames: List<Any>

    fun setTopGamesList(topGamesList: List<Any>){
        topGames = topGamesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGamesViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.recycler_item_top, parent, false)
        return TopGamesViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (topGames.isNotEmpty()){
            return topGames.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: TopGamesViewHolder, position: Int) {
        var currentItem = topGames.get(position)
        holder.name.text = currentItem.hashCode().toString()
    }

    inner class TopGamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.top_name_text)
    }
}