package com.galegando21.day18PalabrasEncadeadas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class PalabrasEncadeadasInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabras_encadeadas_inicio)

        setBanner(this, R.string.palabras_encadeadas)

        comezarButton = findViewById(R.id.start_btn_palabras_encadeadas)

        comezarButton.setOnClickListener {
            Intent(this@PalabrasEncadeadasInicioActivity, PalabrasEncadeadasGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}