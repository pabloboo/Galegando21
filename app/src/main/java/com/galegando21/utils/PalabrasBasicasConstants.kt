package com.galegando21.utils

import android.content.Context
import com.galegando21.model.WordDefinition
import org.json.JSONObject
import java.io.IOException

object PalabrasBasicasConstants {
    fun getPalabrasBasicasWordDefinitions(context: Context): List<WordDefinition> {
        val jsonString: String
        try {
            jsonString = context.assets.open("palabras_basicas.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val jsonObject = JSONObject(jsonString)
        val palabrasBasicasWords = mutableListOf<WordDefinition>()

        for (key in jsonObject.keys()) {
            val definition = jsonObject.getString(key)
            palabrasBasicasWords.add(WordDefinition(key, definition))
        }

        return palabrasBasicasWords
    }

    fun getPalabrasBasicasWords(context: Context): List<String> {
        return getPalabrasBasicasWordDefinitions(context).map { it.palabra }
    }

    fun getWordDefinition(context: Context, word: String): WordDefinition? {
        return getPalabrasBasicasWordDefinitions(context).find { it.palabra == word }
    }

    fun getRagWordsOfLength(context: Context, length: Int): List<String> {
        return getPalabrasBasicasWords(context).filter { it.length == length }
    }

    fun getRagWordsOfLength(context: Context, minLength: Int, maxLength: Int): List<String> {
        return getPalabrasBasicasWords(context).filter { it.length in minLength..maxLength }
    }
}