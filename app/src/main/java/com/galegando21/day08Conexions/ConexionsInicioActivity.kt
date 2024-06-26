package com.galegando21.day08Conexions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class ConexionsInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexions_inicio)

        setBanner(this, R.string.conexions)

        comezarButton = findViewById(R.id.start_btn_conexions)

        comezarButton.setOnClickListener {
            Intent(this@ConexionsInicioActivity, ConexionsGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}