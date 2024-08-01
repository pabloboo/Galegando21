package com.galegando21.day15Wordle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import java.util.Calendar

class WordleInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonDiario: RadioButton
    private lateinit var radioButtonInfinito: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordle_inicio)

        setBanner(this, R.string.wordle)

        radioGroup = findViewById(R.id.wordle_radio_group)
        radioButtonDiario = findViewById(R.id.wordle_radio_btn_diario)
        radioButtonInfinito = findViewById(R.id.wordle_radio_btn_infinito)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_wordle)
        comezarButton = findViewById(R.id.start_btn_wordle)

        comezarButton.setOnClickListener {
            Intent(this@WordleInicioActivity, WordleGameActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonDiario.id -> {
                        if (!checkWordleDiaryPlayed()) return@setOnClickListener
                        it.putExtra("modo", "diario")
                    }
                    radioButtonInfinito.id -> it.putExtra("modo", "infinito")
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonDiario.id -> {
                    explicacionModoXogoTextView.text = "Acerta a palabra diaria."
                }
                radioButtonInfinito.id -> {
                    explicacionModoXogoTextView.text = "Intenta conseguir a maior racha de palabras acertadas."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun checkWordleDiaryPlayed(): Boolean {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.GAMES_STATE, MODE_PRIVATE)

        if (sharedPreferences.getInt(SharedPreferencesKeys.WORDLE_DIARY_LAST_DAY_PLAYED, -1) != currentDay) {
            sharedPreferences.edit().putInt(SharedPreferencesKeys.WORDLE_DIARY_LAST_DAY_PLAYED, currentDay).apply()
            return true
        } else {
            Toast.makeText(this, "Xa xogaches hoxe. Espera ata mañán para xogar de novo.", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}