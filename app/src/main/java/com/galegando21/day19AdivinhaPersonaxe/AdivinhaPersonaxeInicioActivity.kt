package com.galegando21.day19AdivinhaPersonaxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AdivinhaPersonaxeInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_personaxe_inicio)

        comezarButton = findViewById(R.id.start_btn_adivinha_personaxe)

        setBanner(this, R.string.adivinha_o_personaxe)

        comezarButton.setOnClickListener {
            Intent(this@AdivinhaPersonaxeInicioActivity, AdivinhaPersonaxeGameActivity::class.java).also {
                 startActivity(it)
                 finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}