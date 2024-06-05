package com.galegando21.day17SopaLetras

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
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
    private lateinit var rachaActualTextView: TextView
    private lateinit var hintTextView: TextView
    private lateinit var sopaLetrasTimerTv: TextView
    private lateinit var gridSopaLetras: GridLayout
    private lateinit var checkAnswerButton: Button
    private lateinit var xogarDeNovoButton: Button

    private var boardSize = 5
    private var board = Array(boardSize) { arrayOfNulls<Char>(boardSize) }
    private var words: MutableList<String> = mutableListOf()
    private val selectedLetters = mutableListOf<TextView>()
    private val correctTextViews = mutableListOf<TextView>()
    private val allTextViews = mutableListOf<TextView>()
    private var racha = 0
    private var dificultade = SopaLetrasConstants.NIVEL_FACIL
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopa_letras_game)

        rachaActualTextView = findViewById(R.id.rachaActualTextView)
        hintTextView = findViewById(R.id.sopaLetrasHintTextView)
        sopaLetrasTimerTv = findViewById(R.id.sopa_letras_timer_tv)
        gridSopaLetras = findViewById(R.id.gridSopaLetras)
        checkAnswerButton = findViewById(R.id.checkAnswerSopaLetrasBtn)
        xogarDeNovoButton = findViewById(R.id.xogar_de_novo_button_sopa_letras)

        setBanner(this, R.string.sopa_de_letras)

        dificultade = intent.getStringExtra("dificultade")  ?: SopaLetrasConstants.NIVEL_FACIL

        generateWords()
        generateBoard()

        checkAnswerButton.setOnClickListener {
            checkAnswer()
        }

        xogarDeNovoButton.setOnClickListener {
            novoXogo()
        }

        setOnBackPressed(this, SopaLetrasInicioActivity::class.java)
    }

    private fun generateWords() {
        // Cancelar el temporizador si está corriendo
        countDownTimer?.cancel()

        words.clear()

        if (dificultade == SopaLetrasConstants.NIVEL_FACIL) {
            val questionSopaLetras = SopaLetrasConstants.getSopasLetras(4)
            words = mutableListOf(questionSopaLetras.word1, questionSopaLetras.word2, questionSopaLetras.word3)
            hintTextView.text = words.joinToString(", ")
        }
        if (dificultade == SopaLetrasConstants.NIVEL_MEDIO) {
            val questionSopaLetras = SopaLetrasConstants.getSopasLetrasConPista().random()
            words = mutableListOf(questionSopaLetras.word1, questionSopaLetras.word2, questionSopaLetras.word3)
            hintTextView.text = questionSopaLetras.hint
        } else if (dificultade == SopaLetrasConstants.NIVEL_DIFICIL) {
            val questionSopaLetras = SopaLetrasConstants.getSopasLetras(5)
            words = mutableListOf(questionSopaLetras.word1, questionSopaLetras.word2, questionSopaLetras.word3)
            // Ampliar tablero a 6x6
            boardSize = 6
            board = Array(boardSize) { arrayOfNulls<Char>(boardSize) }
            gridSopaLetras.columnCount = boardSize
            gridSopaLetras.columnCount = boardSize
            showTextViewsBoard6()

            hintTextView.text = words.joinToString(", ")
        }

        // Iniciar temporizador si la dificultad es media o difícil
        if (dificultade == SopaLetrasConstants.NIVEL_MEDIO || dificultade == SopaLetrasConstants.NIVEL_DIFICIL) {
            // Inicializar nuevo temporizador
            countDownTimer = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    sopaLetrasTimerTv.text = "$seconds:00"
                }

                override fun onFinish() {
                    Log.d("SopaLetrasGameActivity", "$racha sopas de letras completadas")
                    Intent(this@SopaLetrasGameActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }.start()
        } else {
            sopaLetrasTimerTv.visibility = TextView.GONE
        }
    }

    private fun generateBoard() {
        // Insertar palabras en el tablero
        for (word in words) {
            var direction = 0
            var startRow = 0
            var startCol = 0
            var isOccupied = true

            // Si las posiciones están ocupadas generar nuevas posiciones
            while (isOccupied) {
                if (dificultade == SopaLetrasConstants.NIVEL_FACIL) {
                    direction = Random.nextInt(2)
                } else if (dificultade == SopaLetrasConstants.NIVEL_MEDIO) {
                    direction = Random.nextInt(6)
                } else if (dificultade == SopaLetrasConstants.NIVEL_DIFICIL) {
                    direction = Random.nextInt(6)
                }
                startRow = if (direction == 0) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)
                startCol = if (direction == 1) Random.nextInt(boardSize) else Random.nextInt(boardSize - word.length)

                isOccupied = false
                for (i in word.indices) {
                    if (direction == 0) { // Horizontal
                        if ((board[startRow][startCol + i] != null) && (board[startRow][startCol + i] != word[i])) {
                            isOccupied = true
                            break
                        }
                    } else if (direction == 1) { // Vertical
                        if ((board[startRow + i][startCol] != null) && (board[startRow + i][startCol] != word[i])) {
                            isOccupied = true
                            break
                        }
                    } else if (direction == 2) { // Horizontal inversa
                        if ((board[startRow][(startCol - i).mod(boardSize)] != null) && (board[startRow][(startCol - i).mod(boardSize)] != word[i])) {
                            isOccupied = true
                            break
                        }
                    } else if (direction == 3) { // Vertical inversa
                        if ((board[(startRow - i).mod(boardSize)][startCol] != null) && (board[(startRow - i).mod(boardSize)][startCol] != word[i])){
                            isOccupied = true
                            break
                        }
                    } else if (direction == 4) { // Diagonal
                        if ((board[startRow + i][startCol + i] != null) && (board[startRow + i][startCol + i] != word[i])) {
                            isOccupied = true
                            break
                        }
                    } else if (direction == 5) { // Diagonal inversa
                        if ((board[(startRow - i).mod(boardSize)][(startCol - i).mod(boardSize)] != null) && (board[(startRow - i).mod(boardSize)][(startCol - i).mod(boardSize)] != word[i])) {
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
                } else if (direction == 1) { // Vertical
                    board[startRow + i][startCol] = word[i]
                } else if (direction == 2) { // Horizontal inversa
                    board[startRow][(startCol - i).mod(boardSize)] = word[i]
                } else if (direction == 3) { // Vertical inversa
                    board[(startRow - i).mod(boardSize)][startCol] = word[i]
                } else if (direction == 4) { // Diagonal
                    board[startRow + i][startCol + i] = word[i]
                } else if (direction == 5) { // Diagonal inversa
                    board[(startRow - i).mod(boardSize)][(startCol - i).mod(boardSize)] = word[i]
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
                racha++
                rachaActualTextView.text = "Racha actual: $racha"
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

        // Usar una cola para almacenar las celdas a visitar y visited para marcar las celdas visitadas
        val visited = Array(boardSize) { BooleanArray(boardSize) }
        val queue = mutableListOf<Pair<Int, Int>>()
        queue.add(Pair(firstRow, firstCol))
        visited[firstRow][firstCol] = true

        // dirección (i,j) donde i es el cambio en la fila y j es el cambio en la columna
        // posición relativa: (i,j) = (0,1) derecha, (0,-1) izquierda, (1,0) abajo, (-1,0) arriba
        var direction: Pair<Int, Int>? = null

        // Mientras la cola no estea vacía comprobar si las letras seleccionadas están contiguas
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeAt(0)

            for (i in -1..1) {
                for (j in -1..1) {
                    if (i == 0 && j == 0) { // No visitar la celda actual
                        continue
                    }

                    val newRow = (row + i + boardSize) % boardSize
                    val newCol = (col + j + boardSize) % boardSize

                    if (!visited[newRow][newCol]) {
                        // Si la celda vecina es una letra seleccionada visitarla
                        val textView = allTextViews[newRow * boardSize + newCol]
                        if (selectedLetters.contains(textView)) {
                            if (direction == null) {
                                direction = Pair(i, j)
                            } else if (direction != Pair(i, j)) {
                                return false
                            }
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

    private fun showTextViewsBoard6() {
        for (i in 26 until 37) {
            val textView = findViewById<TextView>(resources.getIdentifier("letter${i}", "id", packageName))
            textView.visibility = TextView.VISIBLE
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