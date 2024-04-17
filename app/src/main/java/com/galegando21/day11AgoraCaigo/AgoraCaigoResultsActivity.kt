package com.galegando21.day11AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AgoraCaigoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

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

        setOnBackPressed(this, AgoraCaigoInicioActivity::class.java)
    }
}