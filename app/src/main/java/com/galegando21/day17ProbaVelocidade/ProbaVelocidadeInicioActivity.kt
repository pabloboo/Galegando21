package com.galegando21.day17ProbaVelocidade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class ProbaVelocidadeInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proba_velocidade_inicio)

        setBanner(this, R.string.proba_velocidade)

        comezarButton = findViewById(R.id.start_btn_proba_velocidade)

        comezarButton.setOnClickListener {
            Intent(this@ProbaVelocidadeInicioActivity, ProbaVelocidadeGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}