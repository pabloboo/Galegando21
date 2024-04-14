package com.galegando21.utils

import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaAnoFoto

object AdivinhaAnoFotoConstants {
    const val SCORE = "adivinha_ano_foto_correct_answers"

    fun getQuestions() : MutableList<QuestionAdivinhaAnoFoto> {
        val questions = mutableListOf<QuestionAdivinhaAnoFoto>()

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.presentacion_lucas_perez,
                "7000 persoas en Riazor aclaman a Lucas Pérez na súa presentación",
                2023
            )
        )

        return questions
    }
}