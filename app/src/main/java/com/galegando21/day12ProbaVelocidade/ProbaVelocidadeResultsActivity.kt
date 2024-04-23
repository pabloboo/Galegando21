package com.galegando21.day12ProbaVelocidade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class ProbaVelocidadeResultsActivity : AppCompatActivity() {
    private lateinit var probaVelocidadeCorrectAnswersResultTv : TextView
    private lateinit var probaVelocidadeResultsTv : TextView
    private lateinit var probaVelocidadeFinishButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proba_velocidade_results)

        probaVelocidadeCorrectAnswersResultTv = findViewById(R.id.proba_velocidade_correct_answers_results_text_view)
        probaVelocidadeResultsTv = findViewById(R.id.proba_velocidade_result_tv)
        probaVelocidadeFinishButton = findViewById(R.id.proba_velocidade_finish_btn)

        setBanner(this, R.string.proba_velocidade)
        setOnBackPressed(this, MainActivity::class.java)

        val score = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_PROBA_VELOCIDADE, 0)
        probaVelocidadeCorrectAnswersResultTv.text = score.toString()
        probaVelocidadeResultsTv.text = "Tardaches un total de $score segundos."

        probaVelocidadeFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeProbaVelocidadeStatistics()
    }

    private fun changeProbaVelocidadeStatistics() {
        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var minTime = 0
        if (sharedPreferences.contains("proba_velocidade_min_time")) {
            minTime = sharedPreferences.getInt("proba_velocidade_min_time", 0)
        }

        val score = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_PROBA_VELOCIDADE, 0)
        if (minTime == 0 || score < minTime) {
            editor.putInt("proba_velocidade_min_time", score)
            editor.apply()
        }
        Log.d("ProbaVelocidadeResultsActivity", "minTime: ${sharedPreferences.getInt("proba_velocidade_min_time", 0)}")
    }
}