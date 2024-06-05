package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class PasagalegoInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonFacil: RadioButton
    private lateinit var radioButtonDificil: RadioButton
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_inicio)

        setBanner(this, R.string.pasagalego)

        radioGroup = findViewById(R.id.pasagalego_radio_group)
        radioButtonFacil = findViewById(R.id.pasagalego_radio_btn_facil)
        radioButtonDificil = findViewById(R.id.pasagalego_radio_btn_dificil)
        comezarButton = findViewById(R.id.start_btn_pasagalego)

        comezarButton.setOnClickListener {
            Intent(this@PasagalegoInicioActivity, PasagalegoQuestionActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonFacil.id -> it.putExtra("dificultade", "facil")
                    radioButtonDificil.id -> it.putExtra("dificultade", "dificil")
                }
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}