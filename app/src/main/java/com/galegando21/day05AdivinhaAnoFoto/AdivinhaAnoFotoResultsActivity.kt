package com.galegando21.day05AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaAnoFotoConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class AdivinhaAnoFotoResultsActivity : AppCompatActivity() {
    private lateinit var AdivinhaAnoFotoCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaAnoFotoResultsTv : TextView
    private lateinit var AdivinhaAnoFotoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_results)

        AdivinhaAnoFotoCorrectAnswersResultTv = findViewById(R.id.adivinhaAnoFoto_correct_answers_results_text_view)
        AdivinhaAnoFotoResultsTv = findViewById(R.id.adivinhaAnoFoto_result_tv)
        AdivinhaAnoFotoFinishButton = findViewById(R.id.adivinhaAnoFoto_finish_btn)

        setBanner(this, R.string.adivinha_ano_foto)

        val score = intent.getIntExtra(AdivinhaAnoFotoConstants.SCORE, 0)
        AdivinhaAnoFotoCorrectAnswersResultTv.text = score.toString()
        AdivinhaAnoFotoResultsTv.text = "Conseguiches un total de $score puntos."

        AdivinhaAnoFotoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAdivinhaAnoFotoStatistics()

        setOnBackPressed(this, AdivinhaAnoFotoInicioActivity::class.java)
    }

    private fun changeAdivinhaAnoFotoStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra(AdivinhaAnoFotoConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("AdivinhaAnoFotoResultsActivity", "changeAdivinhaAnoFotoStatistics: ${sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0)}")

        updateCurrentStreak(this)
    }
}