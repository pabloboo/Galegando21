package com.galegando21.day16OndeEstan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class OndeEstanInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onde_estan_inicio)

        setBanner(this, R.string.onde_estan)

        comezarButton = findViewById(R.id.start_btn_onde_estan)

        comezarButton.setOnClickListener {
            Intent(this@OndeEstanInicioActivity, OndeEstanGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}