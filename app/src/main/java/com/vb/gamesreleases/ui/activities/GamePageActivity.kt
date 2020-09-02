package com.vb.gamesreleases.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vb.gamesreleases.R
import com.vb.gamesreleases.ui.views.GamePageView

class GamePageActivity : AppCompatActivity(), GamePageView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        actionBar?.title = getExtra()
    }

    fun getExtra(): String?{
        val label = intent.extras?.getString("label") ?: resources.getString(R.string.app_name)
        return label
    }
}