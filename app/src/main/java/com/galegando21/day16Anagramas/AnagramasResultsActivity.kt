package com.galegando21.day16Anagramas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AnagramasResultsActivity : AppCompatActivity() {
    private lateinit var AnagramasCorrectAnswersResultTv : TextView
    private lateinit var AnagramasResultsTv : TextView
    private lateinit var AnagramasFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_results)

        AnagramasCorrectAnswersResultTv = findViewById(R.id.anagramas_correct_answers_results_text_view)
        AnagramasResultsTv = findViewById(R.id.anagramas_result_tv)
        AnagramasFinishButton = findViewById(R.id.anagramas_finish_btn)

        setBanner(this, R.string.anagramas)

        val score = intent.getIntExtra("ANAGRAMAS_SCORE", 0)
        AnagramasCorrectAnswersResultTv.text = score.toString()
        AnagramasResultsTv.text = "Acertaches un total de $score anagramas seguidos."

        AnagramasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAnagramasStatistics()

        setOnBackPressed(this, AnagramasInicioActivity::class.java)
    }

    private fun changeAnagramasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val modo = intent.getStringExtra("modo") ?: "facil"
        val maxScoreKey =
            when (modo) {
                "facil" -> SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY
                "dificil" -> SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT
                else -> SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY
            }

        var maxScore = 0
        if (sharedPreferences.contains(maxScoreKey)) {
            maxScore = sharedPreferences.getInt(maxScoreKey, 0)
        }

        val score = intent.getIntExtra("ANAGRAMAS_SCORE", 0)
        if (score > maxScore) {
            editor.putInt(maxScoreKey, score)
            editor.apply()
        }
        Log.d("AnagramasResultsActivity", "Max score: ${sharedPreferences.getInt(maxScoreKey, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, score*10)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}