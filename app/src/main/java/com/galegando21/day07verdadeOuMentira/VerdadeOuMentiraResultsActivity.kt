package com.galegando21.day07verdadeOuMentira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.VerdadeOuMentiraConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

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
        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains("verdade_ou_mentira_max_score")) {
            maxScore = sharedPreferences.getInt("verdade_ou_mentira_max_score", 0)
        }

        val score = intent.getIntExtra(VerdadeOuMentiraConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt("verdade_ou_mentira_max_score", score)
            editor.apply()
        }
        Log.d("maxScore", sharedPreferences.getInt("verdade_ou_mentira_max_score", 0).toString())
    }
}