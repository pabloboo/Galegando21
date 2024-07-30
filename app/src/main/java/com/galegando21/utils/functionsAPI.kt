package com.galegando21.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import khttp.post
import khttp.get
import org.json.JSONArray
import org.json.JSONObject

suspend fun getChallenge(experience: Int): String {
    return withContext(Dispatchers.IO) {
        val response = post(
            url = "http://pabloboo.pythonanywhere.com/api/desafio-personalizado",
            json = mapOf("experience" to experience)
        )

        if (response.statusCode == 200) {
            val challenge = JSONObject(response.text)
            challenge.getString("desafio")
        } else {
            "Non se puido obter o desafío"
        }
    }
}

data class Evento(
    val nome: String,
    val data: String,
    val ubicacion: String,
    val descricion: String
)

suspend fun getEventos(): List<Evento> {
    return withContext(Dispatchers.IO) {
        val response = get("http://pabloboo.pythonanywhere.com/api/eventos")

        if (response.statusCode == 200) {
            val jsonArray = JSONArray(response.text)
            List(jsonArray.length()) { i ->
                val json = jsonArray.getJSONObject(i)
                Evento(
                    json.getString("nome"),
                    json.getString("data"),
                    json.getString("ubicacion"),
                    json.getString("descricion")
                )
            }
        } else {
            emptyList()
        }
    }
}