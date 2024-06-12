package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Chronometer
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.model.QuestionPasagalego
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.PalabrasBasicasConstants
import com.galegando21.utils.PasagalegoConstants
import com.galegando21.utils.PasagalegoConstants.getPasagalegoQuestions
import com.galegando21.utils.removeAccents
import com.galegando21.utils.setOnBackPressed
import java.lang.StringBuilder
import kotlin.random.Random

class PasagalegoQuestionActivity : AppCompatActivity() {
    private lateinit var roscoView: RoscoView
    private lateinit var letter_tv : TextView
    private lateinit var question_tv : TextView
    private lateinit var correctAnswersTv : TextView
    private lateinit var errorAnswersTv: TextView
    private lateinit var chronometer: Chronometer
    private lateinit var userAnswerText : EditText
    private lateinit var checkButton : ImageButton
    private lateinit var pasapalabraButton : ImageButton

    private var letters = StringBuilder(ALFABETO)
    private var questionCounter = 0
    private var correctAnswers = 0
    private var errorAnswers = 0
    private lateinit var currentQuestionPasagalego : QuestionPasagalego

    private var questionMap = mutableMapOf<Char, QuestionPasagalego>()

    private var modo = "diccionario"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_question)

        roscoView = findViewById(R.id.roscoView)
        letter_tv = findViewById(R.id.tv_pasagalego_letter)
        question_tv = findViewById(R.id.tv_pasagalego_question)
        correctAnswersTv = findViewById(R.id.correct_answers_tv)
        errorAnswersTv = findViewById(R.id.error_answers_tv)
        chronometer = findViewById(R.id.chronometer)
        userAnswerText = findViewById(R.id.pasagalego_answer)
        userAnswerText.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_pasagalego)
        pasapalabraButton = findViewById(R.id.pasapalabra_btn)

        modo = intent.getStringExtra("modo")  ?: "diccionario"

        initQuestions()

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }

        pasapalabraButton.setOnClickListener {
            pasapalabra()
        }

        errorAnswersTv.text = "0"
        correctAnswersTv.text = "0"
        chronometer.start()

        showNextQuestion()

        setOnBackPressed(this, PasagalegoInicioActivity::class.java)
    }

    private fun initQuestions() {
        when (modo) {
            "diccionario_facil" -> initQuestionsDiccionarioFacil()
            "diccionario" -> initQuestionsDiccionario()
            "orixinal" -> initQuestionsOrixinal()
            else -> initQuestionsDiccionario()
        }
    }

    private fun initQuestionsDiccionarioFacil() {
        // Cargar todas las palabras
        val allWords = PalabrasBasicasConstants.getPalabrasBasicasWordDefinitions(this)

        // Crear un mapa de letras a palabras
        val wordsByLetter = allWords.groupBy { it.palabra.first() }.toMutableMap()

        // Crear una lista de palabras que contienen la letra 'Ñ'
        val wordsWithNH = allWords.filter { it.palabra.contains('Ñ', ignoreCase = true) }
        wordsByLetter['Ñ'] = wordsWithNH

        // Seleccionar una palabra aleatoria para cada letra
        for (letter in ALFABETO) {
            val wordsForLetter = wordsByLetter[letter]
            if (!wordsForLetter.isNullOrEmpty()) {
                val randomWord = wordsForLetter[Random.nextInt(wordsForLetter.size)]
                questionMap[letter] = QuestionPasagalego(randomWord.definicion, randomWord.palabra)
            }
        }
    }

    private fun initQuestionsDiccionario() {
        // Cargar todas las palabras
        val allWords = DigalegoConstants.getWordDefinitions(this)

        // Crear un mapa de letras a palabras
        val wordsByLetter = allWords.groupBy { it.palabra.first() }.toMutableMap()

        // Crear una lista de palabras que contienen la letra 'Ñ'
        val wordsWithNH = allWords.filter { it.palabra.contains('Ñ', ignoreCase = true) }
        wordsByLetter['Ñ'] = wordsWithNH

        // Seleccionar una palabra aleatoria para cada letra
        for (letter in ALFABETO) {
            val wordsForLetter = wordsByLetter[letter]
            if (!wordsForLetter.isNullOrEmpty()) {
                val randomWord = wordsForLetter[Random.nextInt(wordsForLetter.size)]
                questionMap[letter] = QuestionPasagalego(randomWord.definicion, randomWord.palabra)
            }
        }
    }

    private fun initQuestionsOrixinal() {
        for (letter in ALFABETO) {
            val questionList = getPasagalegoQuestions(letter)
            val randomNumber = Random.nextInt(0, questionList.size)
            questionMap[letter] = questionList[randomNumber]
        }
    }

    private fun showNextQuestion() {
        if (letters.isNotEmpty()) {
            var position = questionCounter.mod(letters.length)
            val letter = letters[position]
            roscoView.setCurrentLetter(letter)
            currentQuestionPasagalego = questionMap.get(letter)!!
            if (letter.equals('Ñ')) {
                letter_tv.text = "Contén a letra 'Ñ'"
            } else {
                letter_tv.text = "Comeza pola letra '$letter'"
            }
            question_tv.text = currentQuestionPasagalego.question
            correctAnswersTv.text = correctAnswers.toString()
            errorAnswersTv.text = errorAnswers.toString()
        } else {
            chronometer.stop()
            Intent(this, PasagalegoResultActivity::class.java). also {
                it.putExtra(PasagalegoConstants.SCORE, correctAnswers)
                it.putExtra(PasagalegoConstants.ERRORS, errorAnswers)
                it.putExtra(PasagalegoConstants.TIME, chronometer.text)
                startActivity(it)
                finish()
            }
        }
    }

    private fun checkButtonClickListener() {
        if (questionCounter>=letters.length) {
            questionCounter = 0
        }

        if (removeAccents(userAnswerText.text.toString()) == currentQuestionPasagalego.answer) {
            correctAnswers++
            roscoView.setLetterStatus(letters[questionCounter], LetterStatus.CORRECT)
        } else {
            errorAnswers++
            roscoView.setLetterStatus(letters[questionCounter], LetterStatus.INCORRECT)
            Toast.makeText(this@PasagalegoQuestionActivity, "Resposta incorrecta, ${currentQuestionPasagalego.answer}", Toast.LENGTH_SHORT).show()
        }
        letters = letters.deleteCharAt(questionCounter)
        userAnswerText.text.clear()
        showNextQuestion()
    }

    private fun pasapalabra() {
        questionCounter++
        if (questionCounter>=letters.length) {
            questionCounter = 0
        }
        showNextQuestion()
    }
}