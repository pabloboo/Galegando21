package com.galegando21.day08Wordle

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.WordleGameManager

class WordleGameActivity : AppCompatActivity() {
    private lateinit var texts:  MutableList<MutableList<TextView>>
    private val rowCount = 7
    private val colCount = 5
    private var countGames = 0
    private var countWins = 0
    private lateinit var gameCore: WordleGameManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordle_game)

        gameCore = WordleGameManager(rowCount)
        initTexts()
        setEventListeners()

        newRound()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@WordleGameActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun setEventListeners() {
        val checkButtonLetter = findViewById<ImageButton>(R.id.check_btn_letter_wordle)
        val editTextLetter = findViewById<EditText>(R.id.input_letter_editText_wordle)
        editTextLetter.filters = arrayOf(InputFilter.AllCaps())
        checkButtonLetter.setOnClickListener {
            if (gameCore.isPouse()) {
                gameCore.startOver()
                newRound()
            }
            val row = gameCore.getCurRow()
            var col = gameCore.getCurCol()
            for (i in 0..4) {
                if (gameCore.setNextChar(editTextLetter.text.toString()[i])) {
                    texts[row][col].text = editTextLetter.text.toString()[i].toString()
                    col++
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