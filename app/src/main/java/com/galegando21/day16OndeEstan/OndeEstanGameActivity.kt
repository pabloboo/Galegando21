package com.galegando21.day16OndeEstan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class OndeEstanGameActivity : AppCompatActivity() {
    private lateinit var palabraActualTextView: TextView
    private lateinit var palabra1TextView: TextView
    private lateinit var palabra2TextView: TextView
    private lateinit var palabra3TextView: TextView
    private lateinit var palabra4TextView: TextView
    private lateinit var palabra5TextView: TextView
    private lateinit var palabra6TextView: TextView
    private lateinit var palabra7TextView: TextView
    private lateinit var palabra8TextView: TextView
    private lateinit var palabra9TextView: TextView
    private lateinit var ondeEstanTimerTv: TextView

    private lateinit var palabras: List<String>
    private lateinit var palabrasOrdenCorrecto: List<String>
    private var aciertos = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onde_estan_game)

        setBanner(this, R.string.onde_estan)

        palabraActualTextView = findViewById(R.id.palabraActualOndeEstanTextView)
        palabra1TextView = findViewById(R.id.palabra1)
        palabra2TextView = findViewById(R.id.palabra2)
        palabra3TextView = findViewById(R.id.palabra3)
        palabra4TextView = findViewById(R.id.palabra4)
        palabra5TextView = findViewById(R.id.palabra5)
        palabra6TextView = findViewById(R.id.palabra6)
        palabra7TextView = findViewById(R.id.palabra7)
        palabra8TextView = findViewById(R.id.palabra8)
        palabra9TextView = findViewById(R.id.palabra9)
        ondeEstanTimerTv = findViewById(R.id.onde_estan_timer_tv)

        palabra1TextView.setOnClickListener {
            checkAnswer(palabra1TextView, palabras[0])
        }
        palabra2TextView.setOnClickListener {
            checkAnswer(palabra2TextView, palabras[1])
        }
        palabra3TextView.setOnClickListener {
            checkAnswer(palabra3TextView, palabras[2])
        }
        palabra4TextView.setOnClickListener {
            checkAnswer(palabra4TextView, palabras[3])
        }
        palabra5TextView.setOnClickListener {
            checkAnswer(palabra5TextView, palabras[4])
        }
        palabra6TextView.setOnClickListener {
            checkAnswer(palabra6TextView, palabras[5])
        }
        palabra7TextView.setOnClickListener {
            checkAnswer(palabra7TextView, palabras[6])
        }
        palabra8TextView.setOnClickListener {
            checkAnswer(palabra8TextView, palabras[7])
        }
        palabra9TextView.setOnClickListener {
            checkAnswer(palabra9TextView, palabras[8])
        }

        initializeGame()

        setOnBackPressed(this, OndeEstanInicioActivity::class.java)
    }

    private fun checkAnswer(textView: TextView, palabra: String) {
        if (palabra == palabrasOrdenCorrecto[aciertos]) {
            mostrarPalabra(textView, palabra)
            aciertos++
            if (aciertos == 9) {
                goResultsActivity(this, true)
            } else {
                palabraActualTextView.text = palabrasOrdenCorrecto[aciertos]
            }
        } else {
            //mostrar palabra durante unos segundos
            mostrarPalabraErronea(textView, palabra)
            textView.postDelayed({
                ocultarPalabras()
            }, 1000)
        }
    }

    private fun initializeGame() {
        palabras = DigalegoConstants.getWords(this).shuffled().take(9)
        palabrasOrdenCorrecto = palabras.shuffled()
        Log.d("OndeEstan", palabras.toString())

        ocultarPalabras()

        countDownTimer?.cancel() // Cancelar timer si ya estaba inicializado
        // Inicializar timer
        countDownTimer = object : CountDownTimer(90000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                ondeEstanTimerTv.text = "$seconds:00"
            }

            override fun onFinish() {
                Toast.makeText(this@OndeEstanGameActivity, "Tempo esgotado!", Toast.LENGTH_SHORT).show()
                goResultsActivity(this@OndeEstanGameActivity, false)
                ocultarPalabras()
            }
        }.start()
    }

    private fun mostrarPalabra(textView: TextView, palabra: String) {
        textView.text = palabra
        textView.setBackgroundResource(R.drawable.ellipse_background_green)
    }

    private fun mostrarPalabraErronea(textView: TextView, palabra: String) {
        textView.text = palabra
        textView.setBackgroundResource(R.drawable.ellipse_background_red)
    }

    private fun ocultarPalabras() {
        palabra1TextView.text = "1"
        palabra2TextView.text = "2"
        palabra3TextView.text = "3"
        palabra4TextView.text = "4"
        palabra5TextView.text = "5"
        palabra6TextView.text = "6"
        palabra7TextView.text = "7"
        palabra8TextView.text = "8"
        palabra9TextView.text = "9"
        palabra1TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra2TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra3TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra4TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra5TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra6TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra7TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra8TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabra9TextView.setBackgroundResource(R.drawable.ellipse_background)
        palabraActualTextView.text = palabrasOrdenCorrecto[0]
        aciertos = 0
    }

    private fun goResultsActivity(activity: AppCompatActivity, xogoGañado: Boolean = false) {
        countDownTimer?.cancel()
        Intent(activity, OndeEstanResultsActivity::class.java).apply {
            if (xogoGañado) {
                putExtra("SCORE_ONDE_ESTAN", 90 - ondeEstanTimerTv.text.toString().split(":")[0].toInt())
            }
            startActivity(this)
            finish()
        }
    }
}