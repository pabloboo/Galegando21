package com.galegando21.day19Aforcado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.AforcadoGameState
import com.galegando21.model.AforcadoGameManager
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

class AforcadoGameActivity : AppCompatActivity() {
    private val gameManager = AforcadoGameManager()

    private lateinit var rachaTextView: TextView
    private lateinit var wordTextView: TextView
    private lateinit var lettersUsedTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var newGameButton: Button
    private lateinit var progressLoadingBar: ProgressBar

    val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "nh", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z")
    private var racha = 0

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var modo = "facil"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aforcado_game)

        rachaTextView = findViewById(R.id.rachaAforcadoTextView)
        imageView = findViewById(R.id.aforcadoImageView)
        wordTextView = findViewById(R.id.aforcadoWordTextView)
        lettersUsedTextView = findViewById(R.id.aforcadoLetrasUsadasTextView)
        gameLostTextView = findViewById(R.id.aforcadoLostTextView)
        gameWonTextView = findViewById(R.id.aforcadoWonTextView)
        newGameButton = findViewById(R.id.novoXogoAforcadoButton)
        progressLoadingBar = findViewById(R.id.loadingProgressBar)

        modo = intent.getStringExtra("modo") ?: "facil"

        newGameButton.setOnClickListener {
            startNewGame()
        }

        startNewGame()

        setOnBackPressed(this, AforcadoInicioActivity::class.java)
    }

    private fun unSetLetterEventListeners() {
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener {}
        }
    }

    private fun setLetterEventListeners() {
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener {
                val gameState = gameManager.playLetter((letterTextView).text[0])
                updateUI(gameState)
                letterTextView.visibility = View.GONE
            }
        }
    }

    private fun updateUI(gameState: AforcadoGameState) {
        when (gameState) {
            is AforcadoGameState.Lost -> showGameLost(gameState.wordToGuess)
            is AforcadoGameState.Running -> showGameRunning(gameState.lettersUsed, gameState.underscoreWord, gameState.drawable)
            is AforcadoGameState.Won ->
                showGameWon(gameState.wordToGuess)
        }
    }

    private fun showGameRunning(lettersUsed: String, underscoreWord: String, drawable: Int) {
        wordTextView.text = underscoreWord
        lettersUsedTextView.text = getString(R.string.letters_used, lettersUsed)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, drawable))
    }

    private fun showGameLost(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameLostTextView.visibility = View.VISIBLE
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.aforcado6))
        unSetLetterEventListeners()
        showGameLostDialog(wordToGuess)
        racha = 0
        rachaTextView.text = "Racha actual: $racha"
    }

    private fun showGameLostDialog(wordToGuess: String) {
        AlertDialog.Builder(this)
            .setTitle("Perdiches, a palabra era $wordToGuess")
            .setMessage(DictionaryConstants.getWordDefinition(this, wordToGuess)?.definicion ?: "")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showGameWon(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        unSetLetterEventListeners()
        racha++
        rachaTextView.text = "Racha actual: $racha"
        changeAforcadoStatistics()
    }

    private fun startNewGame() {
        coroutineScope.launch {
            // Deshabilitar los elementos de la interfaz de usuario
            progressLoadingBar.visibility = View.VISIBLE
            newGameButton.isEnabled = false
            unSetLetterEventListeners()

            gameLostTextView.visibility = View.GONE
            gameWonTextView.visibility = View.GONE
            for (letter in letters) {
                val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
                letterTextView.visibility = View.VISIBLE
            }

            val gameState = withContext(Dispatchers.Default) {
                gameManager.startNewGame(this@AforcadoGameActivity, modo)
            }
            updateUI(gameState)

            // Habilitar los elementos de la interfaz de usuario
            newGameButton.isEnabled = true
            setLetterEventListeners()
            progressLoadingBar.visibility = View.GONE
        }
    }

    private fun changeAforcadoStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val maxStreakKey =
            when (modo) {
                "facil" -> SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY
                "dificil" -> SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT
                else -> SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY
            }

        var maxStreak = 0
        if (sharedPreferences.contains(maxStreakKey)) {
            maxStreak = sharedPreferences.getInt(maxStreakKey, 0)
        }

        if (racha > maxStreak) {
            editor.putInt(maxStreakKey, racha)
            editor.apply()
        }
        Log.d("maxStreak", sharedPreferences.getInt(maxStreakKey, 0).toString())

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, 10)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}