package com.galegando21.day13VerdadeOuMentira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class VerdadeOuMentiraInicioActivity : AppCompatActivity() {
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_inicio)

        setBanner(this, R.string.verdadeOuMentira)

        comezarButton = findViewById(R.id.start_btn_verdade_ou_mentira)

        comezarButton.setOnClickListener {
            Intent(this@VerdadeOuMentiraInicioActivity, VerdadeOuMentiraQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}