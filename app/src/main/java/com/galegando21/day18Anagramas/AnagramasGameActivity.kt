package com.galegando21.day18Anagramas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.galegando21.R
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AnagramasGameActivity : AppCompatActivity() {
    private lateinit var rachaActualTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var hintTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var checkAnswerButton: Button
    private lateinit var finishButton: Button

    private var rachaActual = 0
    private var anagram = ""
    private var solution = ""
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_game)

        rachaActualTextView = findViewById(R.id.rachaActualAnagramasTextView)
        timerTextView = findViewById(R.id.anagramas_timer_tv)
        hintTextView = findViewById(R.id.hintAnagramaTextView)
        answerEditText = findViewById(R.id.answerAnagramaEditText)
        answerEditText.filters = arrayOf(InputFilter.AllCaps())
        checkAnswerButton = findViewById(R.id.checkAnswerAnagramasBtn)
        finishButton = findViewById(R.id.finishAnagramasBtn)

        setBanner(this, R.string.anagramas)

        getWord()

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

        finishButton.setOnClickListener {
            finalizarXogo()
        }

        setOnBackPressed(this, AnagramasInicioActivity::class.java)
    }

    private fun getWord() {
        // Obtener 1 palabra aleatoria
        val words = DigalegoConstants.getWords(this)
        val wordsFiltered = words.filter { it.length >= 4 && it.length <= 7 }
        val randomWord = wordsFiltered.shuffled().take(1)[0]
        solution = randomWord
        Log.d("AnagramasGameActivity", "Solution: $solution")

        // Crear el anagrama
        val shuffledChars = randomWord.toCharArray().toList().shuffled()
        anagram = shuffledChars.joinToString("")
        hintTextView.text = anagram

        // Cancelar el temporizador si está corriendo
        countDownTimer?.cancel()
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