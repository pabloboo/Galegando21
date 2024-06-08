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
            var definition = jsonObject.getString("definicion")
            wordDefinitions.add(WordDefinition(word, definition))
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