package com.galegando21.day21ExplosionDePalabras

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

class ExplosionPalabrasResultsActivity : AppCompatActivity() {
    private lateinit var ExplosionPalabrasCorrectAnswersResultTv : TextView
    private lateinit var ExplosionPalabrasResultsTv : TextView
    private lateinit var ExplosionPalabrasFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosion_palabras_results)

        ExplosionPalabrasCorrectAnswersResultTv = findViewById(R.id.explosion_palabras_correct_answers_results_text_view)
        ExplosionPalabrasResultsTv = findViewById(R.id.explosion_palabras_result_tv)
        ExplosionPalabrasFinishButton = findViewById(R.id.explosion_palabras_finish_btn)

        setBanner(this, R.string.explosion_de_palabras)

        val score = intent.getIntExtra("PUNTOS_EXPLOSION_PALABRAS", 0)
        ExplosionPalabrasCorrectAnswersResultTv.text = score.toString()
        ExplosionPalabrasResultsTv.text = "Conseguiches $score puntos."

        ExplosionPalabrasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeExplosionPalabrasStatistics()

        setOnBackPressed(this, ExplosionPalabrasInicioActivity::class.java)
    }

    private fun changeExplosionPalabrasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra("PUNTOS_EXPLOSION_PALABRAS", 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("ExplosionPalabrasResultsActivity", "Puntuación máxima ${sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE, 0)}")

        updateCurrentStreak(this)
    }
}