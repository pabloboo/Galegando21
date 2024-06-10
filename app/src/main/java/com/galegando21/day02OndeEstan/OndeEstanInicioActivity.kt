package com.galegando21.day02OndeEstan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class OndeEstanInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onde_estan_inicio)

        setBanner(this, R.string.onde_estan)

        comezarButton = findViewById(R.id.start_btn_onde_estan)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        comezarButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            Intent(this@OndeEstanInicioActivity, OndeEstanGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    override fun onStop() {
        super.onStop()

        loadingProgressBar.visibility = View.GONE
    }
}