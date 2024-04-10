package com.galegando21.utils

import com.galegando21.model.Conexions

object ConexionsGameConstants {
    fun getConexions() : MutableList<Conexions> {
        var conexionsList : MutableList<Conexions> = mutableListOf()
        conexionsList.add(
            Conexions(
                "Alboroto", "Xenxibre", "Arroz", "Dardo",
                "Revolto", "Machado", "Prisa", "Anel",
                "Algas", "Ferradura", "Ferver", "Patada",
                "Sésamo", "Fritir", "Emoción", "Escalfar",
                "Formas de preparar ovos", listOf("Ferver", "Fritir", "Escalfar", "Revolto"),
                "Sentimento de euforia", listOf("Alboroto", "Patada", "Prisa", "Emoción"),
                "Lanzados en xogos de precisión", listOf("Machado", "Dardo", "Ferradura", "Anel"),
                "Ingredintes de cociña asiática", listOf("Sésamo", "Algas", "Xenxibre", "Arroz")
            )
        )
        conexionsList.add(
            Conexions(
                "Fiction", "Capitán", "Verde", "Terreos",
                "Campo", "Iniciación", "Bastardos", "Celebración",
                "Director", "Transformación", "Líder", "Odiosos",
                "Renacemento", "Desencadenado", "Céspede", "Xefe",
                "Persoas en cargo", listOf("Director", "Xefe", "Líder", "Capitán"),
                "Zona herbosa", listOf("Campo", "Verde", "Terreos", "Céspede"),
                "Elementos comúns en rituais de paso", listOf("Iniciación", "Transformación", "Renacemento", "Celebración"),
                "Segundas palabras en películas de Tarantino", listOf("Fiction", "Desencadenado", "Odiosos", "Bastardos")
            )
        )
        return conexionsList
    }
}