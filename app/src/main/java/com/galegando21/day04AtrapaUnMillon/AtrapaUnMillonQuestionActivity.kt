package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
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

    private lateinit var linearLayoutOption1 : LinearLayout
    private lateinit var linearLayoutOption2 : LinearLayout
    private lateinit var linearLayoutOption3 : LinearLayout
    private lateinit var linearLayoutOption4 : LinearLayout

    private lateinit var textViewOption1: TextView
    private lateinit var textViewOption2: TextView
    private lateinit var textViewOption3: TextView
    private lateinit var textViewOption4: TextView

    private lateinit var editTextOption1 : EditText
    private lateinit var editTextOption2 : EditText
    private lateinit var editTextOption3 : EditText
    private lateinit var editTextOption4 : EditText

    private lateinit var checkButton: Button

    private var questionsCounter = 1
    private lateinit var questionsList: MutableList<QuestionAtrapaUnMillon>
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
        linearLayoutOption1 = findViewById(R.id.atrapa_un_millon_linear_layout_option1)
        linearLayoutOption2 = findViewById(R.id.atrapa_un_millon_linear_layout_option2)
        linearLayoutOption3 = findViewById(R.id.atrapa_un_millon_linear_layout_option3)
        linearLayoutOption4 = findViewById(R.id.atrapa_un_millon_linear_layout_option4)
        textViewOption1 = findViewById(R.id.atrapa_un_millon_text_view_option1)
        textViewOption2 = findViewById(R.id.atrapa_un_millon_text_view_option2)
        textViewOption3 = findViewById(R.id.atrapa_un_millon_text_view_option3)
        textViewOption4 = findViewById(R.id.atrapa_un_millon_text_view_option4)
        editTextOption1 = findViewById(R.id.atrapa_un_millon_edittext_option1)
        editTextOption2 = findViewById(R.id.atrapa_un_millon_edittext_option2)
        editTextOption3 = findViewById(R.id.atrapa_un_millon_edittext_option3)
        editTextOption4 = findViewById(R.id.atrapa_un_millon_edittext_option4)
        checkButton = findViewById(R.id.atrapa_un_millon_btn_check)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapa_un_millon))
        }.commit()

        //only accept numbers in editText
        editTextOption1.inputType=InputType.TYPE_CLASS_NUMBER
        editTextOption2.inputType=InputType.TYPE_CLASS_NUMBER
        editTextOption3.inputType=InputType.TYPE_CLASS_NUMBER
        editTextOption4.inputType=InputType.TYPE_CLASS_NUMBER

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

        if (questionsCounter < 8 && cash!=0) {
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
        }

        val linearLayoutOptions = mutableListOf<LinearLayout>()
        linearLayoutOptions.add(linearLayoutOption1)
        linearLayoutOptions.add(linearLayoutOption2)
        linearLayoutOptions.add(linearLayoutOption3)
        linearLayoutOptions.add(linearLayoutOption4)
        for (option in linearLayoutOptions) {
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

        val editTextOptions = mutableListOf<EditText>()
        editTextOptions.add(editTextOption1)
        editTextOptions.add(editTextOption2)
        editTextOptions.add(editTextOption3)
        editTextOptions.add(editTextOption4)
        for (option in editTextOptions) {
            option.text.clear()
        }
    }

    private fun showSolution() {
        highlightAnswer(currentQuestion.correctAnswer)
    }

    private fun checkAnswer() {
        answered = true
        val solution = currentQuestion.correctAnswer
        when (solution) {
            1 -> {
                if (editTextOption1.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = editTextOption1.text.toString().toInt()
                }
            }
            2 -> {
                if (editTextOption2.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = editTextOption2.text.toString().toInt()
                }
            }
            3 -> {
                if (editTextOption3.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = editTextOption3.text.toString().toInt()
                }
            }
            4 -> {
                if (editTextOption4.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = editTextOption4.text.toString().toInt()
                }
            }
        }

        checkButton.text = "Seguinte"
        showSolution()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.atrapa_un_millon_btn_check -> {
                var valueOption1 = 0
                if (editTextOption1.text.isNotEmpty()) {
                    valueOption1 = editTextOption1.text.toString().toInt()
                }
                var valueOption2 = 0
                if (editTextOption2.text.isNotEmpty()) {
                    valueOption2 = editTextOption2.text.toString().toInt()
                }
                var valueOption3 = 0
                if (editTextOption3.text.isNotEmpty()) {
                    valueOption3 = editTextOption3.text.toString().toInt()
                }
                var valueOption4 = 0
                if (editTextOption4.text.isNotEmpty()) {
                    valueOption4 = editTextOption4.text.toString().toInt()
                }
                val sum = valueOption1+valueOption2+valueOption3+valueOption4
                if (!answered) {
                    if (questionsCounter == 8 && (valueOption1 != cash && valueOption2 != cash && valueOption3 != cash && valueOption4 != cash)) {
                        Toast.makeText(this@AtrapaUnMillonQuestionActivity, "Tes que poñer todo o diñeiro en unha soa opción", Toast.LENGTH_SHORT).show()
                    } else if (sum===cash) {
                        checkAnswer()
                    } else {
                        Toast.makeText(this@AtrapaUnMillonQuestionActivity, "Tes que sumar un total de $cash€", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    showNextQuestion()
                }
            }
        }
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                linearLayoutOption1.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            2 -> {
                linearLayoutOption2.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            3 -> {
                linearLayoutOption3.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            4 -> {
                linearLayoutOption4.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }
        }
    }
}