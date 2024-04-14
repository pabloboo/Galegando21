package com.galegando21.day10AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaAnoFoto
import com.galegando21.utils.AdivinhaAnoFotoConstants
import com.google.android.material.slider.Slider
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.random.Random

class AdivinhaAnoFotoGameActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var progressBar : ProgressBar
    private lateinit var textViewProgress : TextView
    private lateinit var imageView : ImageView
    private lateinit var descriptionSolution : TextView
    private lateinit var yearSolution : Slider
    private lateinit var checkButton : Button

    private var questionsCounter = 1
    private lateinit var questionsList: MutableList<QuestionAdivinhaAnoFoto>
    private lateinit var currentQuestion: QuestionAdivinhaAnoFoto
    private var answered = false

    private var total_questions = 5
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_game)

        progressBar = findViewById(R.id.adivinhaAnoFoto_progress_bar)
        textViewProgress = findViewById(R.id.adivinhaAnoFoto_text_view_progress)
        imageView = findViewById(R.id.adivinhaAnoFoto_foto_ImageView)
        descriptionSolution = findViewById(R.id.adivinhaAnoFoto_solution_description_tv)
        yearSolution = findViewById(R.id.adivinhaAnoFoto_slider)
        checkButton = findViewById(R.id.check_btn_AdivinhaAnoFoto)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.adivinha_ano_foto))
        }.commit()

        questionsList = AdivinhaAnoFotoConstants.getQuestions()

        checkButton.setOnClickListener {
            checkAnswer()
        }

        showNextQuestion()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AdivinhaAnoFotoGameActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun showNextQuestion() {
        if (questionsCounter <= total_questions) {
            val random = Random.nextInt(0, questionsList.size)
            currentQuestion = questionsList[random]
            progressBar.progress = questionsCounter
            textViewProgress.text = "$questionsCounter/$total_questions"
            imageView.setImageResource(currentQuestion.image)
            descriptionSolution.visibility = TextView.GONE
            yearSolution.clearFocus()
            answered = false
            checkButton.text = getString(R.string.check)
            questionsCounter++
        } else {
            Intent(this@AdivinhaAnoFotoGameActivity, AdivinhaAnoFotoResultsActivity::class.java).also {
                Log.d("SCORE", score.toString())
                it.putExtra(AdivinhaAnoFotoConstants.SCORE, score)
                startActivity(it)
                finish()
            }
        }
    }

    fun checkAnswer() {
        if (!answered) {
            val answer = yearSolution.value.toString()
            if (answer.isNotEmpty()) {
                val answerInt = answer.toFloat().roundToInt()
                score += calculateScore(answerInt, currentQuestion.answerYear)
                answered = true
                descriptionSolution.text = "${currentQuestion.answerYear}: ${currentQuestion.description}"
                descriptionSolution.visibility = TextView.VISIBLE
                checkButton.text = getString(R.string.seguinte)
            }
        } else {
            showNextQuestion()
        }
    }

    fun calculateScore(guessedYear: Int, correctAnswerYear: Int) : Int {
        val difference = (guessedYear - correctAnswerYear).absoluteValue
        when (difference) {
            in 0..0 -> return 10
            in 1 .. 1 -> return 8
            in 2..3 -> return 6
            in 4..5 -> return 4
            in 6 .. 10 -> return 2
            in 11 .. 20 -> return 1
            else -> return 0
        }
    }
}