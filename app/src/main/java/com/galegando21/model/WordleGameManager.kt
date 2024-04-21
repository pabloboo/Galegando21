package com.galegando21.model

import android.util.Log
import com.galegando21.utils.WordleConstants
import kotlin.random.Random

class WordleGameManager(
    private var rowCount: Int = 6
) {
    val IN_WORD = 0
    val IN_PLACE = 1
    val NOT_IN = 2

    private var pouse = false
    private var curRow: Int = 0;
    private var curCol: Int = 0;
    private var rows = mutableListOf<MutableList<Char>>();
    private lateinit var word: String;

    init {
        for (i in 0 until rowCount) {
            val row = MutableList(5) { ' ' }
            rows.add(row)
        }
    }

    fun getFinalWord(): String {
        return word
    }

    fun getResult(): Boolean {
        for (row in 0 until rowCount) {
            if (rows[row].joinToString(separator="") == word) {
                pouse = true
                return true
            }
        }
        return false
    }

    fun isPouse(): Boolean {
        return pouse
    }

    fun startOver() {
        curCol = 0
        curRow = 0
        pouse = false
        for (row in 0 until rowCount) {
            for (col in 0 until 5) {
                rows[row][col] = ' '
            }
        }
        setWord()
    }

    fun setNextChar(c: Char): Boolean {
        if (rows[curRow][curCol] == ' ') {
            rows[curRow][curCol] = c
            if (curCol < 4) {
                curCol++
            }
            return true
        }
        return false
    }

    fun deleteChar() {
        if (curCol > 0 && rows[curRow][curCol] == ' ') {
            curCol--
        }
        rows[curRow][curCol] = ' '
    }

    fun enter(): Boolean {
        if (curCol == 4 && curRow <= rowCount) {
            curCol = 0
            curRow++
            if (curRow == rowCount) {
                pouse = true
            }
            return true
        }
        return false
    }

    fun validateChar(row: Int, col: Int): Int {
        Log.d("TAG", "validateChar: $word, ${rows[row][col]}")
        if (rows[row][col] == word[col]) {
            return IN_PLACE
        } else if (rows[row][col] in word) {
            return IN_WORD
        }
        return NOT_IN
    }

    fun getCurRow(): Int {
        return curRow
    }

    fun getCurCol(): Int {
        return curCol
    }

    fun setWord() {
        val words = WordleConstants.getWords()
        word = words[Random.nextInt(words.size)]
    }
}