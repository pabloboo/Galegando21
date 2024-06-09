package com.galegando21.day07verdadeOuMentira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import com.galegando21.R
import com.galegando21.model.QuestionVerdadeOuMentira
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.VerdadeOuMentiraConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class VerdadeOuMentiraQuestionActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recordSuperadoTextView: TextView
    private lateinit var correctAnswersTextView: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var trueButton: ImageButton
    private lateinit var falseButton: ImageButton

    private var correctAnswers = 0
    private lateinit var questionList: MutableList<QuestionVerdadeOuMentira>
    private lateinit var currentQuestion: QuestionVerdadeOuMentira
    private var questionFailed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_question)

        progressBar = findViewById(R.id.verdade_ou_mentira_progress_bar)
        recordSuperadoTextView = findViewById(R.id.verdade_ou_mentira_record_superado_text_view)
        correctAnswersTextView = findViewById(R.id.verdade_ou_mentira_correct_answers_text_view)
        textViewQuestion = findViewById(R.id.verdade_ou_mentira_question_text_view)
        trueButton = findViewById(R.id.verdade_ou_mentira_true_button)
        falseButton = findViewById(R.id.verdade_ou_mentira_false_button)

        setBanner(this, R.string.verdadeOuMentira)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0)
        }

        if (maxScore == 0) {
            progressBar.visibility = ProgressBar.GONE
            recordSuperadoTextView.visibility = TextView.GONE
        } else {
            progressBar.max = maxScore
            progressBar.progress = correctAnswers
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        questionList = VerdadeOuMentiraConstants.getQuestions()

        showNextQuestion()

        setOnBackPressed(this, VerdadeOuMentiraInicioActivity::class.java)
    }

    private fun showNextQuestion() {
        if (!questionFailed) {

            if (questionList.isEmpty()) { // si se ha acabado la lista de preguntas volver a cogerlas todas
                questionList = VerdadeOuMentiraConstants.getQuestions()
            }
            val question = questionList.random()
            textViewQuestion.text = question.question
            currentQuestion = question
            questionList = (questionList - currentQuestion).toMutableList() //eliminar la pregunta de la lista
        } else {
            Intent(this, VerdadeOuMentiraResultsActivity::class.java).also {
                it.putExtra(VerdadeOuMentiraConstants.SCORE, correctAnswers)
                startActivity(it)
                finish()
            }
        }
    }

    private fun checkAnswer(resposta: Boolean) {
        if (currentQuestion.correctAnswer == resposta) {
            correctAnswers++
            progressBar.progress = correctAnswers
            if (correctAnswers > progressBar.max) {
                progressBar.visibility = View.INVISIBLE
                recordSuperadoTextView.visibility = View.VISIBLE
                recordSuperadoTextView.text = "Superaches o teu récord!\n Levas $correctAnswers preguntas seguidas"
            }
        } else {
            questionFailed = true
        }
        showNextQuestion()
    }
}