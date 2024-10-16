package com.galegando21.day02OndeEstan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.showSurvey
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class OndeEstanResultsActivity : AppCompatActivity() {
    private lateinit var ondeEstanCorrectAnswersResultTv : TextView
    private lateinit var ondeEstanResultsTv : TextView
    private lateinit var ondeEstanRecordTv: TextView
    private lateinit var ondeEstanShareButton: TextView
    private lateinit var ondeEstanFinishButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onde_estan_results)

        ondeEstanCorrectAnswersResultTv = findViewById(R.id.onde_estan_correct_answers_results_text_view)
        ondeEstanResultsTv = findViewById(R.id.onde_estan_result_tv)
        ondeEstanRecordTv = findViewById(R.id.onde_estan_record_tv)
        ondeEstanShareButton = findViewById(R.id.onde_estan_share_btn)
        ondeEstanFinishButton = findViewById(R.id.onde_estan_finish_btn)

        setBanner(this, R.string.onde_estan)
        showSurvey(this, SharedPreferencesKeys.ENQUISA_ONDE_ESTAN, findViewById(R.id.enquisaTextView))
        setOnBackPressed(this, OndeEstanInicioActivity::class.java)

        val score = intent.getIntExtra("SCORE_ONDE_ESTAN", 0)
        if (score == 0) {
            ondeEstanResultsTv.text = "Non conseguiches completar o panel, intentao de novo."
        } else {
            ondeEstanCorrectAnswersResultTv.text = score.toString()
            ondeEstanResultsTv.text = "Tardaches un total de $score segundos."
        }

        ondeEstanFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        ondeEstanShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        changeOndeEstanStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0)
        ondeEstanRecordTv.text = "O teu récord é de $record segundos"
        if (record > 20) {
            ondeEstanRecordTv.text = "O teu récord é de $record segundos.\n\nSuperao en menos de 20 segundos para conseguir unha insignia!"
        }
    }

    private fun changeOndeEstanStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var minTime = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME)) {
            minTime = sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0)
        }

        val score = intent.getIntExtra("SCORE_ONDE_ESTAN", 0)
        if (score != 0) {
            if (minTime == 0 || score < minTime) {
                editor.putInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, score)
                editor.apply()
            }
        }
        Log.d("OndeEstanResultsActivity", "minTime: ${sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, 90-score)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia.", Toast.LENGTH_SHORT).show()
    }
}