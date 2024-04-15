package com.galegando21.utils

import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaEscudo
import kotlin.random.Random

object AdivinhaEscudoConstants {
    const val SCORE = "adivinha_escudo_correct_answers"

    // Lista de nombres de lugares
    val lugares = listOf(
        "Abegondo",
        "Ames",
        "Arteixo",
        "Arzua",
        "Betanzos",
        "Boimorto",
        "Boiro",
        "Boqueixon",
        "Cangas",
        "Carballo",
        "Carral",
        "Cerceda",
        "Cuntis",
        "Curtis",
        "Estrada",
        "Ferrol",
        "Frades",
        "Lalin",
        "Lugo",
        "Muxia",
        "Noia",
        "Ordes",
        "Oroso",
        "Ourense",
        "Padron",
        "Pontedeume",
        "Rianxo",
        "Ribadavia",
        "Santiago de Compostela",
        "Sarria",
        "Touro",
        "Vimianzo"
    )

    // Función para seleccionar tres opciones aleatorias de la lista de lugares
    fun seleccionarOpcionesAleatorias(): List<String> {
        val opcionesAleatorias = lugares.shuffled().take(3)
        return opcionesAleatorias
    }

    // Función para obtener preguntas
    fun getQuestions(): MutableList<QuestionAdivinhaEscudo> {
        val questions = mutableListOf<QuestionAdivinhaEscudo>()

        lugares.forEach { lugar ->
            // Obtener tres opciones aleatorias
            val opciones = seleccionarOpcionesAleatorias().toMutableList()

            // Crear la pregunta y agregarla a la lista
            val randomPosition = Random.nextInt(1, 4)
            when (randomPosition) {
                1 -> questions.add(
                    QuestionAdivinhaEscudo(
                        obtenerDrawable(lugar),
                        lugar,
                        opciones[0],
                        opciones[1],
                        opciones[2],
                        1
                    )
                )
                2 -> questions.add(
                    QuestionAdivinhaEscudo(
                        obtenerDrawable(lugar),
                        opciones[0],
                        lugar,
                        opciones[1],
                        opciones[2],
                        2
                    )
                )
                3 -> questions.add(
                    QuestionAdivinhaEscudo(
                        obtenerDrawable(lugar),
                        opciones[0],
                        opciones[1],
                        lugar,
                        opciones[2],
                        3
                    )
                )
                4 -> questions.add(
                    QuestionAdivinhaEscudo(
                        obtenerDrawable(lugar),
                        opciones[0],
                        opciones[1],
                        opciones[2],
                        lugar,
                        4
                    )
                )
            }
        }
        return questions
    }

    fun obtenerDrawable(nombreLugar: String): Int {
        return when (nombreLugar) {
            "Abegondo" -> R.drawable.escudo_abegondopng
            "Ames" -> R.drawable.escudo_ames
            "Arteixo" -> R.drawable.escudo_arteixo
            "Arzua" -> R.drawable.escudo_arzua
            "Betanzos" -> R.drawable.escudo_betanzos
            "Boimorto" -> R.drawable.escudo_boimorto
            "Boiro" -> R.drawable.escudo_boiro
            "Boqueixon" -> R.drawable.escudo_boqueixon
            "Cangas" -> R.drawable.escudo_cangas
            "Carballo" -> R.drawable.escudo_carballo
            "Carral" -> R.drawable.escudo_carral
            "Cerceda" -> R.drawable.escudo_cerceda
            "Cuntis" -> R.drawable.escudo_cuntis
            "Curtis" -> R.drawable.escudo_curtis
            "Estrada" -> R.drawable.escudo_estrada
            "Ferrol" -> R.drawable.escudo_ferrol
            "Frades" -> R.drawable.escudo_frades
            "Lalin" -> R.drawable.escudo_lalin
            "Lugo" -> R.drawable.escudo_lugo
            "Muxia" -> R.drawable.escudo_muxia
            "Noia" -> R.drawable.escudo_noia
            "Ordes" -> R.drawable.escudo_ordes
            "Oroso" -> R.drawable.escudo_oroso
            "O Pino" -> R.drawable.escudo_o_pino
            "Ourense" -> R.drawable.escudo_ourense
            "Padron" -> R.drawable.escudo_padron
            "Pontedeume" -> R.drawable.escudo_pontedeume
            "Rianxo" -> R.drawable.escudo_rianxo
            "Ribadavia" -> R.drawable.escudo_ribadavia
            "Santiago de Compostela" -> R.drawable.escudo_santiago_de_compostela
            "Sarria" -> R.drawable.escudo_sarria
            "Touro" -> R.drawable.escudo_touro
            "Vimianzo" -> R.drawable.escudo_vimianzo
            else -> R.drawable.g21_logo // Si no se encuentra el nombre del lugar, se devuelve un drawable predeterminado
        }
    }
}