package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.PasagalegoConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

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
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var currentCorrectAnswers = correctAnswersTv.text.toString().toInt()
        var currentErrorAnswers = errorAnswersTv.text.toString().toInt()
        var currentPasagalegoTime = timePasagalegoTv.text.toString()
        val modo = intent.getStringExtra("modo") ?: "diccionario"

        var maxCorrectAnswers = 0
        var maxErrors = 0
        var maxTime = "999999:59"

        var correctAnswersKey = SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY
        var errorAnswersKey = SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY
        var timeKey = SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY
        when (modo) {
            "diccionario" -> {
                correctAnswersKey = SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY
                errorAnswersKey = SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY
                timeKey = SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY
            }
            "orixinal" -> {
                correctAnswersKey = SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_ORIXINAL
                errorAnswersKey = SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_ORIXINAL
                timeKey = SharedPreferencesKeys.PASAGALEGO_TIME_ORIXINAL
            }
            "diccionario_facil" -> {
                correctAnswersKey = SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY_EASY
                errorAnswersKey = SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY_EASY
                timeKey = SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY_EASY
            }
        }

        if (sharedPreferences.contains(correctAnswersKey)) {
            maxCorrectAnswers = sharedPreferences.getInt(correctAnswersKey, 0)
        }
        if (sharedPreferences.contains(errorAnswersKey)) {
            maxErrors = sharedPreferences.getInt(errorAnswersKey, 0)
        }
        if (sharedPreferences.contains(timeKey)) {
            maxTime = sharedPreferences.getString(timeKey, "00:00").toString()
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

        editor.putInt(correctAnswersKey, maxCorrectAnswers)
        editor.putInt(errorAnswersKey, maxErrors)
        editor.putString(timeKey, maxTime)
        editor.apply()
        Log.d("PASAGALEGO", "Correct answers: $maxCorrectAnswers, Errors: $maxErrors, Time: $maxTime")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, currentCorrectAnswers * 10)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }

    private fun timeToSeconds(time: String): Int {
        val split = time.split(":")
        val minutes = split[0].toInt()
        val seconds = split[1].toInt()
        return minutes * 60 + seconds
    }
}