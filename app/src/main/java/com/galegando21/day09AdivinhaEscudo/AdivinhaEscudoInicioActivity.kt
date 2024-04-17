package com.galegando21.day09AdivinhaEscudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AdivinhaEscudoInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_escudo_inicio)

        setBanner(this, R.string.adivinha_escudo)

        comezarButton = findViewById(R.id.start_btn_adivinha_escudo)

        comezarButton.setOnClickListener {
            Intent(this@AdivinhaEscudoInicioActivity, AdivinhaEscudoQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}