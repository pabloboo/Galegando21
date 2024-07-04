package com.galegando21.utils

import android.content.Context
import android.util.Log
import com.galegando21.model.WordDefinition
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object RagConstants {
    fun getRagWords(context: Context): List<String> {
        val jsonString: String
        try {
            jsonString = context.assets.open("rag.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val jsonArray = JSONArray(jsonString)
        val ragWords = mutableListOf<String>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            var word = jsonObject.getString("palabra")
            ragWords.add(word)
        }

        return ragWords
    }

    fun getRagWordsOfLength(context: Context, length: Int): List<String> {
        return getRagWords(context).filter { it.length == length }
    }

    fun getRagWordsOfLength(context: Context, minLength: Int, maxLength: Int): List<String> {
        return getRagWords(context).filter { it.length in minLength..maxLength }
    }

    fun getRagDefinitions(context: Context): List<WordDefinition> {
        val jsonString: String
        try {
            jsonString = context.assets.open("rag.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val jsonArray = JSONArray(jsonString)
        val ragDefinitions = mutableListOf<WordDefinition>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            var word = jsonObject.getString("palabra")
            var definition = jsonObject.getString("definicion")
            if (definition.isNotBlank() && definition.length > 1) {
                ragDefinitions.add(WordDefinition(word, definition))
            }
        }

        return ragDefinitions
    }

    fun getWordDefinition(context: Context, word: String): WordDefinition? {
        return getRagDefinitions(context).find { it.palabra == word }
    }
}