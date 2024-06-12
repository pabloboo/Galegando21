package com.galegando21.day16Anagramas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.galegando21.R
import com.galegando21.model.WordDefinition
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.PalabrasBasicasConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnagramasGameActivity : AppCompatActivity() {
    private lateinit var rachaActualTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var definicionTextView: TextView
    private lateinit var hintTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var checkAnswerButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    private var rachaActual = 0
    private var anagram = ""
    private var solution = ""
    private var definition = ""
    private var countDownTimer: CountDownTimer? = null

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var modo = "facil"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_game)

        rachaActualTextView = findViewById(R.id.rachaActualAnagramasTextView)
        timerTextView = findViewById(R.id.anagramas_timer_tv)
        definicionTextView = findViewById(R.id.definitionHintAnagramaTextView)
        hintTextView = findViewById(R.id.hintAnagramaTextView)
        answerEditText = findViewById(R.id.answerAnagramaEditText)
        answerEditText.filters = arrayOf(InputFilter.AllCaps())
        checkAnswerButton = findViewById(R.id.checkAnswerAnagramasBtn)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        setBanner(this, R.string.anagramas)

        modo = intent.getStringExtra("modo") ?: "facil"

        getWord()

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

    }

    private fun getWord() {
        loadingProgressBar.visibility = View.VISIBLE
        coroutineScope.launch {
            // Cancelar el temporizador si está corriendo
            countDownTimer?.cancel()

            checkAnswerButton.isEnabled = false
            answerEditText.isEnabled = false

            // Obtener 1 palabra aleatoria
            var wordsDefinitions = listOf<WordDefinition>()
            when (modo) {
                "facil" -> {
                    wordsDefinitions = PalabrasBasicasConstants.getPalabrasBasicasWordDefinitions(this@AnagramasGameActivity)
                }
                "dificil" -> {
                    wordsDefinitions = DigalegoConstants.getWordDefinitions(this@AnagramasGameActivity)
                }
            }
            val wordsFiltered = withContext(Dispatchers.Default) {
                wordsDefinitions.filter { it.palabra.length in 4..7 && it.definicion != null }
            }
            val randomWordDefinition = wordsFiltered.shuffled().take(1)[0]
            solution = randomWordDefinition.palabra
            definition = randomWordDefinition.definicion!!
            Log.d("AnagramasGameActivity", "Solution: $solution")

            // Crear el anagrama
            val shuffledChars = solution.toCharArray().toList().shuffled()
            anagram = shuffledChars.joinToString("")

            hintTextView.text = anagram
            definicionTextView.text = definition

            checkAnswerButton.isEnabled = true
            answerEditText.isEnabled = true
            loadingProgressBar.visibility = View.GONE
            // Inicializar nuevo temporizador
            countDownTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    timerTextView.text = "$seconds:00"
                }

                override fun onFinish() {
                    finalizarXogo()
                }
            }.start()
            setOnBackPressed(this@AnagramasGameActivity, AnagramasInicioActivity::class.java, countDownTimer)
        }
    }

    private fun checkAnswer() {
        val answer = answerEditText.text.toString()
        if (answer == solution) {
            rachaActual++
            getWord()
            answerEditText.text.clear()
        } else {
            rachaActual = 0
        }
        rachaActualTextView.text = "Racha: $rachaActual"
    }

    private fun finalizarXogo() {
        countDownTimer?.cancel()
        Intent(this, AnagramasResultsActivity::class.java).also {
            it.putExtra("ANAGRAMAS_SCORE", rachaActual)
            startActivity(it)
            finish()
        }
    }
}