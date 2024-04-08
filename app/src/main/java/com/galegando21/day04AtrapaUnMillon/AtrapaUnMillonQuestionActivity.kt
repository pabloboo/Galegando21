package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.galegando21.BannerFragment
import com.galegando21.R
import com.galegando21.model.QuestionAtrapaUnMillon
import com.galegando21.utils.AtrapaUnMillonConstants
import com.galegando21.utils.AtrapaUnMillonConstants.getAtrapaUnMillonQuestions
import kotlin.random.Random

class AtrapaUnMillonQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var textViewCash : TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView

    private lateinit var textViewOption1: TextView
    private lateinit var textViewOption2: TextView
    private lateinit var textViewOption3: TextView
    private lateinit var textViewOption4: TextView

    private lateinit var checkButton: Button

    private var questionsCounter = 1
    private lateinit var questionsList: MutableList<QuestionAtrapaUnMillon>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: QuestionAtrapaUnMillon
    private var answered = false

    private var cash = 1000000
    private val total_questions = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_question)

        textViewCash = findViewById(R.id.cash_tv)
        progressBar = findViewById(R.id.atrapa_un_millon_progress_bar)
        textViewProgress = findViewById(R.id.atrapa_un_millon_text_view_progress)
        textViewQuestion = findViewById(R.id.atrapa_un_millon_question_text_view)
        textViewOption1 = findViewById(R.id.atrapa_un_millon_text_view_option1)
        textViewOption2 = findViewById(R.id.atrapa_un_millon_text_view_option2)
        textViewOption3 = findViewById(R.id.atrapa_un_millon_text_view_option3)
        textViewOption4 = findViewById(R.id.atrapa_un_millon_text_view_option4)
        checkButton = findViewById(R.id.atrapa_un_millon_btn_check)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapa_un_millon))
        }.commit()

        textViewOption1.setOnClickListener(this)
        textViewOption2.setOnClickListener(this)
        textViewOption3.setOnClickListener(this)
        textViewOption4.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionsList = getQuestions()
        showNextQuestion()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapaUnMillonQuestionActivity, AtrapaUnMillonInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun getQuestions() : MutableList<QuestionAtrapaUnMillon> {
        var questions = mutableListOf<QuestionAtrapaUnMillon>()
        for (i in 1..total_questions) {
            var questionsAux = mutableListOf<QuestionAtrapaUnMillon>()
            when (i) {
                1 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.EASY_LEVEL)
                2 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.EASY_LEVEL)
                3 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.EASY_LEVEL)
                4 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.EASY_LEVEL)
                5 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.MEDIUM_LEVEL)
                6 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.MEDIUM_LEVEL)
                7 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.MEDIUM_LEVEL)
                8 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.HARD_LEVEL)
            }
            var questionAux : QuestionAtrapaUnMillon = questionsAux[Random.nextInt(0, questionsAux.size)]
            questions.add(questionAux)
        }
        return questions
    }

    private fun showNextQuestion() {

        if (questionsCounter < 8) {
            checkButton.text = "Check"

            resetOptions()
            val question = questionsList[questionsCounter]
            textViewCash.text="$cash€"
            progressBar.progress = questionsCounter
            textViewProgress.text = "$questionsCounter/${progressBar.max}"
            textViewQuestion.text = question.question
            textViewOption1.text = question.optionOne
            textViewOption2.text = question.optionTwo
            textViewOption3.text = question.optionThree
            textViewOption4.text = question.optionFour
            currentQuestion = question
        } else {
            checkButton.text = "Rematar"
            Intent(this, AtrapaUnMillonResultsActivity::class.java).also {
                it.putExtra(AtrapaUnMillonConstants.SCORE, cash)
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
            cash=cash
            highlightAnswer(selectedAnswer)
        } else {
            cash /= 2
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
            R.id.atrapa_un_millon_text_view_option1 -> {
                selectedOption(textViewOption1, 1)
            }

            R.id.atrapa_un_millon_text_view_option2 -> {
                selectedOption(textViewOption2, 2)
            }

            R.id.atrapa_un_millon_text_view_option3 -> {
                selectedOption(textViewOption3, 3)
            }

            R.id.atrapa_un_millon_text_view_option4 -> {
                selectedOption(textViewOption4, 4)
            }

            R.id.atrapa_un_millon_btn_check -> {
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