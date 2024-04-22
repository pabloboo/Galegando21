package com.galegando21.utils

import com.galegando21.R
import com.galegando21.model.QuestionAdivinhaEscudo
import kotlin.random.Random

object AdivinhaEscudoConstants {
    const val SCORE = "adivinha_escudo_correct_answers"

    // Lista de nombres de lugares
    val lugares = listOf(
        "Abegondo",
        "A Coruña",
        "Allariz",
        "Ames",
        "Ares",
        "Arteixo",
        "Arzua",
        "Barco de Baldeorras",
        "Beade",
        "Bergondo",
        "Betanzos",
        "Boimorto",
        "Boiro",
        "Boqueixon",
        "Caldas de Reis",
        "Camariñas",
        "Cambados",
        "Cambre",
        "Cangas",
        "Carballo",
        "Cariño",
        "Carnota",
        "Carral",
        "Cedeira",
        "Cee",
        "Cerceda",
        "Chantada",
        "Cuntis",
        "Curtis",
        "Culleredo",
        "Estrada",
        "Fene",
        "Ferrol",
        "Fisterra",
        "Frades",
        "Illa de Arousa",
        "Lalin",
        "Lugo",
        "Malpica de Bergantiños",
        "Manzaneda",
        "Melide",
        "Mondoñedo",
        "Monforte de Lemos",
        "Monterroso",
        "Muros",
        "Naron",
        "Nigran",
        "Noia",
        "O Incio",
        "Oleiros",
        "Ordes",
        "Oroso",
        "Ourense",
        "Padron",
        "Pobra do Caramiñal",
        "Pontecesures",
        "Pontedeume",
        "Porto do Son",
        "Redondela",
        "Rianxo",
        "Ribadavia",
        "Ribeira",
        "Santiago de Compostela",
        "Sarria",
        "Touro",
        "Trazo",
        "Tui",
        "Vigo",
        "Vilagarcia de Arousa",
        "Vila de Cruces",
        "Vimianzo"
    )

    // Función para seleccionar tres opciones aleatorias de la lista de lugares
    fun seleccionarOpcionesAleatorias(): MutableList<String> {
        val opcionesAleatorias = lugares.shuffled().take(3)
        return opcionesAleatorias.toMutableList()
    }

    // Función para obtener preguntas
    fun getQuestions(): MutableList<QuestionAdivinhaEscudo> {
        val questions = mutableListOf<QuestionAdivinhaEscudo>()

        lugares.forEach { lugar ->
            // Obtener tres opciones aleatorias
            val opciones = seleccionarOpcionesAleatorias()
            // Commprobar que la solución no se encuentra entre las opciones aleatorias
            while (opciones.contains(lugar)) {
                opciones.clear()
                opciones.addAll(seleccionarOpcionesAleatorias())
            }

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
            "A Coruña" -> R.drawable.escudo_a_corunha
            "Allariz" -> R.drawable.escudo_allariz
            "Ames" -> R.drawable.escudo_ames
            "Ares" -> R.drawable.escudo_ares
            "Arteixo" -> R.drawable.escudo_arteixo
            "Arzua" -> R.drawable.escudo_arzua
            "Barco de Baldeorras" -> R.drawable.escudo_barco_de_baldeorras
            "Beade" -> R.drawable.escudo_beade
            "Bergondo" -> R.drawable.escudo_bergondo
            "Betanzos" -> R.drawable.escudo_betanzos
            "Boimorto" -> R.drawable.escudo_boimorto
            "Boiro" -> R.drawable.escudo_boiro
            "Boqueixon" -> R.drawable.escudo_boqueixon
            "Caldas de Reis" -> R.drawable.escudo_caldas_de_reis
            "Camariñas" -> R.drawable.escudo_camarinhas
            "Cambados" -> R.drawable.escudo_cambados
            "Cambre" -> R.drawable.escudo_cambre
            "Cangas" -> R.drawable.escudo_cangas
            "Carballo" -> R.drawable.escudo_carballo
            "Cariño" -> R.drawable.escudo_carinho
            "Carnota" -> R.drawable.escudo_carnota
            "Carral" -> R.drawable.escudo_carral
            "Cedeira" -> R.drawable.escudo_cedeira
            "Cee" -> R.drawable.escudo_cee
            "Cerceda" -> R.drawable.escudo_cerceda
            "Chantada" -> R.drawable.escudo_chantada
            "Cuntis" -> R.drawable.escudo_cuntis
            "Curtis" -> R.drawable.escudo_curtis
            "Culleredo" -> R.drawable.escudo_culleredo
            "Estrada" -> R.drawable.escudo_estrada
            "Fene" -> R.drawable.escudo_fene
            "Ferrol" -> R.drawable.escudo_ferrol
            "Fisterra" -> R.drawable.escudo_fisterra
            "Frades" -> R.drawable.escudo_frades
            "Illa de Arousa" -> R.drawable.escudo_illa_de_arousa
            "Lalin" -> R.drawable.escudo_lalin
            "Lugo" -> R.drawable.escudo_lugo
            "Malpica de Bergantiños" -> R.drawable.escudo_malpica_de_bergantinhos
            "Manzaneda" -> R.drawable.escudo_manzaneda
            "Melide" -> R.drawable.escudo_melide
            "Mondoñedo" -> R.drawable.escudo_mondonhedo
            "Monforte de Lemos" -> R.drawable.escudo_monforte_de_lemos
            "Monterroso" -> R.drawable.escudo_monterroso
            "Muros" -> R.drawable.escudo_muros
            "Naron" -> R.drawable.escudo_naron
            "Nigran" -> R.drawable.escudo_nigran
            "Noia" -> R.drawable.escudo_noia
            "O Incio" -> R.drawable.escudo_o_incio
            "Oleiros" -> R.drawable.escudo_oleiros
            "Ordes" -> R.drawable.escudo_ordes
            "Oroso" -> R.drawable.escudo_oroso
            "Ourense" -> R.drawable.escudo_ourense
            "Padron" -> R.drawable.escudo_padron
            "Pobra do Caramiñal" -> R.drawable.escudo_pobra_do_caraminhal
            "Pontecesures" -> R.drawable.escudo_pontecesures
            "Pontedeume" -> R.drawable.escudo_pontedeume
            "Porto do Son" -> R.drawable.escudo_porto_do_son
            "Redondela" -> R.drawable.escudo_redondela
            "Rianxo" -> R.drawable.escudo_rianxo
            "Ribadavia" -> R.drawable.escudo_ribadavia
            "Ribeira" -> R.drawable.escudo_ribeira
            "Santiago de Compostela" -> R.drawable.escudo_santiago_de_compostela
            "Sarria" -> R.drawable.escudo_sarria
            "Touro" -> R.drawable.escudo_touro
            "Trazo" -> R.drawable.escudo_trazo
            "Tui" -> R.drawable.escudo_tui
            "Vigo" -> R.drawable.escudo_vigo
            "Vilagarcia de Arousa" -> R.drawable.escudo_vilagarcia_de_arousa
            "Vila de Cruces" -> R.drawable.escudo_vila_de_cruces
            "Vimianzo" -> R.drawable.escudo_vimianzo
            else -> R.drawable.g21_logo // Si no se encuentra el nombre del lugar, se devuelve un drawable predeterminado
        }
    }
}