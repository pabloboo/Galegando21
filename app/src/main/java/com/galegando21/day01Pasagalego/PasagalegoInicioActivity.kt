package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class PasagalegoInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_inicio)

        setBanner(this, R.string.pasagalego)

        radioGroup = findViewById(R.id.pasagalego_radio_group)
        comezarButton = findViewById(R.id.start_btn_pasagalego)

        comezarButton.setOnClickListener {
            Intent(this@PasagalegoInicioActivity, PasagalegoQuestionActivity::class.java).also {
                if (radioGroup.checkedRadioButtonId == 2131231562) {
                    it.putExtra("dificultade", "facil")
                } else {
                    it.putExtra("dificultade", "dificil")
                }
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}