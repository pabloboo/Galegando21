package com.galegando21.utils

object AforcadoGameConstants {
    fun getWords() : List<String> {
        val words = mutableListOf("GALIÑA", "SANTIAGO", "O/PINO", "ERUDITO", "A/CORUÑA")
        for (letter in PasagalegoConstants.ALFABETO) {
            var questions = PasagalegoConstants.getPasagalegoQuestions(letter)
            for (question in questions) {
                words.add(question.answer.uppercase())
            }
        }
        return words
    }
}