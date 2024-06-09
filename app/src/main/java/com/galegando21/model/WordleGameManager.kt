package com.galegando21.model

import android.content.Context
import android.util.Log
import com.galegando21.utils.WordleConstants
import kotlin.random.Random

class WordleGameManager(
    private val context: Context,
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
            if (curCol < 5) {
                curCol++
            }
            return true
        }
        return false
    }

    fun deleteChar() {
        if (curCol > 0) {
            rows[curRow][curCol-1] = ' '
            curCol--
        }
    }

    fun enter(): Boolean {
        if (curCol == 5 && curRow <= rowCount) {
            curCol = 0
            curRow++
            if (curRow == rowCount) {
                pouse = true
            }
            return true
        }
        return false
    }

    fun countLetterOccurrences(word: String): Map<Char, Int> {
        val occurrences = mutableMapOf<Char, Int>()
        for (char in word) {
            val count = occurrences[char]
            if (count != null) {
                occurrences[char] = count + 1
            } else {
                occurrences[char] = 1
            }
        }
        return occurrences
    }

    fun validateWord(row: Int): List<Pair<Char, Int>> {
        val letterOcurrences = countLetterOccurrences(word).toMutableMap()

        val result = mutableListOf<Pair<Char, Int>>()
        val attempt = rows[row].joinToString(separator="")
        for (col in 0 until attempt.length) {
            val char = attempt[col]
            if (char == word[col]) {
                result.add(Pair(char, IN_PLACE))
                letterOcurrences[char] = letterOcurrences[char]!! - 1
            } else {
                result.add(Pair(char, NOT_IN))
            }
        }

        // Marcar las letras en amarillo si no están marcadas en verde
        for (col in 0 until attempt.length) {
            val char = attempt[col]
            if ((char != word[col]) && (char in word) && (letterOcurrences[char]!! > 0)) {
                result[col] = Pair(char, IN_WORD)
            }
        }
        return result
    }

    fun getCurRow(): Int {
        return curRow
    }

    fun getCurCol(): Int {
        return curCol
    }

    fun setWord() {
        val words = WordleConstants.getWords(context)
        word = words[Random.nextInt(words.size)]
        Log.d("WordleGameManager", "Word: $word")
    }
}