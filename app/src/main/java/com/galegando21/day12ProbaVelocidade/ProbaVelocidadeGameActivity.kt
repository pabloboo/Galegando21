package com.galegando21.day12ProbaVelocidade

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.RecognizerIntent
import android.text.InputFilter
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.galegando21.R
import com.galegando21.model.QuestionRuletaDaSorte
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.QuestionRuletaDaSorteConstants.MAX_CHARS_PER_LINE
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ProbaVelocidadeGameActivity : AppCompatActivity() {
    private lateinit var boardLayout: LinearLayout
    private lateinit var hintTv: TextView
    private lateinit var userAnswerTv: EditText
    private lateinit var checkButton: ImageButton

    private var correctAnswers = 0
    private var totalTime = 0
    private lateinit var questionList: List<QuestionRuletaDaSorte>
    private lateinit var currentQuestionProbaVelocidade: QuestionRuletaDaSorte

    private lateinit var probaVelocidadeTimerTv : TextView

    private var seconds = 0
    private var countDownTimer: CountDownTimer? = null
    private var letterViews: List<TextView> = listOf()
    private var revealedLetterIndices = mutableListOf<Int>()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private lateinit var micButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proba_velocidade_game)

        boardLayout = findViewById(R.id.proba_velocidade_board_ll)
        hintTv = findViewById(R.id.proba_velocidade_hint_tv)
        userAnswerTv = findViewById(R.id.proba_velocidade_answer_et)
        userAnswerTv.filters = arrayOf(InputFilter.AllCaps())
        checkButton = findViewById(R.id.check_btn_proba_velocidade)
        probaVelocidadeTimerTv = findViewById(R.id.proba_velocidade_timer_tv)
        micButton = findViewById(R.id.mic_btn_proba_velocidade)

        checkButton.setOnClickListener {
            checkButtonClickListener()
        }
        micButton.setOnClickListener {
            promptSpeechInput()
        }

        questionList = QuestionRuletaDaSorteConstants.getQuestions()

        showNextQuestion()
        startTimer()

        setBanner(this, R.string.proba_velocidade)

    }

    private fun startTimer() {
        countDownTimer?.cancel() // Cancela el timer anterior si existe

        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                seconds++
                probaVelocidadeTimerTv.text = "$seconds:00"
                if (seconds % 2 == 0) {
                    coroutineScope.launch {
                        revealNextLetter()
                    }
                }
            }

            override fun onFinish() {}
        }.start()
        setOnBackPressed(this, ProbaVelocidadeInicioActivity::class.java, countDownTimer)
    }

    private fun showNextQuestion() {
        if (questionList.isEmpty() || correctAnswers == QuestionRuletaDaSorteConstants.PROBA_VELOCIDADE_QUESTIONS_NUMBER) {
            Intent(this, ProbaVelocidadeResultsActivity::class.java).apply {
                putExtra(QuestionRuletaDaSorteConstants.SCORE_PROBA_VELOCIDADE, totalTime)
                Log.d("ProbaVelocidadeGameActivity", "Total time: $totalTime")
                startActivity(this)
                finish()
            }
        }

        currentQuestionProbaVelocidade = questionList.random()
        questionList = questionList - currentQuestionProbaVelocidade

        boardLayout.removeAllViews()
        var lineLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            boardLayout.addView(this)
        }
        letterViews = currentQuestionProbaVelocidade.board.mapIndexed { index, char ->
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
                    setBackgroundColor(ContextCompat.getColor(this@ProbaVelocidadeGameActivity, R.color.canela))
                    setTextColor(ContextCompat.getColor(this@ProbaVelocidadeGameActivity, R.color.canela))
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

        hintTv.text = currentQuestionProbaVelocidade.hint
        userAnswerTv.text.clear()
        revealedLetterIndices.clear()

        countDownTimer?.cancel()
        seconds=0
        startTimer()
    }

    private suspend fun revealNextLetter() {
        var nextLetterIndex: Int
        if (revealedLetterIndices.size == currentQuestionProbaVelocidade.board.length) return //si ya se han revelado todas las letras
        do {
            nextLetterIndex = Random.nextInt(0, currentQuestionProbaVelocidade.board.length)
        } while (nextLetterIndex in revealedLetterIndices)

        revealedLetterIndices.add(nextLetterIndex)
        withContext(Dispatchers.Main) {
            letterViews[nextLetterIndex].setTextColor(ContextCompat.getColor(this@ProbaVelocidadeGameActivity, R.color.black))
        }
    }

    private fun checkButtonClickListener() {
        if (userAnswerTv.text.toString() == currentQuestionProbaVelocidade.board) {
            correctAnswers++
            Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show()
            totalTime+=seconds
            showNextQuestion()
        } else {
            Toast.makeText(this, "Segue intentandoo!", Toast.LENGTH_SHORT).show()
        }
    }

    //Grabación audio
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "gl-ES")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fala agora")

        try {
             getResult.launch(intent)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "O teu dispositivo non soporta entrada de voz", Toast.LENGTH_SHORT).show()
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && null != it.data) {
                val result = it.data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                userAnswerTv.setText(result?.get(0) ?: "")
            }
        }
}