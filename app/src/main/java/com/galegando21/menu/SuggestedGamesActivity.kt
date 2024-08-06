package com.galegando21.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class SuggestedGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggested_games)

        setBanner(this, R.string.xogos_suxeridos)

        setOnBackPressed(this, MainActivity::class.java)
    }
}