package com.galegando21.day18Anagramas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AnagramasInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_inicio)

        comezarButton = findViewById(R.id.start_btn_anagramas)

        setBanner(this, R.string.anagramas)

        comezarButton.setOnClickListener {
            Intent(this@AnagramasInicioActivity, AnagramasGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}