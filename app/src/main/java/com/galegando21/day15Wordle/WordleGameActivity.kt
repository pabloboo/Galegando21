package com.galegando21.day15Wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.WordleGameManager
import com.galegando21.utils.DictionaryConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class WordleGameActivity : AppCompatActivity() {
    private lateinit var helpImageButton: ImageButton
    private lateinit var xogarDeNovoButton: Button
    private lateinit var progressLoadingBar: ProgressBar

    private lateinit var texts:  MutableList<MutableList<TextView>>
    private val rowCount = 6
    private val colCount = 5
    private var countGames = 0
    private var countWins = 0
    private var countCurrentTries = 0
    private lateinit var gameCore: WordleGameManager
    private var words = mutableListOf<String>()

    private val lettersPaintedStates = mutableMapOf<Char, Int>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordle_game)

        helpImageButton = findViewById(R.id.helpButton)
        xogarDeNovoButton = findViewById(R.id.xogar_de_novo_wordle_btn)
        progressLoadingBar = findViewById(R.id.loadingProgressBar)

        gameCore = WordleGameManager(this, rowCount)
        initTexts()

        newRound()

        helpImageButton.setOnClickListener {
            showHelpDialog()
        }

        xogarDeNovoButton.setOnClickListener {
            gameCore.startOver()
            newRound()
            xogarDeNovoButton.visibility = View.GONE
        }

        setOnBackPressed(this, WordleInicioActivity::class.java)
    }

    private fun unSetEventListeners() {
        val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "nh", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z")
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener(null)
        }

        val btnBackspace = findViewById<ImageButton>(R.id.buttonBackspace)
        btnBackspace.setOnClickListener(null)

        val btnEnter = findViewById<ImageButton>(R.id.buttonEnter)
        btnEnter.setOnClickListener(null)
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
                    if (gameCore.getCurCol() == 5) {
                        return@setOnClickListener
                    }
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
            val row = gameCore.getCurRow()
            // Comprobar si la palabra existe en words
            val inputWord = texts[row].joinToString("") { it.text.toString() }
            if (!words.contains(inputWord)) {
                Toast.makeText(this, "Non existe a palabra", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (gameCore.enter()) {
                countCurrentTries++
                val result: List<Pair<Char, Int>> = gameCore.validateWord(row)
                for (col in 0 until colCount) {
                    val id = when (result[col].second) {
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
                    updateLetterColor(result[col].first, result[col].second)
                }
                if (gameCore.getResult()) {
                    countWins++
                    countCurrentTries = 0
                    Toast.makeText(this, "Acertaches!", Toast.LENGTH_SHORT).show()
                    unSetEventListeners()
                    xogarDeNovoButton.visibility = View.VISIBLE
                    changeWordleStatistics()
                } else if (countCurrentTries == 6) {
                    progressLoadingBar.visibility = View.VISIBLE
                    countCurrentTries = 0
                    showGameLostDialog()
                    unSetEventListeners()
                    xogarDeNovoButton.visibility = View.VISIBLE
                    progressLoadingBar.visibility = View.GONE
                }
            }
        }
    }

    private fun showGameLostDialog() {
        AlertDialog.Builder(this)
            .setTitle("Perdeches, a palabra era ${gameCore.getFinalWord()}")
            .setMessage(DictionaryConstants.getWordDefinition(this, gameCore.getFinalWord())?.definicion ?: "")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
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
        for (row in 0 until rowCount) {
            for (col in 0 until colCount) {
                texts[row][col].background =
                    ContextCompat.getDrawable(this@WordleGameActivity, R.drawable.letter_border)
                texts[row][col].text = " "
            }
        }
        resetAllLetterColors()
        val textGames = findViewById<TextView>(R.id.games)
        val textWins = findViewById<TextView>(R.id.wins)

        textGames.text = "Xogos: $countGames"
        textWins.text = "Victorias: $countWins"
        countGames++

        coroutineScope.launch {
            progressLoadingBar.visibility = View.VISIBLE
            unSetEventListeners()
            withContext(Dispatchers.Default) {
                words = gameCore.setWord().toMutableList()
                Log.d("word", gameCore.getFinalWord())
            }
            setEventListeners()
            progressLoadingBar.visibility = View.GONE
        }
    }

    fun updateLetterColor(letter: Char, state: Int) {
        var letterId = resources.getIdentifier(letter.lowercase(), "id", packageName)
        if (letter.lowercase() == "ñ") {
            letterId = resources.getIdentifier("nh", "id", packageName)
        }
        val letterTextView = findViewById<TextView>(letterId) ?: return

        val currentState = lettersPaintedStates[letter]
        if (currentState == gameCore.IN_PLACE) {
            // La letra ya ha sido marcada de verde
            return
        }

        val drawableId = when (state) {
            gameCore.IN_WORD -> R.drawable.letter_in_word
            gameCore.IN_PLACE -> R.drawable.letter_in_place
            gameCore.NOT_IN -> R.drawable.letter_not_in
            else -> R.drawable.letter_not_in
        }

        if (currentState == gameCore.IN_WORD && drawableId == gameCore.NOT_IN) {
            // Si la letra ya ha sido marcada de amarillo y no se ha acertado
            return
        }

        letterTextView.background = ContextCompat.getDrawable(this, drawableId)
        lettersPaintedStates[letter] = state
    }

    fun resetAllLetterColors() {
        val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "nh", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z")
        for (letter in letters) {
            val letterId = resources.getIdentifier(letter, "id", packageName)
            val letterTextView = findViewById<TextView>(letterId)
            letterTextView.background = ContextCompat.getDrawable(this, R.color.primaryBlue)
        }
        lettersPaintedStates.clear()
    }

    private fun showHelpDialog() {
        val imageView = ImageView(this)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wordle_explicacion))

        AlertDialog.Builder(this)
            .setTitle("Como xogar")
            .setView(imageView)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun changeWordleStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxStreak = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.WORDLE_MAX_STREAK)) {
            maxStreak = sharedPreferences.getInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, 0)
        }

        if (countWins > maxStreak) {
            editor.putInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, countWins)
            editor.apply()
        }
        Log.d("maxStreak", sharedPreferences.getInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, 0).toString())

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, 10)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}