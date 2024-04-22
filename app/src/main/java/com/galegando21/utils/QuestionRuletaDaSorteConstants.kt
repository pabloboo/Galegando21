package com.galegando21.utils

import com.galegando21.model.QuestionRuletaDaSorte

object QuestionRuletaDaSorteConstants {

    const val PROBA_VELOCIDADE_QUESTIONS_NUMBER = 5
    const val SCORE_PROBA_VELOCIDADE = "correct_answers_proba_velocidade"

    fun getQuestions(): MutableList<QuestionRuletaDaSorte> {
        var questions : MutableList<QuestionRuletaDaSorte> = mutableListOf()
        questions.add(
            QuestionRuletaDaSorte(
                "ENTRENADORA",
                "PERSOA"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "SEMPRE TEÑEN CALZADO DO TEU NÚMERO",
                "NA ZAPATERÍA"
            )
        )

        return questions
    }
}