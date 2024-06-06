package com.galegando21.utils

import android.content.Context
import com.galegando21.model.WordDefinition
import org.json.JSONArray
import java.io.IOException

object DigalegoConstants {
    fun getWordDefinitions(context: Context): List<WordDefinition> {
        val jsonString: String
        try {
            jsonString = context.assets.open("digalegodefiniciones.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val jsonArray = JSONArray(jsonString)
        val wordDefinitions = mutableListOf<WordDefinition>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            var word = jsonObject.getString("palabra")
            word = removeAccents(word)
            word = word.split(" ")[0].uppercase()

            // Comprobar si todos los caracteres de la palabra están en ALFABETO
            if (word.all { it in ALFABETO }) {
                var definition = if (jsonObject.isNull("definicion")) null else jsonObject.getString("definicion")
                if (definition == "" || definition == ".") {
                    definition = null
                }
                wordDefinitions.add(WordDefinition(word, definition))
            }
        }

        return wordDefinitions
    }

    fun getWords(context: Context): List<String> {
        return getWordDefinitions(context).map { it.palabra }
    }

    fun getWordsOfLength(context: Context, length: Int): List<String> {
        return getWords(context).filter { it.length == length }
    }
}