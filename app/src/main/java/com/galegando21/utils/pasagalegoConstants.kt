package com.galegando21.utils

import com.galegando21.model.QuestionPasagalego

object pasagalegoConstants {

    fun getPasagalegoQuestions(letter: Char) : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        when (letter) {
            'A' -> questions = getPasagalegoQuestionsA()
        }
        return questions
    }

    private fun getPasagalegoQuestionsA() : MutableList<QuestionPasagalego> {
        lateinit var questions : MutableList<QuestionPasagalego>
        questions.add(QuestionPasagalego(1, 'A',"Armazón con forma de X que, nos molinos de vento, sostén as telas nas que fai presión o vento", "ASPA"))

        return questions
    }

}