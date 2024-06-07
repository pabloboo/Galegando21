package com.galegando21.day05Aforcado

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.galegando21.R
import com.galegando21.model.AforcadoGameState
import com.galegando21.model.AforcadoGameManager
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
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
    private lateinit var lettersLayout: ConstraintLayout
    private lateinit var progressLoadingBar: ProgressBar

    private var racha = 0

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

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
        lettersLayout = findViewById(R.id.aforcadoLettersLayout)
        progressLoadingBar = findViewById(R.id.loadingProgressBar)

        setBanner(this, R.string.aforcado)

        newGameButton.setOnClickListener {
            startNewGame()
        }

        startNewGame()

        lettersLayout.children.forEach {
            letterView ->
                if (letterView is TextView) {
                    letterView.setOnClickListener {
                        val gameState = gameManager.playLetter((letterView).text[0])
                        updateUI(gameState)
                        letterView.visibility = View.GONE
                    }
                }
        }

        setOnBackPressed(this, AforcadoInicioActivity::class.java)
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
        lettersLayout.visibility = View.GONE
        racha = 0
        rachaTextView.text = "Racha actual: $racha"
    }

    private fun showGameWon(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
        racha++
        rachaTextView.text = "Racha actual: $racha"
        changeAforcadoStatistics()
    }

    private fun startNewGame() {
        coroutineScope.launch {
            // Deshabilitar los elementos de la interfaz de usuario
            progressLoadingBar.visibility = View.VISIBLE
            newGameButton.isEnabled = false
            lettersLayout.children.forEach { it.isEnabled = false }

            gameLostTextView.visibility = View.GONE
            gameWonTextView.visibility = View.GONE
            lettersLayout.visibility = View.VISIBLE
            lettersLayout.children.forEach {
                    letterView -> letterView.visibility = View.VISIBLE
            }
            val gameState = withContext(Dispatchers.Default) {
                gameManager.startNewGame(this@AforcadoGameActivity)
            }
            updateUI(gameState)

            // Habilitar los elementos de la interfaz de usuario
            newGameButton.isEnabled = true
            lettersLayout.children.forEach { it.isEnabled = true }
            progressLoadingBar.visibility = View.GONE
        }
    }

    private fun changeAforcadoStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxStreak = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.AFORCADO_MAX_STREAK)) {
            maxStreak = sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK, 0)
        }

        if (racha > maxStreak) {
            editor.putInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK, racha)
            editor.apply()
        }
        Log.d("maxStreak", sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK, 0).toString())

        updateCurrentStreak(this)
    }
}