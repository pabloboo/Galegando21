package com.galegando21.day11AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapameSePodesInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_inicio)

        setBanner(this, R.string.atrapame_se_podes)

        comezarButton = findViewById(R.id.start_btn_atrapame_se_podes)

        comezarButton.setOnClickListener {
            Intent(this@AtrapameSePodesInicioActivity, AtrapameSePodesQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}