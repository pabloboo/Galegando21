package com.galegando21.day11AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.model.QuestionAgoraCaigo
import com.galegando21.utils.AgoraCaigoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlin.random.Random

class AgoraCaigoQuestionActivity : AppCompatActivity() {
    private lateinit var questionTV: TextView
    private lateinit var hintLayout: LinearLayout
    private lateinit var userAnswerET: EditText
    private lateinit var checkButton: ImageButton

    private lateinit var comodin1: ImageView
    private lateinit var comodin2: ImageView
    private lateinit var comodin3: ImageView

    private var correctAnswers = 0
    private var errors = 0
    private lateinit var questionList: List<QuestionAgoraCaigo>
    private lateinit var currentQuestionAgoraCaigo: QuestionAgoraCaigo

    private lateinit var agoraCaigoTimerTv : TextView
    private var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_caigo_question)

        questionTV = findViewById(R.id.agora_caigo_question_tv)
        hintLayout = findViewById(R.id.agora_caigo_hint_ll)
        userAnswerET = findViewById(R.id.agora_caigo_answer_et)
        userAnswerET.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_agora_caigo)
        comodin1 = findViewById(R.id.agora_caigo_comodin1)
        comodin2 = findViewById(R.id.agora_caigo_comodin2)
        comodin3 = findViewById(R.id.agora_caigo_comodin3)
        agoraCaigoTimerTv = findViewById(R.id.agora_caigo_timer_tv)

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        questionList = AgoraCaigoConstants.getQuestions()

        showNextQuestion()

        setBanner(this, R.string.agora_caigo)

        setOnBackPressed(this, AgoraCaigoInicioActivity::class.java)
    }

    private fun showNextQuestion() {
        hintLayout.removeAllViews()
        val random = Random.nextInt(0, questionList.size)
        currentQuestionAgoraCaigo = questionList[random]
        questionTV.text = currentQuestionAgoraCaigo.question
        val hintText = currentQuestionAgoraCaigo.hint
        hintText.forEach { char ->
            val textView = TextView(this).apply {
                text = char.toString()
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
                setBackgroundResource(R.color.canela)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(4, 4, 4, 4)
                }
            }
            hintLayout.addView(textView)
        }
        userAnswerET.text.clear()

        // Cancelar el temporizador si está corriendo
        countDownTimer?.cancel()
        // Inicializar nuevo temporizador
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                agoraCaigoTimerTv.text = "$seconds:00"
            }

            override fun onFinish() {
                errors++
                hideComodines(errors)
                Toast.makeText(this@AgoraCaigoQuestionActivity, "${currentQuestionAgoraCaigo.solution}, Incorrecto!", Toast.LENGTH_SHORT).show()
                if (errors < 4) {
                    showNextQuestion()
                } else {
                    Intent(this@AgoraCaigoQuestionActivity, AgoraCaigoResultsActivity::class.java).also {
                        it.putExtra(AgoraCaigoConstants.SCORE, correctAnswers)
                        startActivity(it)
                    }
                }
            }
        }.start()
    }

    private fun checkButtonClickListener() {
        val userAnswer = userAnswerET.text.toString()
        if (userAnswer == currentQuestionAgoraCaigo.solution) {
            correctAnswers++
            Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show()
            showNextQuestion()
        } else {
            Toast.makeText(this, "Segue probando!", Toast.LENGTH_SHORT).show()
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