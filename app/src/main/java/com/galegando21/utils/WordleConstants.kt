package com.galegando21.utils

import android.content.Context

object WordleConstants {
    fun getWords(context: Context): List<String> {
        return DigalegoConstants.getWordsOfLength(context, 5)
    }
}