package com.galegando21.day20XogoPalabras

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

class XogoPalabrasResultsActivity : AppCompatActivity() {
    private lateinit var XogoPalabrasCorrectAnswersResultTv : TextView
    private lateinit var XogoPalabrasResultsTv : TextView
    private lateinit var XogoPalabrasFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xogo_palabras_results)

        XogoPalabrasCorrectAnswersResultTv = findViewById(R.id.xogo_palabras_correct_answers_results_text_view)
        XogoPalabrasResultsTv = findViewById(R.id.xogo_palabras_result_tv)
        XogoPalabrasFinishButton = findViewById(R.id.xogo_palabras_finish_btn)

        setBanner(this, R.string.xogo_de_palabras)

        val score = intent.getFloatExtra("PORCENTAXE_ACERTO", 0F)
        XogoPalabrasCorrectAnswersResultTv.text = score.toString()
        XogoPalabrasResultsTv.text = "Conseguiches o $score% da puntuación máxima."

        XogoPalabrasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeXogoPalabrasStatistics()

        setOnBackPressed(this, XogoPalabrasInicioActivity::class.java)
    }

    private fun changeXogoPalabrasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0F
        if (sharedPreferences.contains(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE)) {
            maxScore = sharedPreferences.getFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, 0F)
        }

        val score = intent.getFloatExtra("PORCENTAXE_ACERTO", 0F)
        if (score > maxScore) {
            editor.putFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("XogoPalabrasResults", "Puntuación máxima: ${sharedPreferences.getFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, 0F)}")

        updateCurrentStreak(this)
    }
}