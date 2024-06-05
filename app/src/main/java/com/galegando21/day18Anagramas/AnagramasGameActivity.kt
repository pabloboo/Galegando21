package com.galegando21.day18Anagramas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AforcadoGameConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AnagramasGameActivity : AppCompatActivity() {
    private lateinit var rachaActualTextView: TextView
    private lateinit var hintTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var checkAnswerButton: Button

    private var rachaActual = 0
    private var anagram = ""
    private var solution = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_game)

        rachaActualTextView = findViewById(R.id.rachaActualAnagramasTextView)
        hintTextView = findViewById(R.id.hintAnagramaTextView)
        answerEditText = findViewById(R.id.answerAnagramaEditText)
        answerEditText.filters = arrayOf(InputFilter.AllCaps())
        checkAnswerButton = findViewById(R.id.checkAnswerAnagramasBtn)

        setBanner(this, R.string.anagramas)

        getWord()

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

        setOnBackPressed(this, AnagramasInicioActivity::class.java)
    }

    fun getWord() {
        // Obtener 1 palabra aleatoria
        val words = AforcadoGameConstants.getWords()
        val wordsFiltered = words.filter { it.length >= 4 }
        val randomWord = wordsFiltered.shuffled().take(1)[0]
        solution = randomWord
        Log.d("AnagramasGameActivity", "Solution: $solution")

        // Crear el anagrama
        val shuffledChars = randomWord.toCharArray().toList().shuffled()
        anagram = shuffledChars.joinToString("")
        hintTextView.text = anagram
    }

    fun checkAnswer() {
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
}