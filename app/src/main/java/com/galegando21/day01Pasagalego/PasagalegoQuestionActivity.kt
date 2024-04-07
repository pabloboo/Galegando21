package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Chronometer
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.QuestionPasagalego
import com.galegando21.utils.PasagalegoConstants
import com.galegando21.utils.PasagalegoConstants.ALFABETO
import com.galegando21.utils.PasagalegoConstants.getPasagalegoQuestions
import kotlin.random.Random

class PasagalegoQuestionActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var letter_tv : TextView
    private lateinit var question_tv : TextView
    private lateinit var correctAnswersTv : TextView
    private lateinit var errorAnswersTv: TextView
    private lateinit var chronometer: Chronometer
    private lateinit var userAnswerText : EditText
    private lateinit var checkButton : ImageButton

    private var questionCounter = 0
    private var correctAnswers = 0
    private var errorAnswers = 0
    private lateinit var currentQuestionPasagalego : QuestionPasagalego
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_question)

        letter_tv = findViewById(R.id.tv_pasagalego_letter)
        question_tv = findViewById(R.id.tv_pasagalego_question)
        correctAnswersTv = findViewById(R.id.correct_answers_tv)
        errorAnswersTv = findViewById(R.id.error_answers_tv)
        chronometer = findViewById(R.id.chronometer)
        userAnswerText = findViewById(R.id.pasagalego_answer)
        userAnswerText.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_pasagalego)

        // Settear banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.pasagalego))
        }.commit()

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        errorAnswersTv.text = "0"
        correctAnswersTv.text = "0"
        chronometer.start()

        showNextQuestion()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@PasagalegoQuestionActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun showNextQuestion() {
        if (questionCounter < ALFABETO.length) {
            val letter = ALFABETO[questionCounter]
            // Obtener pregunta aleatoria
            val questionList = getPasagalegoQuestions(letter)
            val randomNumber = Random.nextInt(0, questionList.size)
            currentQuestionPasagalego = questionList[randomNumber]
            letter_tv.text = "Comeza pola letra '$letter'"
            question_tv.text = currentQuestionPasagalego.question
            correctAnswersTv.text = correctAnswers.toString()
            errorAnswersTv.text = errorAnswers.toString()
        } else {
            chronometer.stop()
            Intent(this, PasagalegoResultActivity::class.java). also {
                it.putExtra(PasagalegoConstants.SCORE, correctAnswers)
                it.putExtra(PasagalegoConstants.ERRORS, errorAnswers)
                it.putExtra(PasagalegoConstants.TIME, chronometer.text)
                startActivity(it)
                finish()
            }
        }
    }

    private fun checkButtonClickListener() {
        questionCounter++
        if (userAnswerText.text.toString() == currentQuestionPasagalego.answer) {
            correctAnswers++
            Toast.makeText(this@PasagalegoQuestionActivity, "Resposta correcta, ${currentQuestionPasagalego.answer}", Toast.LENGTH_SHORT).show()
        } else {
            errorAnswers++
            Toast.makeText(this@PasagalegoQuestionActivity, "Resposta incorrecta, ${currentQuestionPasagalego.answer}", Toast.LENGTH_SHORT).show()
        }
        userAnswerText.text.clear()
        showNextQuestion()
    }
}