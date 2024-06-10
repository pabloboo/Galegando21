package com.galegando21.day19Aforcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AforcadoInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aforcado_inicio)

        setBanner(this, R.string.aforcado)

        comezarButton = findViewById(R.id.start_btn_aforcado)

        comezarButton.setOnClickListener {
            Intent(this@AforcadoInicioActivity, AforcadoGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}