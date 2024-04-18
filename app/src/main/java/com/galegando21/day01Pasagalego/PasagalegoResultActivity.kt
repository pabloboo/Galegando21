package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        changePasagalegoStatistics()

        pasagalegoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, PasagalegoInicioActivity::class.java)

    }

    private fun changePasagalegoStatistics() {
        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var currentCorrectAnswers = correctAnswersTv.text.toString().toInt()
        var currentErrorAnswers = errorAnswersTv.text.toString().toInt()
        var currentPasagalegoTime = timePasagalegoTv.text.toString()

        var maxCorrectAnswers = 0
        var maxErrors = 0
        var maxTime = "999999:59"
        if (sharedPreferences.contains("pasagalego_correct_answers")) {
            maxCorrectAnswers = sharedPreferences.getInt("pasagalego_correct_answers", 0)
        }
        if (sharedPreferences.contains("pasagalego_error_answers")) {
            maxErrors = sharedPreferences.getInt("pasagalego_error_answers", 0)
        }
        if (sharedPreferences.contains("pasagalego_time")) {
            maxTime = sharedPreferences.getString("pasagalego_time", "00:00").toString()
        }

        if (currentCorrectAnswers > maxCorrectAnswers) { // Cambiar estadísticas si tiene más aciertos
            maxCorrectAnswers = currentCorrectAnswers
            maxErrors = currentErrorAnswers
            maxTime = currentPasagalegoTime
        } else if (currentCorrectAnswers == maxCorrectAnswers) { // Cambiar estadísticas si tiene los mismos aciertos y menos tiempo
            if (timeToSeconds(currentPasagalegoTime) < timeToSeconds(maxTime)) {
                maxCorrectAnswers = currentCorrectAnswers
                maxErrors = currentErrorAnswers
                maxTime = currentPasagalegoTime
            }
        }

        editor.putInt("pasagalego_correct_answers", maxCorrectAnswers)
        editor.putInt("pasagalego_error_answers", maxErrors)
        editor.putString("pasagalego_time", maxTime)
        editor.apply()
        Log.d("PASAGALEGO", "Correct answers: $maxCorrectAnswers, Errors: $maxErrors, Time: $maxTime")
    }

    private fun timeToSeconds(time: String): Int {
        val split = time.split(":")
        val minutes = split[0].toInt()
        val seconds = split[1].toInt()
        return minutes * 60 + seconds
    }
}