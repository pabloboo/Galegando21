package com.galegando21.day20DebuxaEAdivinha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class DebuxaEAdivinhaInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debuxa_eadivinha_inicio)

        setBanner(this, R.string.debuxa_e_adivinha)

        comezarButton = findViewById(R.id.start_btn_debuxa_e_adivinha)

        comezarButton.setOnClickListener {
            Intent(this@DebuxaEAdivinhaInicioActivity, DebuxaEAdivinhaGameActivity::class.java).also {
                updateCurrentStreak(this)
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}