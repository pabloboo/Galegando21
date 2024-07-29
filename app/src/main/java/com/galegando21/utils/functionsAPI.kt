package com.galegando21.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import khttp.post
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