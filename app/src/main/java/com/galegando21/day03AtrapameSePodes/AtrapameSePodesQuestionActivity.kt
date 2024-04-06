package com.galegando21.day03AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.R
import com.galegando21.model.QuestionAtrapameSePodes
import com.galegando21.utils.AtrapameSePodesConstants
import com.galegando21.utils.AtrapameSePodesConstants.getAtrapameSePodesQuestions
import kotlin.random.Random

class AtrapameSePodesQuestionActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var stepsFragment: AtrapameSePodesStepsFragment

    private lateinit var questionTV: TextView
    private lateinit var userAnswerText: EditText
    private lateinit var checkButton: ImageButton

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

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapame_se_podes))
        }.commit()

        setStepsFragment(0)

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        showNextQuestion()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapameSePodesQuestionActivity, AtrapameSePodesInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun showNextQuestion() {
        if (correctAnswers < 5) {
            val questionList = getAtrapameSePodesQuestions()
            val randomNumber = Random.nextInt(0, questionList.size)
            currentQuestionAtrapameSePodes = questionList[randomNumber]
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
        if (userAnswerText.text.toString() == currentQuestionAtrapameSePodes.answer) {
            correctAnswers++
            Toast.makeText(this@AtrapameSePodesQuestionActivity, "Resposta correcta, ${currentQuestionAtrapameSePodes.answer}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@AtrapameSePodesQuestionActivity, "Resposta incorrecta, ${currentQuestionAtrapameSePodes.answer}", Toast.LENGTH_SHORT).show()
        }
        userAnswerText.text.clear()
        showNextQuestion()
    }

    private fun setStepsFragment(level: Int) {
        // Settear el stepsFragment
        stepsFragment = supportFragmentManager.findFragmentById(R.id.atrapame_se_podes_level_fragment_container) as AtrapameSePodesStepsFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            stepsFragment.setStepsImage(level)
        }.commit()
    }
}