package com.galegando21.day05Aforcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.galegando21.BannerFragment
import com.galegando21.R
import com.galegando21.model.AforcadoGameState
import com.galegando21.model.AforcadoGameManager

class AforcadoGameActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment

    private val gameManager = AforcadoGameManager()

    private lateinit var wordTextView: TextView
    private lateinit var lettersUsedTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var newGameButton: Button
    private lateinit var lettersLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aforcado_game)

        imageView = findViewById(R.id.aforcadoImageView)
        wordTextView = findViewById(R.id.aforcadoWordTextView)
        lettersUsedTextView = findViewById(R.id.aforcadoLetrasUsadasTextView)
        gameLostTextView = findViewById(R.id.aforcadoLostTextView)
        gameWonTextView = findViewById(R.id.aforcadoWonTextView)
        newGameButton = findViewById(R.id.novoXogoAforcadoButton)
        lettersLayout = findViewById(R.id.aforcadoLettersLayout)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.aforcado))
        }.commit()

        newGameButton.setOnClickListener {
            startNewGame()
        }

        val gameState = gameManager.startNewGame()
        updateUI(gameState)

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

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AforcadoGameActivity, AforcadoInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun updateUI(gameState: AforcadoGameState) {
        when (gameState) {
            is AforcadoGameState.Lost -> showGameLost(gameState.wordToGuess)
            is AforcadoGameState.Running -> showGameRunning(gameState.lettersUsed, gameState.underscoreWord, gameState.drawable)
            is AforcadoGameState.Won -> showGameWon(gameState.wordToGuess)
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
    }

    private fun showGameWon(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
    }

    private fun startNewGame() {
        gameLostTextView.visibility = View.GONE
        gameWonTextView.visibility = View.GONE
        val gameState = gameManager.startNewGame()
        lettersLayout.visibility = View.VISIBLE
        lettersLayout.children.forEach {
            letterView -> letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }
}