package com.galegando21.utils

import android.content.Context
import android.util.Log
import com.galegando21.model.QuestionRuletaDaSorte
import org.json.JSONArray
import java.io.IOException

object RefransConstants {

    fun getRefran(context: Context): String {
        val jsonString: String
        try {
            jsonString = context.assets.open("refrans.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }

        val jsonArray = JSONArray(jsonString)
        val randomIndex = (0 until jsonArray.length()).random()
        val refran = jsonArray.get(randomIndex).toString()
        Log.d("Refran", refran.uppercase())
        return refran.uppercase()
    }

    fun getRefranRuletaSorte(context: Context): List<QuestionRuletaDaSorte> {
        val refran = getRefran(context)
        val question = QuestionRuletaDaSorte(refran, "")
        return listOf(question)
    }

    fun getRefransProbaVelocidade(context: Context) : List<QuestionRuletaDaSorte> {
        val jsonString: String
        try {
            jsonString = context.assets.open("refrans.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val jsonArray = JSONArray(jsonString)
        val questionList: MutableList<QuestionRuletaDaSorte> = mutableListOf()

        for (i in 0 until jsonArray.length()) {
            val refran = jsonArray.get(i).toString().uppercase()
            val question = QuestionRuletaDaSorte(refran, "")
            questionList.add(question)
        }

        return questionList
    }

}