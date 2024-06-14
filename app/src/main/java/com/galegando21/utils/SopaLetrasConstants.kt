package com.galegando21.utils

import android.content.Context
import com.galegando21.model.QuestionSopaLetras

object SopaLetrasConstants {
    const val TABLEIROS_ACERTADOS = "tableiros_acertados_sopa_letras"
    const val SCORE = "score_sopa_letras"
    const val NIVEL_FACIL = "facil"
    const val NIVEL_MEDIO = "medio"
    const val NIVEL_DIFICIL = "dificil"

    fun getSopasLetrasConPista(): MutableList<QuestionSopaLetras> {
        var sopasLetrasList : MutableList<QuestionSopaLetras> = mutableListOf()
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Cousas que se poden atopar nunha casa",
                "TELE", "SOFA", "CAMA"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Cousas que se poden atopar nunha oficina",
                "RATO", "MESA", "BOLI"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Cousas que se poden atopar nunha escola",
                "LAPIZ", "LIBRO", "MESA"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Cousas que poden ser estrictas",
                "DIETA", "NORMA", "FE"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Non se compra con diñeiro",
                "AMOR", "AMIGO", "SAUDE"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Relativo a un cheque",
                "BANCO", "TALON", "FIRMA"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "En unha ingrexa",
                "ALTAR", "TORRE", "CRUZ"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Tomase para almorzar",
                "ZUMO", "CAFE", "LEITE"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Falar...",
                "SO", "MOITO", "ALTO"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Apelidos españois que terminan en Z",
                "PEREZ", "GOMEZ", "SANZ"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Estudase en filosofía",
                "RAZON", "MORAL", "ETICA"
            )
        )
        sopasLetrasList.add(
            QuestionSopaLetras(
                "Obxectos que usa un mineiro",
                "PICO", "PALA", "CASCO"
            )
        )

        return sopasLetrasList
    }

    fun getSopasLetras(context: Context, maxWordLength: Int): QuestionSopaLetras {
        // Obtener 3 palabras aleatorias de AforcadoConstants.getWords() que tengan máximo 5 letras
        val words = DictionaryConstants.getWords(context)
        val wordsFiltered = words.filter { it.length <= maxWordLength }
        val wordsRandom = wordsFiltered.shuffled().take(3)
        return QuestionSopaLetras(
                "",
                wordsRandom[0], wordsRandom[1], wordsRandom[2]
            )
    }
}