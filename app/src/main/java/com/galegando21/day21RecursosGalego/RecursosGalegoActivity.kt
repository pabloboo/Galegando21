package com.galegando21.day21RecursosGalego

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class RecursosGalegoActivity : AppCompatActivity() {
    private lateinit var spotifyIcon : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musica)

        spotifyIcon = findViewById(R.id.spotify_icon)

        setBanner(this, R.string.dias_co_galego_e_mais)

        spotifyIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0ArqOLd6zTZpVqtTSXDTZC?si=4pA47GITQ8af7kJu3thHVQ")
            startActivity(openURL)
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}