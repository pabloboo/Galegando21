package com.galegando21.day20XogoPalabras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.setBanner
import kotlin.random.Random

class XogoPalabrasGameActivity : AppCompatActivity() {

    private lateinit var inputWordTextView: TextView
    private lateinit var deleteLetterButton: Button
    private lateinit var deleteWordButton: ImageButton
    private lateinit var checkWordButton: Button
    var letterTextViews: List<TextView> = listOf()

    private val ALFABETO_VOCAL = listOf('A', 'E', 'I', 'O', 'U')
    private val ALFABETO_CONSONANTE = ALFABETO.filter { it !in ALFABETO_VOCAL && it != 'Q'}
    var letras = mutableListOf<Char>()
    var centerLetter: Char = ' '
    var palabras = mutableListOf<String>()
    var palabrasPosibles = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xogo_palabras_game)

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
            }
        }

        deleteWordButton.setOnClickListener {
            inputWordTextView.text = ""
        }

        checkWordButton.setOnClickListener {
            comprobarPalabra()
        }
    }

    private fun iniciarXogo() {
        palabras = DigalegoConstants.getWords(this).toMutableList()
        letras = seleccionarLetras()
        mostrarLetras(letras)
        centerLetter = letras[0]
        palabrasPosibles = palabrasPosibles().toMutableList()
        Log.d("XogoPalabras", palabrasPosibles.toString())
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
                }
            }
        }
    }

    private fun comprobarPalabra() {
        val word = inputWordTextView.text.toString()
        if (word.isNotEmpty() && word.contains(centerLetter) && word.length >= 4 && word.length <= 7 && palabras.contains(word)) {
            if (palabras.contains(word)) {
                palabras.remove(word)
                inputWordTextView.text = ""
                Toast.makeText(this, "Palabra correcta", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Non existe a palabra", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Palabra inválida", Toast.LENGTH_SHORT).show()
        }
    }

    private fun palabrasPosibles(): List<String> {
        val resultados = mutableListOf<String>()
        for (palabra in palabras) {
            if (palabra.all { it in letras } && palabra.contains(centerLetter) && palabra.length >= 4 && palabra.length <= 7) {
                resultados.add(palabra)
            }
        }
        return resultados
    }
}