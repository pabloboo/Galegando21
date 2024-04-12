package com.galegando21.day09AdivinhaEscudo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaEscudo
import com.galegando21.utils.AdivinhaEscudoConstants
import kotlin.random.Random

class AdivinhaEscudoQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewOption1: TextView
    private lateinit var textViewOption2: TextView
    private lateinit var textViewOption3: TextView
    private lateinit var textViewOption4: TextView

    private lateinit var checkButton: Button

    private var questionsCounter = 1
    private lateinit var questionsList: MutableList<QuestionAdivinhaEscudo>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: QuestionAdivinhaEscudo
    private var answered = false

    private var total_questions = 10
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_escudo_question)

        progressBar = findViewById(R.id.adivinha_escudo_progress_bar)
        textViewProgress = findViewById(R.id.adivinha_escudo_text_view_progress)
        flagImage = findViewById(R.id.adivinha_escudo_image)
        textViewOption1 = findViewById(R.id.adivinha_escudo_text_view_option1)
        textViewOption2 = findViewById(R.id.adivinha_escudo_text_view_option2)
        textViewOption3 = findViewById(R.id.adivinha_escudo_text_view_option3)
        textViewOption4 = findViewById(R.id.adivinha_escudo_text_view_option4)
        checkButton = findViewById(R.id.adivinha_escudo_button_check)

        textViewOption1.setOnClickListener(this)
        textViewOption2.setOnClickListener(this)
        textViewOption3.setOnClickListener(this)
        textViewOption4.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.adivinha_escudo))
        }.commit()

        questionsList = AdivinhaEscudoConstants.getQuestions()

        showNextQuestion()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AdivinhaEscudoQuestionActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun showNextQuestion() {

        if (questionsCounter < total_questions) {
            checkButton.text = "Comprobar"

            resetOptions()
            val question = questionsList[Random.nextInt(0, questionsList.size)]
            flagImage.setImageResource(question.image)
            progressBar.progress = questionsCounter
            textViewProgress.text = "$questionsCounter/${progressBar.max}"
            textViewOption1.text = question.optionOne
            textViewOption2.text = question.optionTwo
            textViewOption3.text = question.optionThree
            textViewOption4.text = question.optionFour
            currentQuestion = question
        } else {
            checkButton.text = "Rematar"
            Intent(this, MainActivity::class.java).also {
                it.putExtra(AdivinhaEscudoConstants.SCORE, score)
                it.putExtra(AdivinhaEscudoConstants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
            }
        }

        questionsCounter++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOption1)
        options.add(textViewOption2)
        options.add(textViewOption3)
        options.add(textViewOption4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()
        selectedAnswer = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highlightAnswer(selectedAnswer)
    }

    private fun checkAnswer() {
        answered = true
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            highlightAnswer(selectedAnswer)
        } else {
            when (selectedAnswer) {
                1 -> {
                    textViewOption1.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                2 -> {
                    textViewOption2.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                3 -> {
                    textViewOption3.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                4 -> {
                    textViewOption4.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }
            }
        }
        checkButton.text = "Seguinte"
        showSolution()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.adivinha_escudo_text_view_option1 -> {
                selectedOption(textViewOption1, 1)
            }

            R.id.adivinha_escudo_text_view_option2 -> {
                selectedOption(textViewOption2, 2)
            }

            R.id.adivinha_escudo_text_view_option3 -> {
                selectedOption(textViewOption3, 3)
            }

            R.id.adivinha_escudo_text_view_option4 -> {
                selectedOption(textViewOption4, 4)
            }

            R.id.adivinha_escudo_button_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                textViewOption1.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            2 -> {
                textViewOption2.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            3 -> {
                textViewOption3.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            4 -> {
                textViewOption4.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }
        }
    }
}