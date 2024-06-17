package com.galegando21.day18PalabrasEncadeadas

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.galegando21.R
import com.galegando21.utils.DictionaryConstants
import com.galegando21.utils.removeAccents
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PalabrasEncadeadasGameActivity : AppCompatActivity() {
    private lateinit var gameLayout: ConstraintLayout
    private lateinit var timerTextView: TextView
    private lateinit var numeroPalabrasTextView: TextView
    private lateinit var inputWordEditText: EditText
    private lateinit var inputButton: Button

    var palabrasPosibles = mutableListOf<String>()
    var palabrasEncontradas = mutableListOf<String>()
    var lastSilaba = ""
    private var countDownTimer: CountDownTimer? = null
    var colorAnimationBoolean = false
    var backgroundColor = R.color.white

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabras_encadeadas_game)

        gameLayout = findViewById(R.id.palabras_encadeadas_game_layout)
        timerTextView = findViewById(R.id.palabras_encadeadas_timer_tv)
        numeroPalabrasTextView = findViewById(R.id.palabras_encadeadas_numero_palabras)
        inputWordEditText = findViewById(R.id.palabras_encadeadas_edit_text)
        inputWordEditText.filters = arrayOf(InputFilter.AllCaps())
        inputButton = findViewById(R.id.palabras_encadeadas_button)

        setBanner(this, R.string.palabras_encadeadas)

        iniciarXogo()

        inputButton.setOnClickListener {
            encadearPalabra()
        }

    }

    private fun iniciarXogo() {
        coroutineScope.launch {
            palabrasPosibles = withContext(Dispatchers.Default) {
                DictionaryConstants.getWords(this@PalabrasEncadeadasGameActivity).toMutableList()
            }
            var firstWord = palabrasPosibles.random()
            while (firstWord.length < 4) {
                firstWord = palabrasPosibles.random()
            }
            lastSilaba = ultimaSilaba(firstWord)
            inputWordEditText.setText(lastSilaba)

            startTimer()
        }
    }

    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = (millisUntilFinished / 1000).toString()
                changeBackgroundColor(millisUntilFinished)
            }

            override fun onFinish() {
                Intent(this@PalabrasEncadeadasGameActivity, PalabrasEncadeadasResultsActivity::class.java).also {
                    it.putExtra("PALABRAS_ENCADEADAS_SCORE", palabrasEncontradas.size)
                    startActivity(it)
                    countDownTimer?.cancel()
                    finish()
                }
            }
        }.start()
        setOnBackPressed(this, PalabrasEncadeadasInicioActivity::class.java, countDownTimer)
    }

    private fun changeBackgroundColor(millisUntilFinished: Long) {
        var colorFrom: Int = Color.WHITE
        var colorTo: Int = Color.WHITE
        when (millisUntilFinished) {
            in 3000..4000 -> {
                colorFrom = resources.getColor(R.color.yellow, null)
                colorTo = resources.getColor(R.color.errorRed, null)
                colorAnimationBoolean = true
                backgroundColor = R.color.errorRed
            }
            in 5000..6000 -> {
                colorFrom = resources.getColor(R.color.white, null)
                colorTo = resources.getColor(R.color.yellow, null)
                colorAnimationBoolean = true
                backgroundColor = R.color.yellow
            }
            else -> {
                gameLayout.setBackgroundColor(resources.getColor(backgroundColor, null))
                colorAnimationBoolean = false
            }
        }

        if (colorAnimationBoolean) {
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
            colorAnimation.duration = 1000 // milliseconds
            colorAnimation.addUpdateListener { animator -> gameLayout.setBackgroundColor(animator.animatedValue as Int) }
            colorAnimation.start()
            colorAnimationBoolean = false
        }
    }

    private fun ultimaSilaba(palabra: String): String {
        val silabas = Silabas.process(palabra).getSyllables()
        return silabas.last().toString()
    }

    private fun encadearPalabra() {
        val word = removeAccents(inputWordEditText.text.toString())
        if (word.isEmpty()) {
            return
        }
        if (word !in palabrasPosibles) {
            Toast.makeText(this, "Non existe a palabra", Toast.LENGTH_SHORT).show()
            return
        }
        if (word in palabrasEncontradas) {
            Toast.makeText(this, "A palabra xa foi atopada", Toast.LENGTH_SHORT).show()
            return
        }
        if (!word.startsWith(lastSilaba)) {
            Toast.makeText(this, "A palabra non comeza pola última sílaba", Toast.LENGTH_SHORT).show()
            return
        }
        countDownTimer?.cancel()
        palabrasEncontradas.add(word)
        lastSilaba = ultimaSilaba(word)
        inputWordEditText.setText(lastSilaba)
        numeroPalabrasTextView.text = palabrasEncontradas.size.toString()
        inputWordEditText.setSelection(inputWordEditText.text.length)
        backgroundColor = R.color.white
        startTimer()
    }
}