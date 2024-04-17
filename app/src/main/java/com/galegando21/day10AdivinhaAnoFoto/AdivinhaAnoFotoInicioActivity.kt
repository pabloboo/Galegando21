package com.galegando21.day10AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AdivinhaAnoFotoInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_inicio)

        setBanner(this, R.string.adivinha_ano_foto)

        comezarButton = findViewById(R.id.start_btn_adivinhaAnoFoto)

        comezarButton.setOnClickListener {
            Intent(this@AdivinhaAnoFotoInicioActivity, AdivinhaAnoFotoGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}