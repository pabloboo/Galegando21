package com.galegando21.day02Musica

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R

class MusicaActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var spotifyIcon : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musica)

        spotifyIcon = findViewById(R.id.spotify_icon)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.musica))
        }.commit()

        spotifyIcon.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0ArqOLd6zTZpVqtTSXDTZC?si=4pA47GITQ8af7kJu3thHVQ")
            startActivity(openURL)
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@MusicaActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}