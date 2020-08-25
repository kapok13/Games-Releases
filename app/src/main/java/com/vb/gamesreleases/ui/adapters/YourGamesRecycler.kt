package com.vb.gamesreleases.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.data.database.entities.MyGamesEntities
import com.vb.gamesreleases.data.network.models.upcomingreleases.Genre

class YourGamesRecycle(val context: Context) :
    RecyclerView.Adapter<YourGamesRecycle.YourGamesViwHolder>() {

    lateinit var myGamesList: List<MyGamesEntities>

    fun setMygamesList(games: List<MyGamesEntities>){
        myGamesList = games
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourGamesViwHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.recycler_item_your_games, parent, false)
        return YourGamesViwHolder(v)
    }

    override fun getItemCount(): Int {
        if (myGamesList.isNotEmpty()){
            return myGamesList.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: YourGamesViwHolder, position: Int) {
        var currentGame = myGamesList.get(position)
        holder.gameTitle.setText(currentGame.name)
        holder.releaseDate.setText(currentGame.releaseDate)
        setGamePoster(currentGame, holder.gamePic)
        setGenres(holder.ganresContainter, currentGame)
        holder.deleteButton.setOnClickListener({
            RepositoryProvider.provideRepository().deleteGame(currentGame, context)
        })
    }

    inner class YourGamesViwHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gamePic: ImageView = itemView.findViewById(R.id.your_pic)
            val gameTitle: TextView = itemView.findViewById(R.id.your_name_text)
            val releaseDate: TextView = itemView.findViewById(R.id.your_date_cont)
            val ganresContainter: LinearLayout = itemView.findViewById(R.id.your_container)
            val deleteButton: Button = itemView.findViewById(R.id.delete_game_button)
    }

    fun setGenres(linarLayout: LinearLayout, myGamesEntities: MyGamesEntities){
        val genre = myGamesEntities.ganres
        for (genres: Genre in genre){
            val genreText: TextView = TextView(context)
            genreText.setPadding(0, 0, 16, 0)
            genreText.setText(genres.name)
            genreText.isAllCaps = true
            linarLayout.addView(genreText)
        }
    }

    fun setGamePoster(myGamesEntities: MyGamesEntities, imageView: ImageView){
        Picasso.with(context).load(myGamesEntities.poster.toString())
            .resize(108, 108)
            .centerCrop()
            .into(imageView)

    }
}