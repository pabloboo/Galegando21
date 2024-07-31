package com.galegando21.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import khttp.get
import org.json.JSONArray
import org.json.JSONObject

suspend fun getChallenge(experience: Int): String {
    return withContext(Dispatchers.IO) {
        val response = get(
            url = "http://pabloboo.pythonanywhere.com/api/desafio-personalizado",
            params = mapOf("experience" to experience.toString())
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

suspend fun getFeedback(streak: Int, experience: Int, badges: Int): String {
    return withContext(Dispatchers.IO) {
        val response = get(
            url = "http://pabloboo.pythonanywhere.com/api/feedback",
            params = mapOf("streak" to streak.toString(), "experience" to experience.toString(), "badges" to badges.toString())
        )

        if (response.statusCode == 200) {
            val feedbackJson = JSONObject(response.text)
            feedbackJson.getString("feedback")
        } else {
            ""
        }
    }
}