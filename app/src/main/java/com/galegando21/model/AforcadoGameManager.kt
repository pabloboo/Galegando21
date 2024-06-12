package com.galegando21.model

import android.content.Context
import com.galegando21.R
import com.galegando21.utils.DigalegoConstants
import com.galegando21.utils.PalabrasBasicasConstants
import kotlin.random.Random

class AforcadoGameManager {

    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = 5
    private var currentTries = 0
    private var drawable: Int = R.drawable.aforcado1

    fun startNewGame(context: Context, modo: String): AforcadoGameState {
        lettersUsed = ""
        currentTries = 0
        drawable = R.drawable.aforcado6

        when (modo) {
            "facil" -> {
                val randomIndex = Random.nextInt(0, PalabrasBasicasConstants.getPalabrasBasicasWords(context).size)
                wordToGuess = PalabrasBasicasConstants.getPalabrasBasicasWords(context)[randomIndex]
            }
            "dificil" -> {
                val randomIndex = Random.nextInt(0, DigalegoConstants.getWords(context).size)
                wordToGuess = DigalegoConstants.getWords(context)[randomIndex]
            }
        }

        generateUnderscores(wordToGuess)
        return getGameState()
    }

    fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun playLetter(letter: Char): AforcadoGameState {
        if (lettersUsed.contains(letter)) {
            return AforcadoGameState.Running(lettersUsed, underscoreWord, drawable)
        }

        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getGameState()
    }

    private fun getHangmanDrawable(): Int {
        return when (currentTries) {
            0 -> R.drawable.aforcado1
            1 -> R.drawable.aforcado2
            2 -> R.drawable.aforcado3
            3 -> R.drawable.aforcado4
            4 -> R.drawable.aforcado5
            5 -> R.drawable.aforcado6
            else -> R.drawable.aforcado6
        }
    }

    private fun getGameState(): AforcadoGameState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return AforcadoGameState.Won(wordToGuess)
        }

        if (currentTries == maxTries) {
            return AforcadoGameState.Lost(wordToGuess)
        }

        drawable = getHangmanDrawable()
        return AforcadoGameState.Running(lettersUsed, underscoreWord, drawable)
    }

}