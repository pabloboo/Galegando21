package com.galegando21.model

sealed class AforcadoGameState {
    class Running(val lettersUsed: String,
                  val underscoreWord: String,
                  val drawable: Int) : AforcadoGameState()
    class Lost(val wordToGuess: String) : AforcadoGameState()
    class Won(val wordToGuess: String) : AforcadoGameState()
}