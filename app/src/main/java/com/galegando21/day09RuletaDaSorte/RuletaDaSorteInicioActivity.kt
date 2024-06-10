package com.galegando21.day09RuletaDaSorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class RuletaDaSorteInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_inicio)

        setBanner(this, R.string.ruleta_da_sorte)

        comezarButton = findViewById(R.id.start_btn_ruleta_da_sorte)

        comezarButton.setOnClickListener {
            Intent(this@RuletaDaSorteInicioActivity, RuletaDaSorteGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}