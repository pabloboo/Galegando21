package com.galegando21.day11AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.QuestionAgoraCaigo
import com.galegando21.utils.AgoraCaigoConstants
import kotlin.random.Random

class AgoraCaigoQuestionActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var questionTV: TextView
    private lateinit var hintTV: TextView
    private lateinit var userAnswerET: EditText
    private lateinit var checkButton: ImageButton

    private lateinit var comodin1: ImageView
    private lateinit var comodin2: ImageView
    private lateinit var comodin3: ImageView

    private var correctAnswers = 0
    private var errors = 0
    private lateinit var questionList: List<QuestionAgoraCaigo>
    private lateinit var currentQuestionAgoraCaigo: QuestionAgoraCaigo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_caigo_question)

        questionTV = findViewById(R.id.agora_caigo_question_tv)
        hintTV = findViewById(R.id.agora_caigo_hint_tv)
        userAnswerET = findViewById(R.id.agora_caigo_answer_et)
        userAnswerET.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_agora_caigo)
        comodin1 = findViewById(R.id.agora_caigo_comodin1)
        comodin2 = findViewById(R.id.agora_caigo_comodin2)
        comodin3 = findViewById(R.id.agora_caigo_comodin3)

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        questionList = AgoraCaigoConstants.getQuestions()

        showNextQuestion()

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.agora_caigo))
        }.commit()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AgoraCaigoQuestionActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun showNextQuestion() {
        val random = Random.nextInt(0, questionList.size)
        currentQuestionAgoraCaigo = questionList[random]
        questionTV.text = currentQuestionAgoraCaigo.question
        hintTV.text = currentQuestionAgoraCaigo.hint
        userAnswerET.text.clear()
    }

    private fun checkButtonClickListener() {
        val userAnswer = userAnswerET.text.toString()
        if (userAnswer == currentQuestionAgoraCaigo.solution) {
            correctAnswers++
            Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show()
            showNextQuestion()
        } else {
            errors++
            hideComodines(errors)
            Toast.makeText(this, "${currentQuestionAgoraCaigo.solution}, Incorrecto!", Toast.LENGTH_SHORT).show()
            if (errors < 4) {
                showNextQuestion()
            } else {
                Intent(this, MainActivity::class.java).also {
                    it.putExtra(AgoraCaigoConstants.SCORE, correctAnswers)
                    startActivity(it)
                }
            }
        }
    }

    private fun hideComodines(errors: Int) {
        when (errors) {
            1 -> {
                comodin1.visibility = View.GONE
            }
            2 -> {
                comodin2.visibility = View.GONE
            }
            3 -> {
                comodin3.visibility = View.GONE
            } else -> {}
        }
    }
}