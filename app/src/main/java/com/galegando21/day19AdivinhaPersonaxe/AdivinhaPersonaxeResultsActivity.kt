package com.galegando21.day19AdivinhaPersonaxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaPersonaxeConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class AdivinhaPersonaxeResultsActivity : AppCompatActivity() {
    private lateinit var AdivinhaPersonaxeCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaPersonaxeResultsTv : TextView
    private lateinit var AdivinhaPersonaxeFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_personaxe_results)

        AdivinhaPersonaxeCorrectAnswersResultTv = findViewById(R.id.adivinha_personaxe_correct_answers_results_text_view)
        AdivinhaPersonaxeResultsTv = findViewById(R.id.adivinha_personaxe_result_tv)
        AdivinhaPersonaxeFinishButton = findViewById(R.id.adivinha_personaxe_finish_btn)

        setBanner(this, R.string.adivinha_o_personaxe)

        val score = intent.getIntExtra(AdivinhaPersonaxeConstants.ADIVINHA_PERSONAXE_SCORE, 0)
        if (score != 0) {
            AdivinhaPersonaxeCorrectAnswersResultTv.text = score.toString()
            AdivinhaPersonaxeResultsTv.text = "Necesitaches $score pistas para acertar o personaxe."
        } else {
            AdivinhaPersonaxeResultsTv.text = "Intentao de novo!"
        }

        AdivinhaPersonaxeFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        if (score != 0) {
            changeAdivinhaPersonaxeStatistics()
        }

        setOnBackPressed(this, AdivinhaPersonaxeInicioActivity::class.java)
    }

    private fun changeAdivinhaPersonaxeStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = Int.MAX_VALUE
        if (sharedPreferences.contains(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, Int.MAX_VALUE)
        }

        val score = intent.getIntExtra(AdivinhaPersonaxeConstants.ADIVINHA_PERSONAXE_SCORE, 0)
        if (score < maxScore) {
            editor.putInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("AdivinhaPersonaxeResultsActivity", "Max score: ${sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, Int.MAX_VALUE)}")

        updateCurrentStreak(this)
    }
}