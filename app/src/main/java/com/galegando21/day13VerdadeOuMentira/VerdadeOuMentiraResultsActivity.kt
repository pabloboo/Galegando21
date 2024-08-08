package com.galegando21.day13VerdadeOuMentira

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
import com.galegando21.utils.VerdadeOuMentiraConstants
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.showSurvey
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class VerdadeOuMentiraResultsActivity : AppCompatActivity() {
    private lateinit var verdadeOuMentiraCorrectAnswersResultTv : TextView
    private lateinit var verdadeOuMentiraResultsTv : TextView
    private lateinit var verdadeOuMentiraRecordTv: TextView
    private lateinit var verdadeOuMentiraShareButton: Button
    private lateinit var verdadeOuMentiraFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_results)

        verdadeOuMentiraCorrectAnswersResultTv = findViewById(R.id.verdade_ou_mentira_correct_answers_results_text_view)
        verdadeOuMentiraResultsTv = findViewById(R.id.verdade_ou_mentira_result_tv)
        verdadeOuMentiraRecordTv = findViewById(R.id.verdade_ou_mentira_record_tv)
        verdadeOuMentiraShareButton = findViewById(R.id.verdade_ou_mentira_share_btn)
        verdadeOuMentiraFinishButton = findViewById(R.id.verdade_ou_mentira_finish_btn)

        setBanner(this, R.string.verdadeOuMentira)
        showSurvey(this, SharedPreferencesKeys.ENQUISA_VERDADE_OU_MENTIRA, findViewById(R.id.enquisaTextView))

        val score = intent.getIntExtra(VerdadeOuMentiraConstants.SCORE, 0)
        verdadeOuMentiraCorrectAnswersResultTv.text = score.toString()
        verdadeOuMentiraResultsTv.text = "Acertaches un total de $score preguntas seguidas."

        verdadeOuMentiraShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        verdadeOuMentiraFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeVerdadeOuMentiraStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0)
        verdadeOuMentiraRecordTv.text = "O teu récord é de $record preguntas."
        if (record < 30) {
            verdadeOuMentiraRecordTv.text = "O teu récord é de $record preguntas.\n\nResponde 30 preguntas seguidas para conseguir unha insignia!"
        }

        setOnBackPressed(this, VerdadeOuMentiraInicioActivity::class.java)
    }

    private fun changeVerdadeOuMentiraStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra(VerdadeOuMentiraConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("maxScore", sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0).toString())

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, score)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}