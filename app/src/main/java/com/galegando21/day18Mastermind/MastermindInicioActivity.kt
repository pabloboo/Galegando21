package com.galegando21.day18Mastermind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class MastermindInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mastermind_inicio)

        setBanner(this, R.string.mastermind)

        comezarButton = findViewById(R.id.start_btn_mastermind)

        comezarButton.setOnClickListener {
            Intent(this@MastermindInicioActivity, MastermindGameActivity::class.java).also {
                updateCurrentStreak(this)
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}