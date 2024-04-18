package com.galegando21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class StatisticsActivity : AppCompatActivity() {
    private lateinit var correctAnswersPasagalego : TextView
    private lateinit var errorAnswersPasagalego : TextView
    private lateinit var timePasagalego : TextView

    private lateinit var questionsNeededAtrapameSePodes : TextView
    private lateinit var cashAtrapaUnMillon : TextView
    private lateinit var verdadeOuMentiraScore : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        setBanner(this, R.string.estadisticas)
        setOnBackPressed(this, MainActivity::class.java)

        correctAnswersPasagalego = findViewById(R.id.correct_answers_pasagalego_statistics)
        errorAnswersPasagalego = findViewById(R.id.error_answers_pasagalego_statistics)
        timePasagalego = findViewById(R.id.time_pasagalego_statistics)
        questionsNeededAtrapameSePodes = findViewById(R.id.questions_needed_atrapame_se_podes_statistics)
        cashAtrapaUnMillon = findViewById(R.id.cash_atrapa_un_millon_statistics)
        verdadeOuMentiraScore = findViewById(R.id.verdade_ou_mentira_correct_answers_statistics)

        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)
        correctAnswersPasagalego.text = sharedPreferences.getInt("pasagalego_correct_answers", 0).toString()
        errorAnswersPasagalego.text = sharedPreferences.getInt("pasagalego_error_answers", 0).toString()
        timePasagalego.text = sharedPreferences.getString("pasagalego_time", "00:00")

        val questionsNeededAtrapameSePodesValue = sharedPreferences.getInt("atrapame_se_podes_questions_needed", 0).toString()
        questionsNeededAtrapameSePodes.text = "Necesitaches un total de $questionsNeededAtrapameSePodesValue preguntas."

        val cashAtrapaUnMillonValue = sharedPreferences.getInt("atrapa_un_millon_max_cash", 0).toString()
        cashAtrapaUnMillon.text = "$cashAtrapaUnMillonValue€"

        verdadeOuMentiraScore.text = sharedPreferences.getInt("verdade_ou_mentira_max_score", 0).toString()

    }
}