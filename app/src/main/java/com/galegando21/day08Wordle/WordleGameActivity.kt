package com.galegando21.day08Wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.WordleGameManager
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

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
        val checkButtonLetter = findViewById<ImageButton>(R.id.check_btn_letter_wordle)
        val editTextLetter = findViewById<EditText>(R.id.input_letter_editText_wordle)
        editTextLetter.filters = arrayOf(InputFilter.AllCaps())
        checkButtonLetter.setOnClickListener {
            val inputText = editTextLetter.text.toString()
            if (inputText.length != colCount || inputText.contains(" ")) {
                Toast.makeText(this@WordleGameActivity, "Introduce unha palabra con $colCount caracteres", Toast.LENGTH_SHORT).show()
            } else {
                if (gameCore.isPouse()) {
                    gameCore.startOver()
                    newRound()
                }
                val row = gameCore.getCurRow()
                var col = gameCore.getCurCol()
                for (i in 0..4) {
                    if (gameCore.setNextChar(inputText[i])) {
                        texts[row][col].text = inputText[i].toString()
                        col++
                    }
                }
            }
        }

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
                editTextLetter.text.clear()
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