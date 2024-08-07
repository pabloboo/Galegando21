package com.galegando21.day05AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaAnoFoto
import com.galegando21.utils.AdivinhaAnoFotoConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.google.android.material.slider.Slider
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetSequence
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class AdivinhaAnoFotoGameActivity : AppCompatActivity() {
    private lateinit var progressBar : ProgressBar
    private lateinit var textViewProgress : TextView
    private lateinit var imageView : ImageView
    private lateinit var animacionPuntos : ImageView
    private lateinit var descriptionSolution : TextView
    private lateinit var yearSolution : Slider
    private lateinit var checkButton : Button

    private var questionsCounter = 1
    private lateinit var questionsList: MutableList<QuestionAdivinhaAnoFoto>
    private lateinit var currentQuestion: QuestionAdivinhaAnoFoto
    private var answered = false

    private var total_questions = 5
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_game)

        progressBar = findViewById(R.id.adivinhaAnoFoto_progress_bar)
        textViewProgress = findViewById(R.id.adivinhaAnoFoto_text_view_progress)
        imageView = findViewById(R.id.adivinhaAnoFoto_foto_ImageView)
        animacionPuntos = findViewById(R.id.adivinhaAnoFoto_pts_animacion_iv)
        descriptionSolution = findViewById(R.id.adivinhaAnoFoto_solution_description_tv)
        yearSolution = findViewById(R.id.adivinhaAnoFoto_slider)
        checkButton = findViewById(R.id.check_btn_AdivinhaAnoFoto)

        setBanner(this, R.string.adivinha_ano_foto)

        questionsList = AdivinhaAnoFotoConstants.getQuestions()

        checkButton.setOnClickListener {
            checkAnswer()
        }

        showNextQuestion()

        setOnBackPressed(this, AdivinhaAnoFotoInicioActivity::class.java)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.GAMES_STATE, MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_FIRST_TIME, true)

        if (isFirstRun) {
            MaterialTapTargetSequence()
                .addPrompt(
                    MaterialTapTargetPrompt.Builder(this)
                        .setTarget(R.id.adivinhaAnoFoto_slider)
                        .setPrimaryText("Marca o ano")
                        .setSecondaryText("Desliza o control para marcar o ano que cres que é correcto")
                )
                .addPrompt(
                    MaterialTapTargetPrompt.Builder(this)
                        .setTarget(R.id.check_btn_AdivinhaAnoFoto)
                        .setPrimaryText("Comproba")
                        .setSecondaryText("Preme este botón para comprobar a túa resposta")
                )
                .setSequenceCompleteListener {
                    sharedPreferences.edit().putBoolean(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_FIRST_TIME, false).apply()
                }
                .show()
        }
    }

    private fun showNextQuestion() {
        if (questionsCounter <= total_questions) {
            currentQuestion = questionsList.random()
            questionsList = (questionsList - currentQuestion).toMutableList() // Eliminar la pregunta de la lista
            progressBar.progress = questionsCounter
            textViewProgress.text = "$questionsCounter/$total_questions"
            imageView.setImageResource(currentQuestion.image)
            descriptionSolution.visibility = TextView.GONE
            yearSolution.clearFocus()
            answered = false
            checkButton.text = getString(R.string.check)
            questionsCounter++
        } else {
            Intent(this@AdivinhaAnoFotoGameActivity, AdivinhaAnoFotoResultsActivity::class.java).also {
                Log.d("SCORE", score.toString())
                it.putExtra(AdivinhaAnoFotoConstants.SCORE, score)
                startActivity(it)
                finish()
            }
        }
    }

    fun checkAnswer() {
        if (!answered) {
            val answer = yearSolution.value.toString()
            if (answer.isNotEmpty()) {
                val answerInt = answer.toFloat().roundToInt()
                score += calculateScore(answerInt, currentQuestion.answerYear)
                answered = true
                descriptionSolution.text = "${currentQuestion.answerYear}: ${currentQuestion.description}"
                descriptionSolution.visibility = TextView.VISIBLE
                checkButton.text = getString(R.string.seguinte)
            }
        } else {
            showNextQuestion()
        }
    }

    fun calculateScore(guessedYear: Int, correctAnswerYear: Int) : Int {
        val difference = (guessedYear - correctAnswerYear).absoluteValue
        var score = 0
        when (difference) {
            in 0..0 -> score = 10
            in 1 .. 1 -> score = 8
            in 2..3 -> score = 6
            in 4..5 -> score = 4
            in 6 .. 10 -> score = 2
            in 11 .. 20 -> score = 1
            else -> score = 0
        }

        // Mostrar score
        puntosAnimation(score)
        return score
    }

    private fun puntosAnimation(pts: Int) {
        when (pts) {
            10 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_10pts)
            8 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_8pts)
            6 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_6pts)
            4 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_4pts)
            2 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_2pts)
            1 -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_1pts)
            else -> animacionPuntos.setImageResource(R.drawable.adivinhar_ano_foto_0pts)
        }

        animacionPuntos.animate().apply {
            duration = 2000
            alpha(1f)
            scaleXBy(1f)
            scaleYBy(1f)
        }.withEndAction {
            animacionPuntos.animate().apply {
                duration = 1000
                alpha(0f)
                scaleXBy(-1f)
                scaleYBy(-1f)
            }.start()
        }
    }
}