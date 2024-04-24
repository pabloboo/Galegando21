package com.galegando21.day07verdadeOuMentira

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.QuestionVerdadeOuMentira
import com.galegando21.utils.VerdadeOuMentiraConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlin.random.Random

class VerdadeOuMentiraQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var correctAnswersTextView: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var textViewVerdadeiro: TextView
    private lateinit var textViewFalso: TextView
    private lateinit var checkButton: Button

    private var correctAnswers = 0
    private lateinit var questionList: MutableList<QuestionVerdadeOuMentira>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: QuestionVerdadeOuMentira
    private var answered = false
    private var questionFailed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_question)

        correctAnswersTextView = findViewById(R.id.verdade_ou_mentira_correct_answers_text_view)
        textViewQuestion = findViewById(R.id.verdade_ou_mentira_question_text_view)
        textViewVerdadeiro = findViewById(R.id.verdade_ou_mentira_text_view_verdadeiro)
        textViewFalso = findViewById(R.id.verdade_ou_mentira_text_view_falso)
        checkButton = findViewById(R.id.verdade_ou_mentira_btn_check)

        setBanner(this, R.string.verdadeOuMentira)

        textViewVerdadeiro.setOnClickListener(this)
        textViewFalso.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionList = VerdadeOuMentiraConstants.getQuestions()

        showNextQuestion()

        setOnBackPressed(this, VerdadeOuMentiraInicioActivity::class.java)
    }

    private fun showNextQuestion() {
        resetOptions()
        if (!questionFailed) {
            checkButton.text = "Comprobar"
            resetOptions()

            if (questionList.isEmpty()) { // si se ha acabado la lista de preguntas volver a cogerlas todas
                questionList = VerdadeOuMentiraConstants.getQuestions()
            }
            val question = questionList.random()
            textViewQuestion.text = question.question
            currentQuestion = question
            questionList = (questionList - currentQuestion).toMutableList() //eliminar la pregunta de la lista
        } else {
            checkButton.text="Finalizar"
            Intent(this, VerdadeOuMentiraResultsActivity::class.java).also {
                it.putExtra(VerdadeOuMentiraConstants.SCORE, correctAnswers)
                startActivity(it)
            }
        }
        answered = false
    }

    private fun resetOptions() {
        textViewVerdadeiro.setTextColor(Color.parseColor("#7A8089"))
        textViewVerdadeiro.typeface = Typeface.DEFAULT
        textViewVerdadeiro.background = ContextCompat.getDrawable(
            this,
            R.drawable.default_option_border_bg
        )

        textViewFalso.setTextColor(Color.parseColor("#7A8089"))
        textViewFalso.typeface = Typeface.DEFAULT
        textViewFalso.background = ContextCompat.getDrawable(
            this,
            R.drawable.default_option_border_bg
        )
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
            correctAnswers++
            correctAnswersTextView.text=correctAnswers.toString()
            highlightAnswer(selectedAnswer)
        } else {
            when (selectedAnswer) {
                0 -> {
                    textViewFalso.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                1 -> {
                    textViewVerdadeiro.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }
            }
            questionFailed = true
        }
        checkButton.text = "Seguinte"
        showSolution()
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            0 -> {
                textViewFalso.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            1 -> {
                textViewVerdadeiro.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.verdade_ou_mentira_text_view_falso -> {
                selectedOption(textViewFalso, 0)
            }

            R.id.verdade_ou_mentira_text_view_verdadeiro -> {
                selectedOption(textViewVerdadeiro, 1)
            }

            R.id.verdade_ou_mentira_btn_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }
}