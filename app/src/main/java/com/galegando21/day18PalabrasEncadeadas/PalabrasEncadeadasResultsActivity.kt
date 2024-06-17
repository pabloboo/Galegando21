package com.galegando21.day18PalabrasEncadeadas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class PalabrasEncadeadasResultsActivity : AppCompatActivity() {
    private lateinit var PalabrasEncadeadasCorrectAnswersResultTv : TextView
    private lateinit var PalabrasEncadeadasResultsTv : TextView
    private lateinit var PalabrasEncadeadasFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabras_encadeadas_results)

        PalabrasEncadeadasCorrectAnswersResultTv = findViewById(R.id.palabras_encadeadas_correct_answers_results_text_view)
        PalabrasEncadeadasResultsTv = findViewById(R.id.palabras_encadeadas_result_tv)
        PalabrasEncadeadasFinishButton = findViewById(R.id.palabras_encadeadas_finish_btn)

        setBanner(this, R.string.palabras_encadeadas)

        val score = intent.getIntExtra("PALABRAS_ENCADEADAS_SCORE", 0)
        PalabrasEncadeadasCorrectAnswersResultTv.text = score.toString()
        PalabrasEncadeadasResultsTv.text = "Encadeaches $score palabras."

        PalabrasEncadeadasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changePalabrasEncadeadasStatistics()

        setOnBackPressed(this, PalabrasEncadeadasInicioActivity::class.java)
    }

    private fun changePalabrasEncadeadasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra("PALABRAS_ENCADEADAS_SCORE", 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("PalabrasEncadeadasResultsActivity", "maxScore: ${sharedPreferences.getInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, 0)}")

        updateCurrentStreak(this)
    }
}