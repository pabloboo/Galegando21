package com.galegando21.day12AdivinhaPersonaxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaPersonaxeConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AdivinhaPersonaxeResultsActivity : AppCompatActivity() {
    private lateinit var AdivinhaPersonaxeCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaPersonaxeResultsTv : TextView
    private lateinit var AdivinhaPersonaxeRecordTv : TextView
    private lateinit var AdivinhaPersonaxeShareButton : Button
    private lateinit var AdivinhaPersonaxeFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_personaxe_results)

        AdivinhaPersonaxeCorrectAnswersResultTv = findViewById(R.id.adivinha_personaxe_correct_answers_results_text_view)
        AdivinhaPersonaxeResultsTv = findViewById(R.id.adivinha_personaxe_result_tv)
        AdivinhaPersonaxeRecordTv = findViewById(R.id.adivinha_personaxe_record_tv)
        AdivinhaPersonaxeShareButton = findViewById(R.id.adivinha_personaxe_share_btn)
        AdivinhaPersonaxeFinishButton = findViewById(R.id.adivinha_personaxe_finish_btn)

        setBanner(this, R.string.adivinha_o_personaxe)

        val score = intent.getIntExtra(AdivinhaPersonaxeConstants.ADIVINHA_PERSONAXE_SCORE, 0)
        if (score != 0) {
            AdivinhaPersonaxeCorrectAnswersResultTv.text = score.toString()
            AdivinhaPersonaxeResultsTv.text = "Necesitaches $score pistas para acertar o personaxe."
        } else {
            AdivinhaPersonaxeResultsTv.text = "Intentao de novo!"
        }

        AdivinhaPersonaxeShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
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

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, Int.MAX_VALUE)
        if (record != Int.MAX_VALUE) {
            AdivinhaPersonaxeRecordTv.text = "No teu récord necesitaches $record pistas."
            if (record > 2) {
                AdivinhaPersonaxeRecordTv.text = "No teu récord necesitaches $record pistas.\n\nUsa só 2 pistas para obter unha insignia!"
            }
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

        val experience = updateUserExperience(this, 30)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}