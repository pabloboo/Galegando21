package com.galegando21.day06SopaLetras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.SopaLetrasConstants
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class SopaLetrasResultsActivity : AppCompatActivity() {
    private lateinit var SopaLetrasCorrectAnswersResultTv : TextView
    private lateinit var SopaLetrasResultsTv : TextView
    private lateinit var SopaLetrasRecordTv : TextView
    private lateinit var helpImageButton: ImageButton
    private lateinit var SopaLetrasShareButton: Button
    private lateinit var SopaLetrasFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopa_letras_results)

        SopaLetrasCorrectAnswersResultTv = findViewById(R.id.sopa_letras_correct_answers_results_text_view)
        SopaLetrasResultsTv = findViewById(R.id.sopa_letras_result_tv)
        SopaLetrasRecordTv = findViewById(R.id.sopa_letras_record_tv)
        helpImageButton = findViewById(R.id.helpButtonSopaLetrasResults)
        SopaLetrasShareButton = findViewById(R.id.sopa_letras_share_btn)
        SopaLetrasFinishButton = findViewById(R.id.sopa_letras_finish_btn)

        setBanner(this, R.string.sopa_de_letras)

        val score = intent.getIntExtra(SopaLetrasConstants.SCORE, 0)
        val tableirosAcertados = intent.getIntExtra(SopaLetrasConstants.TABLEIROS_ACERTADOS, 0)
        SopaLetrasCorrectAnswersResultTv.text = score.toString()
        SopaLetrasResultsTv.text = "Conseguiches un total de $score puntos, adiviñando $tableirosAcertados tableiros."

        helpImageButton.setOnClickListener {
            showHelpDialog()
        }

        SopaLetrasShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        SopaLetrasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeSopaLetrasStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0)
        SopaLetrasRecordTv.text = "O teu récord é de $record puntos"
        if (record < 500) {
            SopaLetrasRecordTv.text = "O teu récord é de $record puntos.\n\nObtén 500 puntos para conseguir unha insignia!"
        }

        setOnBackPressed(this, SopaLetrasInicioActivity::class.java)
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Como se puntúa?")
            .setMessage("Nivel fácil: 10 puntos por tableiro\nNivel medio: tempo restante de cada taboleiro * 2\nNivel difícil: tempo restante de cada taboleiro * 4\n\nPodes atopar a túa puntuación máxima na sección de estadísticas.")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun changeSopaLetrasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxScore = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE)) {
            maxScore = sharedPreferences.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0)
        }

        val score = intent.getIntExtra(SopaLetrasConstants.SCORE, 0)
        if (score > maxScore) {
            editor.putInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, score)
            editor.apply()
        }
        Log.d("SopaLetrasResultsActivity", "maxScore: ${sharedPreferences.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, score.div(10))
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}