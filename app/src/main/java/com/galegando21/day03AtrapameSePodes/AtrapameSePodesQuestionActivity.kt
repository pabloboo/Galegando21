package com.galegando21.day03AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.model.QuestionAtrapameSePodes
import com.galegando21.utils.AtrapameSePodesConstants
import com.galegando21.utils.AtrapameSePodesConstants.getAtrapameSePodesQuestions
import com.galegando21.utils.removeAccents
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapameSePodesQuestionActivity : AppCompatActivity() {
    private lateinit var stepsFragment: AtrapameSePodesStepsFragment

    private lateinit var questionTV: TextView
    private lateinit var userAnswerText: EditText
    private lateinit var checkButton: ImageButton

    private var questionList = mutableListOf<QuestionAtrapameSePodes>()
    private var questionCounter = 0
    private var correctAnswers = 0
    private lateinit var currentQuestionAtrapameSePodes: QuestionAtrapameSePodes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_question)

        questionTV = findViewById(R.id.tv_atrapame_se_podes_question)
        userAnswerText = findViewById(R.id.atrapame_se_podes_answer)
        userAnswerText.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_atrapame_se_podes)

        setBanner(this, R.string.atrapame_se_podes)

        setStepsFragment(0)

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        showNextQuestion()

        setOnBackPressed(this, AtrapameSePodesInicioActivity::class.java)
    }

    private fun showNextQuestion(sameLevel: Boolean = false) {
        if (correctAnswers < 5) {
            if (sameLevel) {
                questionList = (questionList - currentQuestionAtrapameSePodes).toMutableList()
            }
            if (!sameLevel || questionList.isEmpty()) {
                questionList = getAtrapameSePodesQuestions(correctAnswers)
            }
            currentQuestionAtrapameSePodes = questionList.random()
            questionTV.text = currentQuestionAtrapameSePodes.question
            setStepsFragment(correctAnswers)
        } else {
            Intent(this, AtrapameSePodesResultActivity::class.java).also {
                it.putExtra(AtrapameSePodesConstants.SCORE, questionCounter)
                startActivity(it)
                finish()
            }
        }
    }

    private fun checkButtonClickListener() {
        questionCounter++

        if (removeAccents(userAnswerText.text.toString()) == currentQuestionAtrapameSePodes.answer) {
            correctAnswers++
            userAnswerText.text.clear()
            showNextQuestion()
        } else {
            Toast.makeText(this@AtrapameSePodesQuestionActivity, "Resposta incorrecta, ${currentQuestionAtrapameSePodes.answer}", Toast.LENGTH_SHORT).show()
            userAnswerText.text.clear()
            showNextQuestion(true)
        }
    }

    private fun setStepsFragment(level: Int) {
        // Settear el stepsFragment
        stepsFragment = supportFragmentManager.findFragmentById(R.id.atrapame_se_podes_level_fragment_container) as AtrapameSePodesStepsFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            stepsFragment.setStepsImage(level)
        }.commit()
    }
}