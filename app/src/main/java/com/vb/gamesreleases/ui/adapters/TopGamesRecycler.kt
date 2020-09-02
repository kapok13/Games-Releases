package com.vb.gamesreleases.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.network.models.upcomingreleases.Result

class TopGamesRecycler(val context: Context)
    : RecyclerView.Adapter<TopGamesRecycler.TopGamesViewHolder>() {

    private lateinit var topGames: List<Result>

    fun setTopGamesList(topGamesList: List<Result>){
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

    fun setGamePoster(result: Result, imageView: ImageView){
        Picasso.with(context).load(result.backgroundImage.toString())
            .resize(108, 108)
            .centerCrop()
            .into(imageView)

    }

    override fun onBindViewHolder(holder: TopGamesViewHolder, position: Int) {
        var currentItem = topGames.get(position)
        holder.name.text = currentItem.name
        setGamePoster(currentItem, holder.pic)
    }

    inner class TopGamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.top_item_title_tv)
            val pic: ImageView = itemView.findViewById(R.id.top_image)
    }
}