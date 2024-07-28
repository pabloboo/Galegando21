package com.galegando21.day20DebuxaEAdivinha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.galegando21.R
import com.galegando21.utils.DebuxaEAdivinhaConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetSequence
import kotlin.math.roundToInt

class DebuxaEAdivinhaGameActivity : AppCompatActivity() {
    private lateinit var clearButton: Button
    private lateinit var showWordButton: Button
    private lateinit var blackButton: ImageButton
    private lateinit var blueButton: ImageButton
    private lateinit var redButton: ImageButton
    private lateinit var greenButton: ImageButton
    private lateinit var yellowButton: ImageButton
    private lateinit var eraserButton: ImageButton
    private lateinit var grosorSlider: SeekBar
    private lateinit var palabraTv: TextView
    private lateinit var drawPath: DrawPath

    private lateinit var colorButtons : List<ImageButton>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debuxa_eadivinha_game)

        clearButton = findViewById(R.id.btnClearDebuxaEAdivinha)
        showWordButton = findViewById(R.id.btnMostrarPalabraDebuxaEAdivinha)

        blackButton = findViewById(R.id.imageCircleBlackDebuxaEAdivinha)
        blueButton = findViewById(R.id.imageCircleBlueDebuxaEAdivinha)
        redButton = findViewById(R.id.imageCircleRedDebuxaEAdivinha)
        greenButton = findViewById(R.id.imageCircleGreenDebuxaEAdivinha)
        yellowButton = findViewById(R.id.imageCircleYellowDebuxaEAdivinha)
        eraserButton = findViewById(R.id.imageEraserDebuxaEAdivinha)
        colorButtons = listOf(blackButton, blueButton, redButton, greenButton, yellowButton, eraserButton)

        grosorSlider = findViewById(R.id.grosorSliderDebuxaEAdivinha)

        palabraTv = findViewById(R.id.palabraDebuxaEAdivinhaTv)
        drawPath = findViewById(R.id.drawPathDebuxaEAdivinha)

        setBanner(this, R.string.debuxa_e_adivinha)
        setOnBackPressed(this, DebuxaEAdivinhaInicioActivity::class.java)

        clearButton.setOnClickListener {
            // Clear the drawing
            drawPath.clearCanvas()
        }

        val word = DebuxaEAdivinhaConstants.getWords().random()
        palabraTv.text = word

        showWordButton.setOnClickListener {
            showWord()
        }

        // Establecer los listeners de los botones
        blackButton.setOnClickListener { selectButton(blackButton) }
        redButton.setOnClickListener { selectButton(redButton) }
        blueButton.setOnClickListener { selectButton(blueButton) }
        greenButton.setOnClickListener { selectButton(greenButton) }
        yellowButton.setOnClickListener { selectButton(yellowButton) }
        eraserButton.setOnClickListener { selectButton(eraserButton) }

        // Por defecto color negro
        selectButton(blackButton)

        grosorSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                drawPath.setStrokeWidth(progress.toFloat())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.GAMES_STATE, MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean(SharedPreferencesKeys.DEBUXA_E_ADIVINHA_FIRST_TIME, true)

        if (isFirstRun) {
            MaterialTapTargetSequence()
                .addPrompt(
                    MaterialTapTargetPrompt.Builder(this)
                        .setTarget(R.id.btnMostrarPalabraDebuxaEAdivinha)
                        .setPrimaryText("Ocultar palabra")
                        .setSecondaryText("Preme este botón para ocultar ou mostrar a palabra que debes debuxar")
                )
                .addPrompt(
                    MaterialTapTargetPrompt.Builder(this)
                        .setTarget(R.id.horizontalScrollViewDebuxaEAdivinha)
                        .setPrimaryText("Elementos para debuxar")
                        .setSecondaryText("Desliza horizontalmente para ver todos os elementos que podes usar para debuxar")
                )
                .setSequenceCompleteListener {
                    sharedPreferences.edit().putBoolean(SharedPreferencesKeys.DEBUXA_E_ADIVINHA_FIRST_TIME, false).apply()
                }
                .show()
        }
    }

    private fun showWord() {
        if (palabraTv.visibility == View.VISIBLE) {
            palabraTv.visibility = View.GONE
            showWordButton.text = "Mostrar palabra"
        } else {
            palabraTv.visibility = View.VISIBLE
            showWordButton.text = "Ocultar palabra"
        }
    }

    // Método para restablecer el tamaño de todos los botones
    fun resetButtonSizes() {
        for (button in colorButtons) {
            val params = button.layoutParams as LinearLayout.LayoutParams
            params.width = 40.dpToPx()
            params.height = 40.dpToPx()
            button.layoutParams = params
        }
    }

    // Método para cambiar el color seleccionado
    fun selectButton(button: ImageButton) {
        resetButtonSizes()
        val params = button.layoutParams as LinearLayout.LayoutParams
        params.width = 50.dpToPx()
        params.height = 50.dpToPx()
        button.layoutParams = params

        when (button) {
            blackButton -> drawPath.setColor("#000000")
            blueButton -> drawPath.setColor("#ff0099cc")
            redButton -> drawPath.setColor("#ffcc0000")
            greenButton -> drawPath.setColor("#ff669900")
            yellowButton -> drawPath.setColor("#FFFF00")
            eraserButton -> drawPath.setColor("#FFFFFF") // Color blanco para borrar
        }
    }

    // Convertir dp a px
    fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).roundToInt()
    }
}