package com.galegando21.utils

import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaEscudo

object AdivinhaEscudoConstants {
    const val TOTAL_QUESTIONS = "advinha_escudo_total_questions"
    const val SCORE = "adivinha_escudo_correct_answers"

    fun getQuestions() : MutableList<QuestionAdivinhaEscudo> {
        val questions = mutableListOf<QuestionAdivinhaEscudo>()

        questions.add(
            QuestionAdivinhaEscudo(
                R.drawable.escudo_o_pino,
                "O Pino",
                "Oroso",
                "Carballo",
                "Ordes",
                1
            )
        )

        return questions
    }
}