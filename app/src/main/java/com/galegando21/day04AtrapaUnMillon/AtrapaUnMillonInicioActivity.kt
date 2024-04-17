package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapaUnMillonInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_inicio)

        setBanner(this, R.string.atrapa_un_millon)

        comezarButton = findViewById(R.id.start_btn_atrapame_un_millon)

        comezarButton.setOnClickListener {
            Intent(this@AtrapaUnMillonInicioActivity, AtrapaUnMillonQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)

    }
}