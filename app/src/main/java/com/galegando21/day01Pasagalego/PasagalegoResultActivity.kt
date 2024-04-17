package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.PasagalegoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class PasagalegoResultActivity : AppCompatActivity() {
    private lateinit var correctAnswersTv : TextView
    private lateinit var timePasagalegoTv : TextView
    private lateinit var errorAnswersTv : TextView
    private lateinit var pasagalegoFinishButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_result)

        correctAnswersTv = findViewById(R.id.correct_answers_result_tv)
        timePasagalegoTv = findViewById(R.id.time_pasagalego_tv)
        errorAnswersTv = findViewById(R.id.error_answers_result_tv)
        pasagalegoFinishButton = findViewById(R.id.pasagalego_finish_btn)

        setBanner(this, R.string.pasagalego)

        val score = intent.getIntExtra(PasagalegoConstants.SCORE, 0)
        val time = intent.getStringExtra(PasagalegoConstants.TIME)
        val errors = intent.getIntExtra(PasagalegoConstants.ERRORS, 0)

        correctAnswersTv.text=score.toString()
        timePasagalegoTv.text=time.toString()
        errorAnswersTv.text=errors.toString()

        pasagalegoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, PasagalegoInicioActivity::class.java)

    }
}