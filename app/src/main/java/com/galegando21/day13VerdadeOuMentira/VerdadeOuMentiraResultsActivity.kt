package com.galegando21.day13VerdadeOuMentira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.VerdadeOuMentiraConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class VerdadeOuMentiraResultsActivity : AppCompatActivity() {
    private lateinit var verdadeOuMentiraCorrectAnswersResultTv : TextView
    private lateinit var verdadeOuMentiraResultsTv : TextView
    private lateinit var verdadeOuMentiraFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_results)

        verdadeOuMentiraCorrectAnswersResultTv = findViewById(R.id.verdade_ou_mentira_correct_answers_results_text_view)
        verdadeOuMentiraResultsTv = findViewById(R.id.verdade_ou_mentira_result_tv)
        verdadeOuMentiraFinishButton = findViewById(R.id.verdade_ou_mentira_finish_btn)

        setBanner(this, R.string.verdadeOuMentira)

        val score = intent.getIntExtra(VerdadeOuMentiraConstants.SCORE, 0)
        verdadeOuMentiraCorrectAnswersResultTv.text = score.toString()
        verdadeOuMentiraResultsTv.text = "Acertaches un total de $score preguntas seguidas."

        verdadeOuMentiraFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeVerdadeOuMentiraStatistics()

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
    }
}