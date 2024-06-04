package com.galegando21.utils

import com.galegando21.model.QuestionSopaLetras

object SopaLetrasConstants {
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
}