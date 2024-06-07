package com.galegando21.day20XogoPalabras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlin.random.Random
import kotlin.math.round

class XogoPalabrasGameActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var inputWordTextView: TextView
    private lateinit var deleteLetterButton: Button
    private lateinit var deleteWordButton: ImageButton
    private lateinit var checkWordButton: Button
    var letterTextViews: List<TextView> = listOf()

    private val ALFABETO_VOCAL = listOf('A', 'E', 'I', 'O', 'U')
    private val ALFABETO_CONSONANTE = ALFABETO.filter { it !in ALFABETO_VOCAL && it != 'Q'}
    var letras = mutableListOf<Char>()
    var centerLetter: Char = ' '
    var palabrasPosibles = mutableListOf<String>()
    var palabrasEncontradas = mutableListOf<String>()
    var puntuacion = 0
    var puntuacionMax = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xogo_palabras_game)

        progressBar = findViewById(R.id.progress_bar_xogo_palabras)
        inputWordTextView = findViewById(R.id.input_word)
        deleteLetterButton = findViewById(R.id.btn_delete_xogo_palabras)
        deleteWordButton = findViewById(R.id.btn_delete_word_xogo_palabras)
        checkWordButton = findViewById(R.id.btn_enter_xogo_palabras)

        setBanner(this, R.string.xogo_de_palabras)

        iniciarXogo()

        deleteLetterButton.setOnClickListener {
            val text = inputWordTextView.text.toString()
            if (text.isNotEmpty()) {
                inputWordTextView.text = text.dropLast(1)
                resaltarLetraCentral()
            }
        }

        deleteWordButton.setOnClickListener {
            inputWordTextView.text = ""
        }

        checkWordButton.setOnClickListener {
            comprobarPalabra()
        }

        checkWordButton.setOnLongClickListener {
            rematarXogo()
            true
        }

        setOnBackPressed(this, XogoPalabrasInicioActivity::class.java)
    }

    private fun iniciarXogo() {
        letras = seleccionarLetras()
        mostrarLetras(letras)
        centerLetter = letras[0]
        palabrasPosibles = palabrasPosibles().toMutableList()
        Log.d("XogoPalabras", palabrasPosibles.toString())

        calcularPuntuacionMax()
        progressBar.max = puntuacionMax
        progressBar.progress = puntuacion
    }

    private fun seleccionarLetras(): MutableList<Char> {
        val vocalesDisponibles = ALFABETO_VOCAL.toMutableList()
        val consonantesDisponibles = ALFABETO_CONSONANTE.toMutableList()

        val vocales = List(Random.nextInt(2, 4)) {
            vocalesDisponibles.removeAt(Random.nextInt(vocalesDisponibles.size))
        }
        val consonantes = List(7 - vocales.size) {
            consonantesDisponibles.removeAt(Random.nextInt(consonantesDisponibles.size))
        }
        return (vocales + consonantes).toMutableList()
    }

    private fun mostrarLetras(letras: List<Char>) {
        letterTextViews = listOf(
            findViewById(R.id.center_letter),
            findViewById(R.id.letter1),
            findViewById(R.id.letter2),
            findViewById(R.id.letter3),
            findViewById(R.id.letter4),
            findViewById(R.id.letter5),
            findViewById(R.id.letter6)
        )

        letras.zip(letterTextViews) { letra, textView ->
            textView.text = letra.toString()
        }

        letterTextViews.forEach { textView ->
            textView.setOnClickListener {
                val text = inputWordTextView.text.toString()
                if (text.length < 7) {
                    inputWordTextView.text = text + textView.text
                    resaltarLetraCentral()
                }
            }
        }
    }

    private fun comprobarPalabra() {
        val word = inputWordTextView.text.toString()
        var palabraValida = true
        if (!word.contains(centerLetter)) {
            Toast.makeText(this, "Non contén a letra central", Toast.LENGTH_SHORT).show()
            palabraValida = false
        } else if (word.length < 4 || word.length > 7) {
            Toast.makeText(this, "Non ten a lonxitude adecuada", Toast.LENGTH_SHORT).show()
            palabraValida = false
        } else if (palabrasEncontradas.contains(word)) {
            Toast.makeText(this, "Palabra xa encontrada", Toast.LENGTH_SHORT).show()
            palabraValida = false
        } else if (!palabrasPosibles.contains(word)) {
            Toast.makeText(this, "Palabra non encontrada", Toast.LENGTH_SHORT).show()
            palabraValida = false
        }

        if (palabraValida) {
            palabrasPosibles.remove(word)
            inputWordTextView.text = ""

            // Calcular puntuación
            puntuacion += calcularPuntuacion(word)
            progressBar.progress = puntuacion

            if (puntuacion == puntuacionMax) {
                rematarXogo()
            } else {
                Toast.makeText(this, "Palabra válida, ${calcularPuntuacion(word)} ptos.", Toast.LENGTH_SHORT).show()
                palabrasEncontradas.add(word)
            }
        }
    }

    private fun palabrasPosibles(): List<String> {
        val resultados = mutableSetOf<String>()

        while(resultados.size == 0) {
            val palabras = DigalegoConstants.getWords(this).toMutableList()
            for (palabra in palabras) {
                if (palabra.all { it in letras } && palabra.contains(centerLetter) && palabra.length >= 4 && palabra.length <= 7) {
                    resultados.add(palabra)
                }
            }
        }

        return resultados.toList()
    }

    private fun calcularPuntuacion(palabra: String): Int {
        val longitud = palabra.length
        var puntuacion = when (longitud) {
            4 -> 1
            5 -> 2
            6 -> 3
            7 -> 5
            else -> 0
        }

        // Si la palabra contiene todas y cada una de las letras de la variable letras, se le suma 10 puntos
        var letrasAux = letras.toMutableList()
        for (letra in palabra) {
            letrasAux.remove(letra)
        }
        if (letrasAux.isEmpty()) {
            puntuacion += 10
        }

        return puntuacion
    }

    private fun calcularPuntuacionMax() {
        for (palabra in palabrasPosibles) {
            puntuacionMax += calcularPuntuacion(palabra)
        }
    }

    private fun resaltarLetraCentral() {
        val palabra = inputWordTextView.text.toString()
        val spannable = SpannableString(palabra)

        palabra.indices.forEach { indice ->
            if (palabra[indice] == centerLetter) {
                spannable.setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.yellow, null)),
                    indice,
                    indice + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        inputWordTextView.text = spannable
    }

    private fun rematarXogo() {
        Toast.makeText(this, "Xogo rematado", Toast.LENGTH_SHORT).show()
        Intent(this, XogoPalabrasResultsActivity::class.java).apply {
            val porcentaxeAcerto = ((puntuacion.toFloat().div(puntuacionMax.toFloat())) * 100F)
            val porcentaxeAcertoAproximado = round(porcentaxeAcerto * 100) / 100
            putExtra("PORCENTAXE_ACERTO", porcentaxeAcertoAproximado)
            startActivity(this)
        }
    }
}