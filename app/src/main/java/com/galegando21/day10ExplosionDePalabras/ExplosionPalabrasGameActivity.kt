package com.galegando21.day10ExplosionDePalabras

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.DictionaryConstants
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
    private lateinit var scoreTextView: TextView

    private var totalWidthLetters = 0
    private val fallingLetters = mutableListOf<String>()
    private var seconds = 0
    private var countDownTimer: CountDownTimer? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var words = listOf<String>()

    private val letters_frequency = mapOf(
        'A' to 0.125, 'K' to 0.008, 'T' to 0.0442, 'B' to 0.0127, 'L' to 0.0584, 'U' to 0.04, 'C' to 0.0443,
        'M' to 0.0261, 'V' to 0.0098, 'D' to 0.0514, 'N' to 0.0709, 'W' to 0.0003, 'E' to 0.1324, 'Ñ' to 0.0022,
        'X' to 0.0019, 'F' to 0.0079, 'O' to 0.0898, 'Y' to 0.0079, 'G' to 0.0117, 'P' to 0.0275, 'Z' to 0.0042,
        'H' to 0.0081, 'Q' to 0.0083, 'I' to 0.0691, 'R' to 0.0662, 'J' to 0.0045, 'S' to 0.0744
    )
    private val vogais = listOf('A', 'E', 'I', 'O', 'U')
    val consonantes = letters_frequency.keys.filterNot { it in vogais }
    val cumulativeVowels = createCumulativeFrequencyList(vogais)
    var cumulativeVogaisMax = 0.0
    val cumulativeConsonants = createCumulativeFrequencyList(consonantes)
    var cumulativeConsoantesMax = 0.0
    private var recontoVogais = 0
    private var recontoConsoantes = 0
    private var score = 0
    private var dificultade = "facil"
    private var segundosEntreLetras = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosion_palabras_game)

        lettersDisplay = findViewById(R.id.lettersContainer)
        wordInput = findViewById(R.id.wordInputExplosionPalabras)
        wordInput.filters = arrayOf(InputFilter.AllCaps())
        submitWordButton = findViewById(R.id.submitWordButtonExplosionPalabras)
        scoreTextView = findViewById(R.id.pointsCounterExplosionPalabras)

        dificultade = intent.getStringExtra("dificultade") ?: "facil"
        if (dificultade == "facil") {
            segundosEntreLetras = 3
        } else {
            segundosEntreLetras = 1
        }

        words = DictionaryConstants.getWords(this)

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
                if (seconds % segundosEntreLetras == 0) {
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

                            if (totalWidthLetters < (lettersDisplay.width - 2 * newLetterWidth)) {
                                fallingLetters.add(letter)
                                lettersDisplay.addView(newLetterTextView)
                                startFallAnimation(newLetterTextView, 3000)
                            } else {
                                Intent(this@ExplosionPalabrasGameActivity, ExplosionPalabrasResultsActivity::class.java).apply {
                                    putExtra("PUNTOS_EXPLOSION_PALABRAS", score)
                                    putExtra("dificultade", dificultade)
                                    startActivity(this)
                                    finish()
                                    countDownTimer?.cancel()
                                }
                            }
                        }
                    }
                }
            }

            override fun onFinish() {}
        }.start()

        setOnBackPressed(this, ExplosionPalabrasInicioActivity::class.java, countDownTimer)
    }

    // Función para generar listas de probabilidades acumuladas
    fun createCumulativeFrequencyList(letters: List<Char>): List<Pair<Char, Double>> {
        var cumulative = 0.0
        return letters.map { letter ->
            cumulative += letters_frequency[letter] ?: 0.0
            if (letter in vogais) {
                cumulativeVogaisMax = cumulative
            } else {
                cumulativeConsoantesMax = cumulative
            }
            letter to cumulative
        }
    }

    fun generateLetter(): String {
        val isVowel: Boolean

        if (recontoVogais + recontoConsoantes >= 7) {
            // Resetear los contadores
            recontoVogais = 0
            recontoConsoantes = 0
        }

        if (recontoVogais >= 3) {
            // Ya hay suficientes vocales, forzar una consonante
            isVowel = false
        } else if (recontoConsoantes >= 4) {
            // Ya hay suficientes consonantes, forzar una vocal
            isVowel = true
        } else {
            // Decidir aleatoriamente si la letra será vocal o consonante
            isVowel = Random.nextBoolean()
        }

        return if (isVowel) {
            // Generar una vocal basada en las frecuencias relativas
            recontoVogais++
            selectLetterBasedOnFrequency(cumulativeVowels, true).toString()
        } else {
            // Generar una consonante basada en las frecuencias relativas
            recontoConsoantes++
            selectLetterBasedOnFrequency(cumulativeConsonants, false).toString()
        }
    }

    // Función para seleccionar una letra basada en las frecuencias acumuladas
    fun selectLetterBasedOnFrequency(frequencies: List<Pair<Char, Double>>, vowel: Boolean): Char {
        var randomValue = 0.0
        if (vowel) {
            randomValue = Random.nextDouble(cumulativeVogaisMax)
        } else {
            randomValue = Random.nextDouble(cumulativeConsoantesMax)
        }

        var letter = ' '
        do {
            letter = frequencies.first { it.second >= randomValue }.first
        } while (letter !in ALFABETO)

        return letter
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
        val inputWord = wordInput.text.toString().trim()
        var validWord = true
        for (letter in inputWord) {
            if (letter.toString() !in fallingLetters) {
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
                scoreTextView.text = "Puntos: $score"
                totalWidthLetters = recalculateTotalWidthLetters()
            } else {
                Toast.makeText(this, "A palabra non existe", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usa só as letras mostradas", Toast.LENGTH_SHORT).show()
        }

        wordInput.text.clear()
    }

    fun recalculateTotalWidthLetters(): Int {
        var totalWidthLettersAux = 0
        for (i in 0 until lettersDisplay.childCount) {
            val child = lettersDisplay.getChildAt(i)
            if (child is TextView) {
                val bounds = Rect()
                child.paint.getTextBounds(child.text.toString(), 0, child.text.length, bounds)
                val childWidth = bounds.width()
                totalWidthLettersAux += childWidth
            }
        }
        return totalWidthLettersAux
    }

    fun startFallAnimation(view: View, duration: Long) {
        val anim = ObjectAnimator.ofFloat(view, "translationY", 0f, (lettersDisplay.height.toFloat()-(0.1*lettersDisplay.height)).toFloat())
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