package com.galegando21.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.model.WordDefinition
import org.json.JSONObject
import java.io.IOException

object PalabrasBasicasConstants {
    fun getPalabrasBasicasWordDefinitions(context: Context): List<WordDefinition> {
        val jsonString: String
        try {
            val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
            val fileName = sharedPreferences.getString(SharedPreferencesKeys.PALABRAS_BASICAS_SOURCE, "palabras_basicas.json")
            Log.d("PalabrasBasicasConstants", "fileName: $fileName")
            jsonString = context.assets.open(fileName!!).bufferedReader().use { it.readText() }
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

    fun getPalabrasBasicasWordsOfLength(context: Context, length: Int): List<String> {
        return getPalabrasBasicasWords(context).filter { it.length == length }
    }

    fun getPalabrasBasicasWordsOfLength(context: Context, minLength: Int, maxLength: Int): List<String> {
        return getPalabrasBasicasWords(context).filter { it.length in minLength..maxLength }
    }
}