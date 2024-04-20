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

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_11,
                "Espectáculo de medio tempo da SuperBowl",
                2016
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_12,
                "Fran Sinatra prende un cigarro para a actriz Lauren Bacall en unha festa de estrea para 'Una estrella nace'",
                1954
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_13,
                "Pink Floyd publicou 'The Dark Side of the Moon'",
                1973
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_14,
                "Os cantantes Frank Sinatra e Nat King Cole asisten a un club de frailes de Nova York",
                1950
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_15,
                "Cardi B e Megan Thee Stallion no seu video musical 'WAP'",
                2020
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_16,
                "Atendados terroristas 11-M en España",
                2004
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_17,
                "Conferencia para a creación da UNESCO en Londres",
                1945
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_18,
                "Un empleado abandona a oficina de Lehman Brothers tras a súa quebra",
                2008
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_19,
                "Celebración icónica de Diego Maranoda contra Grecia",
                1994
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_20,
                "No seu título de peso pesado, Ali derrotou a Liston por knockout na primeira ronda",
                1965
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_21,
                "Wonder Woman, retratada pola actriz Lynda Carter",
                1975
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_22,
                "Mc Hammer Smash Hit 'Non podes tocar isto'",
                1990
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_23,
                "amazon.com foi iniciado como unha librería en liña por Jeff Bezos no seu garaxe",
                1994
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_24,
                "Estrela de fútbol O.J. Simpson é absolto do brutal dobre asesinato de 1994",
                1995
            )
        )

        questions.add(
            QuestionAdivinhaAnoFoto(
                R.drawable.adivinar_ano_25,
                "Elizabeth Taylor e os seus fillos no set da dúa película Cleopatra",
                1962
            )
        )

        return questions
    }
}