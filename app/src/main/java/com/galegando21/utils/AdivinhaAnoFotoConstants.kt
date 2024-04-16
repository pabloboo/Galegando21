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

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_1,
                "Matt Damon e Ben Affleck no estreno de 'Good Will Hunting'",
                1997
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_2,
                "Miley Cyrus baila no escenario con Robin Thicke nos MTV Video Music Awards",
                2014
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_3,
                "O elenco de \"Don't watching Darling\" no festival de cine de Valencia",
                2022
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_4,
                "Barack Obama e Michelle Obama encontranse xunto aos seus retratos despois da súa presentación no Smithsonian",
                2018
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_5,
                "Lee Radziwill e Truman Capote en branco e negro no Hotel Plaza",
                1966
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_6,
                "Suxeitador de Madonna con forma de cono",
                1990
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_7,
                "Voda do príncipe Harry e Meghan Markle",
                2018
                )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_8,
                "Diana baila con John Travolta na Casa Blanca",
                1985
                )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_9,
                "Matrimonio de Justing Bieber e Hailey Baldwin",
                2019
                )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_10,
                "Espectáculo de medio tempo da SuperBowl",
                2017
                )
        )

        return questions
    }
}