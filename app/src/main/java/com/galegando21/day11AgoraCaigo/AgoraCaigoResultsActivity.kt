package com.galegando21.day11AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AgoraCaigoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class AgoraCaigoResultsActivity : AppCompatActivity() {
    private lateinit var AgoraCaigoCorrectAnswersResultTv : TextView
    private lateinit var AgoraCaigoResultsTv : TextView
    private lateinit var AgoraCaigoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_caigo_results)

        AgoraCaigoCorrectAnswersResultTv = findViewById(R.id.agora_caigo_correct_answers_results_text_view)
        AgoraCaigoResultsTv = findViewById(R.id.agora_caigo_result_tv)
        AgoraCaigoFinishButton = findViewById(R.id.agora_caigo_finish_btn)

        setBanner(this, R.string.agora_caigo)

        val score = intent.getIntExtra(AgoraCaigoConstants.SCORE, 0)
        AgoraCaigoCorrectAnswersResultTv.text = score.toString()
        AgoraCaigoResultsTv.text = "Acertaches un total de $score preguntas."

        AgoraCaigoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAgoraCaigoStatistics()

        setOnBackPressed(this, AgoraCaigoInicioActivity::class.java)
    }

    private fun changeAgoraCaigoStatistics() {
        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains("agora_caigo_max_score")) {
            maxScore = sharedPreferences.getInt("agora_caigo_max_score", 0)
        }

        val score = intent.getIntExtra(AgoraCaigoConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt("agora_caigo_max_score", score)
            editor.apply()
        }
        Log.d("AgoraCaigoResultsActivity", "maxScore: ${sharedPreferences.getInt("agora_caigo_max_score", 0)}")

        updateCurrentStreak(this)
    }
}