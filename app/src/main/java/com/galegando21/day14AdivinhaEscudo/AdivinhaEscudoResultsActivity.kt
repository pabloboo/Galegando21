package com.galegando21.day14AdivinhaEscudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaEscudoConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AdivinhaEscudoResultsActivity : AppCompatActivity() {
    private lateinit var AdivinhaEscudoCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaEscudoResultsTv : TextView
    private lateinit var AdivinhaEscudoRecordTv : TextView
    private lateinit var AdivinhaEscudoShareButton : Button
    private lateinit var AdivinhaEscudoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_escudo_results)

        AdivinhaEscudoCorrectAnswersResultTv = findViewById(R.id.adivinha_escudo_correct_answers_results_text_view)
        AdivinhaEscudoResultsTv = findViewById(R.id.adivinha_escudo_result_tv)
        AdivinhaEscudoRecordTv = findViewById(R.id.adivinha_escudo_record_tv)
        AdivinhaEscudoShareButton = findViewById(R.id.adivinha_escudo_share_btn)
        AdivinhaEscudoFinishButton = findViewById(R.id.adivinha_escudo_finish_btn)

        setBanner(this, R.string.adivinha_escudo)

        val score = intent.getIntExtra(AdivinhaEscudoConstants.SCORE, 0)
        AdivinhaEscudoCorrectAnswersResultTv.text = score.toString()
        AdivinhaEscudoResultsTv.text = "Acertaches un total de $score preguntas."

        AdivinhaEscudoShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        AdivinhaEscudoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAdivinhaEscudoStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0)
        AdivinhaEscudoRecordTv.text = "O teu récord é de $record preguntas."
        if (record < 10) {
            AdivinhaEscudoRecordTv.text = "O teu récord é de $record preguntas.\n\nAcerta todos os escudos para conseguir unha insignia!"
        }

        setOnBackPressed(this, AdivinhaEscudoInicioActivity::class.java)
    }

    private fun changeAdivinhaEscudoStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra(AdivinhaEscudoConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("AdivinhaEscudoResultsActivity", "changeAdivinhaEscudoStatistics: ${sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, score)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}