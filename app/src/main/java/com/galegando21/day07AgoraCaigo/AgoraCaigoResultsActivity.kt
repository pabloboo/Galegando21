package com.galegando21.day07AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AgoraCaigoConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.showSurvey
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AgoraCaigoResultsActivity : AppCompatActivity() {
    private lateinit var AgoraCaigoCorrectAnswersResultTv : TextView
    private lateinit var AgoraCaigoResultsTv : TextView
    private lateinit var AgoraCaigoRecordTv : TextView
    private lateinit var AgoraCaigoShareButton : Button
    private lateinit var AgoraCaigoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_caigo_results)

        AgoraCaigoCorrectAnswersResultTv = findViewById(R.id.agora_caigo_correct_answers_results_text_view)
        AgoraCaigoResultsTv = findViewById(R.id.agora_caigo_result_tv)
        AgoraCaigoRecordTv = findViewById(R.id.agora_caigo_record_tv)
        AgoraCaigoShareButton = findViewById(R.id.agora_caigo_share_btn)
        AgoraCaigoFinishButton = findViewById(R.id.agora_caigo_finish_btn)

        setBanner(this, R.string.agora_caigo)
        showSurvey(this, SharedPreferencesKeys.ENQUISA_AGORA_CAIGO, findViewById(R.id.enquisaTextView))

        val score = intent.getIntExtra(AgoraCaigoConstants.SCORE, 0)
        AgoraCaigoCorrectAnswersResultTv.text = score.toString()
        AgoraCaigoResultsTv.text = "Acertaches un total de $score preguntas."

        AgoraCaigoShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        AgoraCaigoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAgoraCaigoStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0)
        AgoraCaigoRecordTv.text = "O teu récord é de $record preguntas"
        if (record < 50) {
            AgoraCaigoRecordTv.text = "O teu récord é de $record preguntas.\n\nAcerta 50 preguntas para conseguir unha insignia!"
        }

        setOnBackPressed(this, AgoraCaigoInicioActivity::class.java)
    }

    private fun changeAgoraCaigoStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra(AgoraCaigoConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("AgoraCaigoResultsActivity", "maxScore: ${sharedPreferences.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, score)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}