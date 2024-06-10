package com.galegando21.day12AdivinhaPersonaxe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaPersonaxe
import com.galegando21.utils.AdivinhaPersonaxeConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AdivinhaPersonaxeGameActivity : AppCompatActivity() {
    private lateinit var cluesContainer: LinearLayout
    private lateinit var answerEditText: EditText
    private lateinit var checkAnswerButton: Button

    private val clues = mutableListOf<TextView>()
    private lateinit var question: QuestionAdivinhaPersonaxe
    private var currentClueIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_personaxe_game)

        cluesContainer = findViewById(R.id.cluesContainer)
        answerEditText = findViewById(R.id.answerAdivinhaPersonaxeEditText)
        answerEditText.filters = arrayOf(InputFilter.AllCaps())
        checkAnswerButton = findViewById(R.id.checkAnswerAdivinhaPersonaxeBtn)

        setBanner(this, R.string.adivinha_o_personaxe)

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

        generatePersonaxeQuestion()

        setOnBackPressed(this, AdivinhaPersonaxeInicioActivity::class.java)

    }

    private fun generatePersonaxeQuestion() {
        // Obtiene una pregunta aleatoria
        question = AdivinhaPersonaxeConstants.getQuestions().random()

        // Agregar la primera pista
        addClue(question.hints[currentClueIndex])
    }

    private fun showNextClue() {
        // Incrementa el índice de la pista actual
        currentClueIndex++

        // Verifica si hay más pistas disponibles
        if (currentClueIndex < question.hints.size) {
            // Agrega la siguiente pista
            addClue(question.hints[currentClueIndex])
        } else {
            // Muestra un mensaje si no hay más pistas disponibles
            Toast.makeText(this, "Non hai máis pistas dispoñibles", Toast.LENGTH_SHORT).show()
            finalizarXogo(false)
        }
    }

    private fun showAllLeftClues() {
        currentClueIndex++
        if (currentClueIndex >= question.hints.size) {
            return
        }
        // Muestra todas las pistas restantes, esperando 500ms entre cada addClue
        question.hints.subList(currentClueIndex, question.hints.size).forEachIndexed { index, hint ->
            cluesContainer.postDelayed({
                addClue(hint, true)
            }, index * 500L)
        }
    }

    private fun addClue(clue: String, gameWon: Boolean = false) {
        val newClue = TextView(this).apply {
            text = clue
            textSize = 24f
            setTextColor(Color.WHITE)
            if (gameWon) {
                setBackgroundColor(resources.getColor(R.color.correctGreen, null))
            } else {
                setBackgroundColor(resources.getColor(R.color.primaryBlue, null))
            }
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        clues.add(newClue)

        // Agrega la nueva pista al final del LinearLayout
        cluesContainer.addView(newClue)

        // Crea una animación de desplazamiento desde la parte inferior de la pantalla
        val animation = TranslateAnimation(0f, 0f, cluesContainer.height.toFloat(), 0f).apply {
            duration = 500
            fillAfter = true // Mantiene la vista en la posición final después de la animación
        }

        // Inicia la animación
        newClue.startAnimation(animation)
    }

    private fun checkAnswer() {
        if (answerEditText.text.toString() == question.answer) {
            showAllLeftClues()
            finalizarXogo(true)
        } else {
            showNextClue()
            answerEditText.text.clear()
        }
    }

    private fun finalizarXogo(xogoGanhado: Boolean) {
        checkAnswerButton.text = "Rematar"
        checkAnswerButton.setOnClickListener {
            Intent(this, AdivinhaPersonaxeResultsActivity::class.java).also {
                if (xogoGanhado) {
                    it.putExtra(AdivinhaPersonaxeConstants.ADIVINHA_PERSONAXE_SCORE, currentClueIndex)
                }
                startActivity(it)
                finish()
            }
        }
    }
}