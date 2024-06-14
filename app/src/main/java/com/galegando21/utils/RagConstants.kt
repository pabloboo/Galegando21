package com.galegando21.utils

import android.content.Context
import com.galegando21.model.WordDefinition
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

        val jsonObject = JSONObject(jsonString)
        val ragWords = mutableListOf<String>()

        for (key in jsonObject.keys()) {
            ragWords.add(key)
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

        val jsonObject = JSONObject(jsonString)
        val ragDefinitions = mutableListOf<WordDefinition>()

        for (key in jsonObject.keys()) {
            val definition = jsonObject.getString(key)
            if (definition.isNotBlank() && definition.length > 1) {
                ragDefinitions.add(WordDefinition(key, definition))
            }
        }

        return ragDefinitions
    }

    fun getWordDefinition(context: Context, word: String): WordDefinition? {
        return getRagDefinitions(context).find { it.palabra == word }
    }
}