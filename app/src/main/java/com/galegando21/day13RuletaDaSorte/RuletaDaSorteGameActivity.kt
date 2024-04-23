package com.galegando21.day13RuletaDaSorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.model.QuestionRuletaDaSorte
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.QuestionRuletaDaSorteConstants.MAX_CHARS_PER_LINE
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class RuletaDaSorteGameActivity : AppCompatActivity() {
    private lateinit var boardLayout: LinearLayout
    private lateinit var hintTv: TextView
    private lateinit var lettersLayout: ConstraintLayout

    private lateinit var questionList: List<QuestionRuletaDaSorte>
    private lateinit var question: QuestionRuletaDaSorte
    private var letterViews: List<TextView> = listOf()
    private var specialChars = listOf(',')
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_game)

        boardLayout = findViewById(R.id.ruleta_sorte_board_ll)
        hintTv = findViewById(R.id.ruleta_sorte_hint_tv)
        lettersLayout = findViewById(R.id.ruletaDaSorteLettersLayout)

        questionList = QuestionRuletaDaSorteConstants.getQuestions()
        startGame()

        setBanner(this, R.string.ruleta_da_sorte)
        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun startGame() {

        // Settear el board
        question = questionList.random()
        var lineLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            boardLayout.addView(this)
        }
        letterViews = question.board.mapIndexed { index, char ->
            if (index != 0 && index % MAX_CHARS_PER_LINE == 0) {
                lineLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    boardLayout.addView(this)
                }
            }
            TextView(this).apply {
                text = char.toString()
                textSize = 24f
                if (char != ' ') {
                    if (char in specialChars) {
                        setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.black))
                    } else {
                        setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.canela))
                    }
                    setBackgroundColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.canela))
                }
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    val marginHorizontal = if (char == ' ') 20 else 5
                    val marginVertical = 5
                    setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical) // Ajusta estos valores para cambiar la separación entre las letras
                }
                layoutParams = params
                lineLayout.addView(this)
            }
        }

        hintTv.text = question.hint

        // Settear el onClickListener de las letras
        lettersLayout.children.forEach {
                letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    playLetter((letterView).text[0])
                    if (isGameWon()) {
                        Intent(this, MainActivity::class.java).apply {
                            putExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, 1)
                            Log.d("RuletaDaSorteGameActivity", "Score: 1")
                            startActivity(this)
                            finish()
                        }
                    }
                    letterView.visibility = View.GONE
                }
            }
        }
    }

    private fun playLetter(letterGuessed: Char) {
        // Mapa de vocales sin tilde a vocales con tilde
        val accentMap = mapOf(
            'A' to listOf('Á'),
            'E' to listOf('É'),
            'I' to listOf('Í'),
            'O' to listOf('Ó'),
            'U' to listOf('Ú'),
        )

        // Revelar la letra jugada
        for (letter in letterViews) {
            if (letter.text == letterGuessed.toString()) {
                letter.setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.black))
            }

            // Si la letra jugada es una vocal, revelar también las vocales con tilde correspondientes
            if (accentMap.containsKey(letterGuessed)) {
                for (accentedLetter in accentMap[letterGuessed]!!) {
                    if (letter.text == accentedLetter.toString()) {
                        letter.setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.black))
                    }
                }
            }
        }
    }

    private fun isGameWon(): Boolean {
        // Verificar si el juego ha sido ganado
        for (letter in letterViews) {
            if (letter.currentTextColor == ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.canela)) {
                return false
            }
        }
        return true
    }
}