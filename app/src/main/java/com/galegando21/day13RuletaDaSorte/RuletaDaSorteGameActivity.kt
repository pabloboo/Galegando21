package com.galegando21.day13RuletaDaSorte

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
import kotlin.random.Random

class RuletaDaSorteGameActivity : AppCompatActivity() {
    private lateinit var boardLayout: LinearLayout
    private lateinit var hintTv: TextView
    private lateinit var tirarRuletaButton: Button
    private lateinit var ruletaAccionTextView: TextView
    private lateinit var cashTv: TextView
    private lateinit var lettersLayout: ConstraintLayout

    private lateinit var questionList: List<QuestionRuletaDaSorte>
    private lateinit var question: QuestionRuletaDaSorte
    private var letterViews: List<TextView> = listOf()
    private var specialChars = listOf(',')
    private var nextMultiplicadorAccion = 0 // Multiplicador de la acción de la ruleta
    private var cash = 0 // Dinero acumulado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_game)

        boardLayout = findViewById(R.id.ruleta_sorte_board_ll)
        hintTv = findViewById(R.id.ruleta_sorte_hint_tv)
        tirarRuletaButton = findViewById(R.id.ruleta_sorte_button)
        ruletaAccionTextView = findViewById(R.id.ruleta_sorte_accion_ruleta_tv)
        cashTv = findViewById(R.id.ruleta_sorte_cash_tv)
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
        cashTv.text = "$cash€"

        //La ruleta aún no ha sido tirada
        setVisibility(false)
        tirarRuletaButton.setOnClickListener {
            tirarRuleta()
        }
    }

    private fun playLetter(letterGuessed: Char) {
        var numAciertos = 0
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
                numAciertos++
            }

            // Si la letra jugada es una vocal, revelar también las vocales con tilde correspondientes
            if (accentMap.containsKey(letterGuessed)) {
                for (accentedLetter in accentMap[letterGuessed]!!) {
                    if (letter.text == accentedLetter.toString()) {
                        letter.setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.black))
                        numAciertos++
                    }
                }
            }
        }

        // Actualizar el dinero acumulado
        cash += numAciertos * nextMultiplicadorAccion
        cashTv.text = "$cash€"
        // La letra ya ha sido jugada -> tirar ruleta
        setVisibility(false)
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

    private fun tirarRuleta() {
        playRuleta()
        setVisibility(true)
    }

    private fun playRuleta() {
        // Tirar la ruleta
        val acciones = listOf<String>("0", "25", "50", "75", "100", "150", "bote", "quiebra")
        val random = Random.nextInt(1, 100)
        val accion = when {
            random <= 5 -> acciones[0]
            random <= 15 -> acciones[1]
            random <= 40 -> acciones[2]
            random <= 75 -> acciones[3]
            random <= 94 -> acciones[4]
            random <= 96 -> acciones[5]
            random <= 100 -> acciones[6]
            else -> acciones[0]
        }

        // ANIMACIÓN DE TIRAR LA RULETA
        // Crear el ValueAnimator
        val animator = ValueAnimator.ofInt(0, acciones.size * 3)
        animator.duration = 2000
        // Actualizar el texto de ruletaAccionTextView en cada actualización de la animación
        animator.addUpdateListener { animation ->
            val currentIndex = animation.animatedValue as Int
            ruletaAccionTextView.text = acciones[currentIndex % acciones.size]
        }
        // Cuando la animación termine, establecer el texto final
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                playRuletaAccion(accion)
            }
        })
        // Iniciar la animación
        animator.start()
    }

    private fun playRuletaAccion(accion: String) {
        // Realizar la acción de la ruleta
        when (accion) {
            "0" -> {
                ruletaAccionTextView.text = "0"
                nextMultiplicadorAccion = 0
            }
            "25" -> {
                ruletaAccionTextView.text = "25"
                nextMultiplicadorAccion = 25
            }
            "50" -> {
                ruletaAccionTextView.text = "50"
                nextMultiplicadorAccion = 50
            }
            "75" -> {
                ruletaAccionTextView.text = "75"
                nextMultiplicadorAccion = 75
            }
            "100" -> {
                ruletaAccionTextView.text = "100"
                nextMultiplicadorAccion = 100
            }
            "150" -> {
                ruletaAccionTextView.text = "150"
                nextMultiplicadorAccion = 150
            }
            "bote" -> {
                ruletaAccionTextView.text = "¡Bote!"
                nextMultiplicadorAccion = 0
                cash+=2000

            }
            "quiebra" -> {
                ruletaAccionTextView.text = "¡Quiebra!"
                nextMultiplicadorAccion = 0
                cash=0
            }
        }
    }

    private fun setVisibility(isRuletaTirada: Boolean) {
        // Cambiar la visibilidad de los elementos de la pantalla
        if (isRuletaTirada) {
            tirarRuletaButton.visibility = View.INVISIBLE
            ruletaAccionTextView.visibility = View.VISIBLE
            setLettersOnClickListeners()
        } else {
            tirarRuletaButton.visibility = View.VISIBLE
            ruletaAccionTextView.visibility = View.GONE
            hideLettersOnClickListeners()
        }
    }

    private fun setLettersOnClickListeners() {
        // Settear el onClickListener de las letras
        lettersLayout.children.forEach {
                letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    playLetter((letterView).text[0])
                    if (isGameWon()) {
                        Intent(this, RuletaDaSorteResultsActivity::class.java).apply {
                            putExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, cash)
                            Log.d("RuletaDaSorteGameActivity", "$cash")
                            startActivity(this)
                            finish()
                        }
                    }
                    letterView.visibility = View.GONE
                }
            }
        }
    }

    private fun hideLettersOnClickListeners() {
        // Ocultar el onClickListener de las letras
        lettersLayout.children.forEach {
                letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener(null)
            }
        }
    }
}