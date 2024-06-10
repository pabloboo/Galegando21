package com.galegando21.day21ExplosionDePalabras

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class ExplosionPalabrasGameActivity : AppCompatActivity() {
    private lateinit var lettersDisplay: LinearLayout
    private lateinit var wordInput: EditText
    private lateinit var submitWordButton: Button

    private var totalWidthLetters = 0
    private val fallingLetters = mutableListOf<String>()
    private var seconds = 0
    private var countDownTimer: CountDownTimer? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var words = listOf<String>()
    private val vogais = listOf('A', 'E', 'I', 'O', 'U')
    private var recontoVogais = 0
    private var recontoConsoantes = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosion_palabras_game)

        lettersDisplay = findViewById(R.id.lettersContainer)
        wordInput = findViewById(R.id.wordInputExplosionPalabras)
        wordInput.filters = arrayOf(InputFilter.AllCaps())
        submitWordButton = findViewById(R.id.submitWordButtonExplosionPalabras)

        words = DigalegoConstants.getWords(this)

        submitWordButton.setOnClickListener {
            checkWord()
        }

        startGame()
    }

    private fun startGame() {
        countDownTimer?.cancel() // Cancela el timer anterior si existe

        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                seconds++
                if (seconds % 3 == 0) {
                    coroutineScope.launch {
                        val letter = generateLetter()
                        withContext(Dispatchers.Main) {
                            val newLetterTextView = createNewLetterTextView(letter)

                            val bounds = Rect()
                            newLetterTextView.paint.getTextBounds(
                                newLetterTextView.text.toString(),
                                0,
                                newLetterTextView.text.length,
                                bounds
                            )
                            val newLetterWidth = bounds.width()
                            totalWidthLetters += newLetterWidth

                            if (totalWidthLetters < lettersDisplay.width) {
                                fallingLetters.add(letter)
                                lettersDisplay.addView(newLetterTextView)
                                startFallAnimation(newLetterTextView, 3000)
                                Log.d("ExplosionPalabras", "Anchura total de las letras: $totalWidthLetters, Anchura del contenedor de letras: ${lettersDisplay.width}")
                                Log.d("ExplosionPalabras", "Tamaño falling letters: ${fallingLetters.size}, Tamaño lettersDisplay: ${lettersDisplay.childCount}")
                            }
                        }
                    }
                }
            }

            override fun onFinish() {}
        }.start()

        setOnBackPressed(this, MainActivity::class.java, countDownTimer)
    }

    private fun generateLetter(): String {
        val isVogal: Boolean

        if (recontoVogais + recontoConsoantes >= 7) {
            // Resetear los contadores
            recontoVogais = 0
            recontoConsoantes = 0
        }

        if (recontoVogais >= 3) {
            // Ya hay suficientes vocales, forzar una consonante
            isVogal = false
        } else if (recontoConsoantes >= 4) {
            // Ya hay suficientes consonantes, forzar una vocal
            isVogal = true
        } else {
            // Decidir aleatoriamente si la letra será vocal o consonante
            isVogal = Random.nextBoolean()
        }

        var letter: Char
        if (isVogal) {
            // Generar una vocal aleatoria
            letter = vogais[Random.nextInt(vogais.size)]
            recontoVogais++
        } else {
            // Generar una consonante aleatoria
            do {
                letter = ALFABETO[Random.nextInt(ALFABETO.length)]
            } while (letter in vogais)
            recontoConsoantes++
        }

        return letter.toString()
    }

    private fun createNewLetterTextView(letter: String): TextView {
        val newLetterTextView = TextView(this)
        newLetterTextView.text = letter
        newLetterTextView.textSize = 24f
        newLetterTextView.setTextColor(Color.BLACK)
        return newLetterTextView
    }

    private fun checkWord() {
        // Comprobar si todos los caracteres de inputWord están en fallingLetters
        val inputWord = wordInput.text.toString()
        var validWord = true
        for (letter in inputWord) {
            if (letter.toString() !in fallingLetters) {
                Log.d("ExplosionPalabras", "La letra $letter no está en fallingLetters: $fallingLetters")
                validWord = false
                break
            }
        }

        if (validWord) {
            if (inputWord in words) {
                score += inputWord.length

                // Eliminar las letras de fallingLetters que están en inputWord
                var letrasBorradas = mutableListOf<Char>()
                for (letter in inputWord) {
                    if ((letter.toString() in fallingLetters) && (letter !in letrasBorradas)) {
                        fallingLetters.remove(letter.toString())
                        letrasBorradas.add(letter)
                    }
                }

                // Eliminar las letras de inputWord del LinearLayout
                letrasBorradas = mutableListOf<Char>()
                var i = 0
                while (i <= lettersDisplay.childCount) {
                    val child = lettersDisplay.getChildAt(i)
                    if ((child is TextView) && (child.text.toString() in inputWord) && (child.text[0] !in letrasBorradas)) {
                        lettersDisplay.removeViewAt(i)
                        letrasBorradas.add(child.text[0])
                        // Non incrementar i porque se elimina unha vista
                    } else {
                        i++
                    }
                }
                Toast.makeText(this, "Palabra válida! Puntuación: $score", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "A palabra non existe", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usa só as letras mostradas", Toast.LENGTH_SHORT).show()
        }

        wordInput.text.clear()
    }

    fun startFallAnimation(view: View, duration: Long) {
        val anim = ObjectAnimator.ofFloat(view, "translationY", 0f, lettersDisplay.height.toFloat() - view.height)
        anim.duration = duration
        anim.repeatCount = 0
        anim.interpolator = LinearInterpolator()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                view.y = (lettersDisplay.height.toFloat() - view.height).coerceAtLeast(0f)
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
        anim.start()
    }
}