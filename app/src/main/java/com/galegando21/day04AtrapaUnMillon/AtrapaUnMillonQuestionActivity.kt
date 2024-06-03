package com.galegando21.day04AtrapaUnMillon

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.QuestionAtrapaUnMillon
import com.galegando21.utils.AtrapaUnMillonConstants
import com.galegando21.utils.AtrapaUnMillonConstants.getAtrapaUnMillonQuestions
import com.galegando21.utils.setOnBackPressed

class AtrapaUnMillonQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var textViewCash : TextView
    private lateinit var atrapa1MillonImageView: ImageView
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

    private lateinit var quantityOption1 : TextView
    private lateinit var quantityOption2 : TextView
    private lateinit var quantityOption3 : TextView
    private lateinit var quantityOption4 : TextView

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
        atrapa1MillonImageView = findViewById(R.id.atrapa_un_millon_image_view)
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
        quantityOption1 = findViewById(R.id.atrapa_un_millon_quantity_text_option1)
        quantityOption2 = findViewById(R.id.atrapa_un_millon_quantity_text_option2)
        quantityOption3 = findViewById(R.id.atrapa_un_millon_quantity_text_option3)
        quantityOption4 = findViewById(R.id.atrapa_un_millon_quantity_text_option4)
        checkButton = findViewById(R.id.atrapa_un_millon_btn_check)

        val dragMoney = View.OnLongClickListener { v ->
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(v)
            v.startDragAndDrop(data, shadowBuilder, v, 0)
            true
        }

        atrapa1MillonImageView.setOnLongClickListener(dragMoney)

        val relocateMoney = View.OnLongClickListener { v ->
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(v)
            if (v is LinearLayout) {
                val droppedText = when (v.id) {
                    R.id.atrapa_un_millon_linear_layout_option1 -> quantityOption1
                    R.id.atrapa_un_millon_linear_layout_option2 -> quantityOption2
                    R.id.atrapa_un_millon_linear_layout_option3 -> quantityOption3
                    R.id.atrapa_un_millon_linear_layout_option4 -> quantityOption4
                    else -> return@OnLongClickListener true
                }
                val currentAmount = droppedText.text.toString().toIntOrNull() ?: 0
                if (currentAmount > 0) {
                    droppedText.text = (currentAmount - 100000).toString()
                    v.startDragAndDrop(data, shadowBuilder, v, 0)
                    true
                } else {
                    false
                }
            }
            true
        }

        linearLayoutOption1.setOnLongClickListener(relocateMoney)
        linearLayoutOption2.setOnLongClickListener(relocateMoney)
        linearLayoutOption3.setOnLongClickListener(relocateMoney)
        linearLayoutOption4.setOnLongClickListener(relocateMoney)

        val dragListener = View.OnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    if (v is LinearLayout) {
                        val droppedText = when (v.id) {
                            R.id.atrapa_un_millon_linear_layout_option1 -> quantityOption1
                            R.id.atrapa_un_millon_linear_layout_option2 -> quantityOption2
                            R.id.atrapa_un_millon_linear_layout_option3 -> quantityOption3
                            R.id.atrapa_un_millon_linear_layout_option4 -> quantityOption4
                            else -> return@OnDragListener true
                        }
                        val currentAmount = droppedText.text.toString().toIntOrNull() ?: 0
                        droppedText.text = (currentAmount + 100000).toString()
                    }
                }
            }
            true
        }

        linearLayoutOption1.setOnDragListener(dragListener)
        linearLayoutOption2.setOnDragListener(dragListener)
        linearLayoutOption3.setOnDragListener(dragListener)
        linearLayoutOption4.setOnDragListener(dragListener)

        checkButton.setOnClickListener(this)

        questionsList = getQuestions()
        showNextQuestion()

        setOnBackPressed(this, AtrapaUnMillonInicioActivity::class.java)
    }

    private fun getQuestions() : MutableList<QuestionAtrapaUnMillon> {
        var questions = mutableListOf<QuestionAtrapaUnMillon>()
        var questionsAux = mutableListOf<QuestionAtrapaUnMillon>()
        for (i in 1..total_questions) {
            when (i) { // cambiar la lista de preguntas sólo cuando cambia la dificultad.
                1 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.EASY_LEVEL)
                5 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.MEDIUM_LEVEL)
                8 -> questionsAux = getAtrapaUnMillonQuestions(AtrapaUnMillonConstants.HARD_LEVEL)
            }
            var questionAux : QuestionAtrapaUnMillon = questionsAux.random()
            questionsAux = (questionsAux - questionAux).toMutableList()
            questions.add(questionAux)
        }
        return questions
    }

    private fun showNextQuestion() {

        if (questionsCounter < 8 && cash!=0) {
            checkButton.text = "Comprobar"

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
                finish()
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

        val quantityTextOptions = mutableListOf<TextView>()
        quantityTextOptions.add(quantityOption1)
        quantityTextOptions.add(quantityOption2)
        quantityTextOptions.add(quantityOption3)
        quantityTextOptions.add(quantityOption4)
        for (option in quantityTextOptions) {
            option.text = "0"
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
                if (quantityOption1.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = quantityOption1.text.toString().toInt()
                }
            }
            2 -> {
                if (quantityOption2.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = quantityOption2.text.toString().toInt()
                }
            }
            3 -> {
                if (quantityOption3.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = quantityOption3.text.toString().toInt()
                }
            }
            4 -> {
                if (quantityOption4.text.isEmpty()) {
                    cash = 0
                } else {
                    cash = quantityOption4.text.toString().toInt()
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
                if (quantityOption1.text.isNotEmpty()) {
                    valueOption1 = quantityOption1.text.toString().toInt()
                }
                var valueOption2 = 0
                if (quantityOption2.text.isNotEmpty()) {
                    valueOption2 = quantityOption2.text.toString().toInt()
                }
                var valueOption3 = 0
                if (quantityOption3.text.isNotEmpty()) {
                    valueOption3 = quantityOption3.text.toString().toInt()
                }
                var valueOption4 = 0
                if (quantityOption4.text.isNotEmpty()) {
                    valueOption4 = quantityOption4.text.toString().toInt()
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