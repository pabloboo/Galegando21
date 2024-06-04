package com.galegando21.day17SopaLetras

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import com.galegando21.utils.SopaLetrasConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlin.random.Random

class SopaLetrasGameActivity : AppCompatActivity() {
    private lateinit var hintTextView: TextView
    private lateinit var checkAnswerButton: Button
    private lateinit var xogarDeNovoButton: Button

    private val boardSize = 5
    private var board = Array(boardSize) { arrayOfNulls<Char>(boardSize) }
    private var words: MutableList<String> = mutableListOf()
    private val selectedLetters = mutableListOf<TextView>()
    private val correctTextViews = mutableListOf<TextView>()
    private val allTextViews = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopa_letras_game)

        hintTextView = findViewById(R.id.sopaLetrasHintTextView)
        checkAnswerButton = findViewById(R.id.checkAnswerSopaLetrasBtn)
        xogarDeNovoButton = findViewById(R.id.xogar_de_novo_button_sopa_letras)

        setBanner(this, R.string.sopa_de_letras)

        generateWords()
        generateBoard()

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

        xogarDeNovoButton.setOnClickListener {
            novoXogo()
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun generateWords() {
        words.clear()
        val questionSopaLetras = SopaLetrasConstants.getSopasLetrasConPista().random()
        words = mutableListOf(questionSopaLetras.word1, questionSopaLetras.word2, questionSopaLetras.word3)
        hintTextView.text = questionSopaLetras.hint
    }

    private fun generateBoard() {
        // Insertar palabras en el tablero
        for (word in words) {
            var direction = Random.nextInt(2)
            var startRow = if (direction == 0) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)
            var startCol = if (direction == 1) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)

            // Comprobar si las posiciones ya están ocupadas
            var isOccupied = false
            for (i in word.indices) {
                if (direction == 0) {
                    if (board[startRow][startCol + i] != null) {
                        isOccupied = true
                        break
                    }
                } else {
                    if (board[startRow + i][startCol] != null) {
                        isOccupied = true
                        break
                    }
                }
            }

            // Si las posiciones están ocupadas generar nuevas posiciones
            while (isOccupied) {
                direction = Random.nextInt(2)
                startRow = if (direction == 0) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)
                startCol = if (direction == 1) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)

                isOccupied = false
                for (i in word.indices) {
                    if (direction == 0) {
                        if (board[startRow][startCol + i] != null) {
                            isOccupied = true
                            break
                        }
                    } else {
                        if (board[startRow + i][startCol] != null) {
                            isOccupied = true
                            break
                        }
                    }
                }
            }

            // Insertar la palabra en el tablero
            for (i in word.indices) {
                if (direction == 0) {
                    board[startRow][startCol + i] = word[i]
                } else {
                    board[startRow + i][startCol] = word[i]
                }
            }
        }

        // Rellenar el resto del tablero con letras aleatorias
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                if (board[i][j] == null) {
                    board[i][j] = ALFABETO.random()
                }
            }
        }

        // Actualizar los TextViews para mostrar el tablero
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                val textView = findViewById<TextView>(resources.getIdentifier("letter${i * boardSize + j + 1}", "id", packageName))
                allTextViews.add(textView)
                textView.text = board[i][j].toString()
                textView.setOnClickListener {
                    if (selectedLetters.contains(it)) {
                        it.setBackgroundColor(resources.getColor(R.color.primaryBlue, null))
                        selectedLetters.remove(it as TextView)
                    } else {
                        it.setBackgroundColor(resources.getColor(R.color.secondaryBlue, null))
                        selectedLetters.add(it as TextView)
                    }
                }
            }
        }
    }

    private fun checkAnswer() {
        val selectedWord = selectedLetters.joinToString("") { it.text.toString() }
        Log.d("SopaLetrasGameActivity", "Selected word: $selectedWord")

        if (isContiguous() && words.contains(selectedWord)) {
            words.remove(selectedWord)
            // Comprobar si se han encontrado todas las palabras
            if (words.isEmpty()) {
                Toast.makeText(this, "Felicidades! Encontraches todas as palabras", Toast.LENGTH_SHORT).show()
                xogarDeNovoButton.visibility = Button.VISIBLE
                checkAnswerButton.visibility = Button.GONE
            }

            for (textView in selectedLetters) {
                textView.setBackgroundColor(Color.GREEN)
                correctTextViews.add(textView)
            }
        } else {
            for (textView in selectedLetters) {
                // Si el textView está en verde no cambiarlo
                if (!correctTextViews.contains(textView)) {
                    textView.setBackgroundColor(resources.getColor(R.color.primaryBlue, null))
                } else { // Si el textView está en verde dejarlo en verde
                    textView.setBackgroundColor(Color.GREEN)
                }
            }
        }
        selectedLetters.clear()
    }

    // Comprobar si las letras seleccionadas están contiguas
    private fun isContiguous(): Boolean {
        if (selectedLetters.size <= 1) {
            return true
        }

        // Calcular la posición de la primera letra seleccionada en el tablero
        val firstTextView = selectedLetters[0]
        val firstRow = allTextViews.indexOf(firstTextView) / boardSize
        val firstCol = allTextViews.indexOf(firstTextView) % boardSize

        // Realizar un recorrido en anchura para comprobar si las letras seleccionadas están contiguas
        // Usar una cola para almacenar las celdas a visitar y visited para marcar las celdas visitadas
        val visited = Array(boardSize) { BooleanArray(boardSize) }
        val queue = mutableListOf<Pair<Int, Int>>()
        queue.add(Pair(firstRow, firstCol))
        visited[firstRow][firstCol] = true

        // Mientras la cola no estea vacía comprobar si las letras seleccionadas están contiguas
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeAt(0)

            for (i in -1..1) {
                for (j in -1..1) {
                    if (i == 0 && j == 0) {
                        continue
                    }

                    val newRow = row + i
                    val newCol = col + j

                    if (newRow in 0 until boardSize && newCol in 0 until boardSize && !visited[newRow][newCol]) {
                        val textView = allTextViews[newRow * boardSize + newCol]
                        if (selectedLetters.contains(textView)) {
                            visited[newRow][newCol] = true
                            queue.add(Pair(newRow, newCol))
                        }
                    }
                }
            }
        }

        // Si alguna letra seleccionada no está visitada devolver false
        for (textView in selectedLetters) {
            val row = allTextViews.indexOf(textView) / boardSize
            val col = allTextViews.indexOf(textView) % boardSize
            if (!visited[row][col]) {
                return false
            }
        }

        return true
    }

    private fun restablecerTextViews() {
        for (textView in allTextViews) {
            textView.setBackgroundColor(resources.getColor(R.color.primaryBlue, null))
            textView.text = null
        }
    }

    private fun novoXogo() {
        board = Array(boardSize) { arrayOfNulls<Char>(boardSize) }
        restablecerTextViews()
        selectedLetters.clear()
        correctTextViews.clear()
        xogarDeNovoButton.visibility = Button.GONE
        checkAnswerButton.visibility = Button.VISIBLE
        generateWords()
        generateBoard()
    }
}