package com.galegando21.utils

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

        return sopasLetrasList
    }

    fun getSopasLetras(maxWordLength: Int): QuestionSopaLetras {
        // Obtener 3 palabras aleatorias de AforcadoConstants.getWords() que tengan máximo 5 letras
        val words = AforcadoGameConstants.getWords()
        val wordsFiltered = words.filter { it.length <= maxWordLength }
        val wordsRandom = wordsFiltered.shuffled().take(3)
        return QuestionSopaLetras(
                "",
                wordsRandom[0], wordsRandom[1], wordsRandom[2]
            )
    }
}