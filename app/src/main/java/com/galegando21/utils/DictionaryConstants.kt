package com.galegando21.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.model.WordDefinition

object DictionaryConstants {
    const val RAG_SOURCE = "real_academia_galega_source"
    const val DIGALEGO_SOURCE = "digalego_source"

    fun getWordDefinitions(context: Context): List<WordDefinition> {
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
        val source = sharedPreferences.getString(SharedPreferencesKeys.DICTIONARY_SOURCE, RAG_SOURCE)

        if (source == RAG_SOURCE) {
            return RagConstants.getRagDefinitions(context)
        } else if (source == DIGALEGO_SOURCE) {
            return DigalegoConstants.getWordDefinitions(context)
        }
        return RagConstants.getRagDefinitions(context)
    }

    fun getWordDefinition(context: Context, word: String): WordDefinition? {
        return getWordDefinitions(context).find { it.palabra == word }
    }

    fun getWords(context: Context): List<String> {
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
        val source = sharedPreferences.getString(SharedPreferencesKeys.DICTIONARY_SOURCE, RAG_SOURCE)

        if (source == RAG_SOURCE) {
            return RagConstants.getRagWords(context)
        } else if (source == DIGALEGO_SOURCE) {
            return DigalegoConstants.getWords(context)
        }
        return RagConstants.getRagWords(context)
    }

    fun getWordsOfLength(context: Context, length: Int): List<String> {
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
        val source = sharedPreferences.getString(SharedPreferencesKeys.DICTIONARY_SOURCE, RAG_SOURCE)

        if (source == RAG_SOURCE) {
            return RagConstants.getRagWordsOfLength(context, length)
        } else if (source == DIGALEGO_SOURCE) {
            return DigalegoConstants.getWordsOfLength(context, length)
        }
        return RagConstants.getRagWordsOfLength(context, length)
    }

}