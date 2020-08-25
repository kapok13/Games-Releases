package com.vb.gamesreleases.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vb.gamesreleases.R
import com.vb.gamesreleases.data.RepositoryProvider
import com.vb.gamesreleases.data.database.entities.MyGamesEntities
import com.vb.gamesreleases.data.network.models.upcomingreleases.Genre
import com.vb.gamesreleases.data.network.models.upcomingreleases.Result
import com.vb.gamesreleases.ui.activities.GamePageActivity

class UpcomingReleasesRecycer(val context: Context)
    : RecyclerView.Adapter<UpcomingReleasesRecycer.ReleasesViewHolder>() {

    private lateinit var releasesList: List<Result>

    fun setReleases(list: List<Result>){
        releasesList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleasesViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recycler_item_releases, parent, false)
        return ReleasesViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (releasesList.isNotEmpty()){
            return releasesList.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: ReleasesViewHolder, position: Int) {
        var currentItem = releasesList.get(position)
        holder.name.setText(currentItem.name)
        holder.releaseDateText.setText(currentItem.released.toString())
        setGamePoster(currentItem, holder.gamePoster)
        setGenres(holder.layoutForGanres, currentItem)
        holder.relativeContainer.setOnClickListener{
            val intent = Intent(context, GamePageActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("label", currentItem.name)
            context?.startActivity(intent)
        }
        holder.releaseSaveButton.setOnClickListener({
            RepositoryProvider.provideRepository().insertGame(MyGamesEntities(
                currentItem.backgroundImage,
                currentItem.name,
                currentItem.genres,
                currentItem.released), context)
        })
    }

    inner class ReleasesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentView = itemView
        val name: TextView = parentView.findViewById(R.id.upcoming_name_text)
        val gamePoster: ImageView = parentView.findViewById(R.id.upcoming_pic)
        val releaseDateText: TextView = parentView.findViewById(R.id.release_date_cont)
        val layoutForGanres: LinearLayout = parentView.findViewById(R.id.ganres_container)
        val relativeContainer: RelativeLayout = parentView.findViewById(R.id.releases_main_container)
        val releaseSaveButton: Button = parentView.findViewById(R.id.insert_release_to_db_button)
    }

    fun setGenres(linarLayout: LinearLayout, result: Result){
        val genre = result.genres
        for (genres: Genre in genre){
            val genreText: TextView = TextView(context)
            genreText.setPadding(0, 0, 16, 0)
            genreText.setText(genres.name)
            genreText.isAllCaps = true
            linarLayout.addView(genreText)
        }
    }

    fun setGamePoster(result: Result, imageView: ImageView){
             Picasso.with(context).load(result.backgroundImage.toString())
                .resize(108, 108)
                .centerCrop()
                .into(imageView)

    }
}