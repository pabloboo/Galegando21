package com.galegando21.day08Wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.WordleGameManager
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import java.util.Locale

class WordleGameActivity : AppCompatActivity() {
    private lateinit var texts:  MutableList<MutableList<TextView>>
    private val rowCount = 6
    private val colCount = 5
    private var countGames = 0
    private var countWins = 0
    private lateinit var gameCore: WordleGameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordle_game)

        setBanner(this, R.string.wordle)

        gameCore = WordleGameManager(rowCount)
        initTexts()
        setEventListeners()

        newRound()

        setOnBackPressed(this, WordleInicioActivity::class.java)
    }

    private fun setEventListeners() {

        //letters buttons
        val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "nh", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z")
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener {
                if (letter == "nh") {
                    texts[gameCore.getCurRow()][gameCore.getCurCol()].text = "Ñ"
                    gameCore.setNextChar('Ñ')
                } else {
                    texts[gameCore.getCurRow()][gameCore.getCurCol()].text = letter.uppercase(Locale.ROOT)
                    gameCore.setNextChar(letter.uppercase(Locale.ROOT)[0])
                }
            }
        }

        //backspace button
        val btnBackspace = findViewById<ImageButton>(R.id.buttonBackspace)
        btnBackspace.setOnClickListener {
                if (gameCore.getCurCol() > 0) {
                    gameCore.deleteChar()
                    texts[gameCore.getCurRow()][gameCore.getCurCol()].text = " "
                }
            }

        //check solution
        val btnEnter = findViewById<ImageButton>(R.id.buttonEnter)
        btnEnter.setOnClickListener {
            if (gameCore.isPouse()) {
                gameCore.startOver()
                newRound()
            }
            val row = gameCore.getCurRow()
            if (gameCore.enter()) {
                for (col in 0 until colCount) {
                    val id = when (gameCore.validateChar(row, col)) {
                        gameCore.IN_WORD -> {
                            R.drawable.letter_in_word
                        }

                        gameCore.IN_PLACE -> {
                            R.drawable.letter_in_place
                        }

                        else -> {
                            R.drawable.letter_not_in
                        }
                    }

                    texts[row][col].background = ContextCompat.getDrawable(this, id)
                }
                if (gameCore.getResult()) {
                    countWins++
                }
            }
        }
    }

    private fun initTexts() {
        texts = MutableList(rowCount) { mutableListOf() }
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                val resID =
                    resources.getIdentifier("text${col + 1}col${row + 1}row", "id", packageName)
                texts[row].add(findViewById(resID))
            }
        }
    }

    private fun newRound() {
        gameCore.setWord()
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                texts[row][col].background = ContextCompat.getDrawable(this,  R.drawable.letter_border)
                texts[row][col].text = " "
            }
        }
        val textGames = findViewById<TextView>(R.id.games)
        val textWins = findViewById<TextView>(R.id.wins)

        textGames.text = "Xogos: $countGames"
        textWins.text = "Victorias: $countWins"
        countGames++

        Log.e("Word", "=============---- ${gameCore.getFinalWord()}")
    }
}