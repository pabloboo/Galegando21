package com.galegando21.day09RuletaDaSorte

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.QuestionRuletaDaSorte
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.QuestionRuletaDaSorteConstants.MAX_CHARS_PER_LINE
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlinx.coroutines.launch

class RuletaDaSorteGameActivity : AppCompatActivity() {
    private lateinit var boardLayout: LinearLayout
    private lateinit var hintTv: TextView
    private lateinit var tirarRuletaButton: Button
    private lateinit var ruletaAccionImageView: ImageView
    private lateinit var cashTv: TextView

    val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "l", "m", "n", "nh", "o", "p", "q", "r", "s", "t", "u", "v", "x", "z")
    private lateinit var questionList: List<QuestionRuletaDaSorte>
    private lateinit var question: QuestionRuletaDaSorte
    private var letterViews: List<TextView> = listOf()
    private var specialChars = listOf(',')
    private var nextMultiplicadorAccion = 0 // Multiplicador de la acción de la ruleta
    private var cash = 0 // Dinero acumulado
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_game)

        boardLayout = findViewById(R.id.ruleta_sorte_board_ll)
        hintTv = findViewById(R.id.ruleta_sorte_hint_tv)
        tirarRuletaButton = findViewById(R.id.ruleta_sorte_button)
        ruletaAccionImageView = findViewById(R.id.ruleta_sorte_accion_ruleta_image_view)
        cashTv = findViewById(R.id.ruleta_sorte_cash_tv)

        questionList = QuestionRuletaDaSorteConstants.getQuestions()
        startGame()

        setBanner(this, R.string.ruleta_da_sorte)
        setOnBackPressed(this, RuletaDaSorteInicioActivity::class.java)
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

    private suspend fun animationRevealLetter(letterView: TextView) {
        letterView.setBackgroundColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.orange))
        letterView.setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.orange))
        delay(1000)
        val flipAnimation = AnimatorInflater.loadAnimator(this@RuletaDaSorteGameActivity, R.animator.flip)
        flipAnimation.setTarget(letterView)
        flipAnimation.start()
        letterView.setBackgroundColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.canela))
        letterView.setTextColor(ContextCompat.getColor(this@RuletaDaSorteGameActivity, R.color.black))
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
                coroutineScope.launch {
                    // Animación de revelar la letra
                    animationRevealLetter(letter)
                }
                numAciertos++
            }

            // Si la letra jugada es una vocal, revelar también las vocales con tilde correspondientes
            if (accentMap.containsKey(letterGuessed)) {
                for (accentedLetter in accentMap[letterGuessed]!!) {
                    if (letter.text == accentedLetter.toString()) {
                        coroutineScope.launch {
                            // Animación de revelar la letra
                            animationRevealLetter(letter)
                        }
                        numAciertos++
                    }
                }
            }
        }

        //Si falló la letra no está pierde la partida
        if (numAciertos == 0) {
            cash = 0
            finalizarRuletaDaSorte()
        } else { // Acertó -> Animación de sonido
            val mediaPlayer = MediaPlayer.create(this@RuletaDaSorteGameActivity, R.raw.flip_letter)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { it.release() }
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
        val accionesImagen = listOf<Drawable>(
            ContextCompat.getDrawable(this, R.drawable.ruleta_0)!!,
            ContextCompat.getDrawable(this, R.drawable.ruleta_50)!!,
            ContextCompat.getDrawable(this, R.drawable.ruleta_100)!!,
            ContextCompat.getDrawable(this, R.drawable.ruleta_150)!!,
            ContextCompat.getDrawable(this, R.drawable.ruleta_bote)!!,
            ContextCompat.getDrawable(this, R.drawable.ruleta_quebra)!!
        )
        val acciones = listOf<String>("0", "50", "100", "150", "bote", "quebra")
        val random = Random.nextInt(1, 100)
        val accion = when {
            random <= 10 -> acciones[0]
            random <= 35 -> acciones[1]
            random <= 75 -> acciones[2]
            random <= 94 -> acciones[3]
            random <= 96 -> acciones[4]
            random <= 100 -> acciones[5]
            else -> acciones[0]
        }

        // ANIMACIÓN DE TIRAR LA RULETA
        // Crear el ValueAnimator
        val animator = ValueAnimator.ofInt(0, acciones.size * 3 + acciones.indexOf(accion))
        animator.duration = 2000

        // Crear la animación de escala
        val scaleAnimation = ScaleAnimation(
            1f, 3f,
            1f, 3f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 2000

        // Actualizar el texto de ruletaAccionTextView en cada actualización de la animación
        animator.addUpdateListener { animation ->
            val currentIndex = animation.animatedValue as Int
            ruletaAccionImageView.setImageDrawable(accionesImagen[currentIndex % accionesImagen.size])
        }

        // Cuando la animación termine, establecer el texto final
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                playRuletaAccion(accion)
                // Cuando la animación termine, volver a la escala original
                val scaleDownAnimation = ScaleAnimation(
                    3f, 1f,
                    3f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                )
                scaleDownAnimation.duration = 1000
                ruletaAccionImageView.startAnimation(scaleDownAnimation)
            }
        })

        // Iniciar la animación
        animator.start()
        ruletaAccionImageView.startAnimation(scaleAnimation)
    }

    private fun playRuletaAccion(accion: String) {
        // Realizar la acción de la ruleta
        when (accion) {
            "0" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_0)
                nextMultiplicadorAccion = 0
            }
            "50" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_50)
                nextMultiplicadorAccion = 50
            }
            "100" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_100)
                nextMultiplicadorAccion = 100
            }
            "150" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_150)
                nextMultiplicadorAccion = 150
            }
            "bote" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_bote)
                nextMultiplicadorAccion = 0
                cash+=2000

            }
            "quebra" -> {
                ruletaAccionImageView.setImageResource(R.drawable.ruleta_quebra)
                nextMultiplicadorAccion = 0
                cash=0
            }
        }
    }

    private fun setVisibility(isRuletaTirada: Boolean) {
        // Cambiar la visibilidad de los elementos de la pantalla
        if (isRuletaTirada) {
            tirarRuletaButton.visibility = View.INVISIBLE
            ruletaAccionImageView.visibility = View.VISIBLE
            setLettersOnClickListeners()
        } else {
            tirarRuletaButton.visibility = View.VISIBLE
            ruletaAccionImageView.visibility = View.INVISIBLE
            hideLettersOnClickListeners()
        }
    }

    private fun setLettersOnClickListeners() {
        // Settear el onClickListener de las letras
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener {
                playLetter((letterTextView).text[0])
                // Esperar 1 segundo antes de comprobar si ha ganado para que termine la animación
                coroutineScope.launch {
                    delay(1)
                    if (isGameWon()) {
                        finalizarRuletaDaSorte()
                    }
                    letterTextView.visibility = View.GONE
                }
            }
        }
    }

    private fun hideLettersOnClickListeners() {
        // Ocultar el onClickListener de las letras
        for (letter in letters) {
            val letterTextView = findViewById<TextView>(resources.getIdentifier(letter, "id", packageName))
            letterTextView.setOnClickListener{}
        }
    }

    private fun finalizarRuletaDaSorte() {
        Intent(this, RuletaDaSorteResultsActivity::class.java).apply {
            putExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, cash)
            Log.d("RuletaDaSorteGameActivity", "$cash")
            startActivity(this)
            finish()
        }
    }
}