package com.galegando21.day10ExplosionDePalabras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class ExplosionPalabrasResultsActivity : AppCompatActivity() {
    private lateinit var ExplosionPalabrasCorrectAnswersResultTv : TextView
    private lateinit var ExplosionPalabrasResultsTv : TextView
    private lateinit var ExplosionPalabrasRecordTv : TextView
    private lateinit var ExplosionPalabrasShareButton : Button
    private lateinit var ExplosionPalabrasFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosion_palabras_results)

        ExplosionPalabrasCorrectAnswersResultTv = findViewById(R.id.explosion_palabras_correct_answers_results_text_view)
        ExplosionPalabrasResultsTv = findViewById(R.id.explosion_palabras_result_tv)
        ExplosionPalabrasRecordTv = findViewById(R.id.explosion_palabras_record_tv)
        ExplosionPalabrasShareButton = findViewById(R.id.explosion_palabras_share_btn)
        ExplosionPalabrasFinishButton = findViewById(R.id.explosion_palabras_finish_btn)

        setBanner(this, R.string.explosion_de_palabras)

        val score = intent.getIntExtra("PUNTOS_EXPLOSION_PALABRAS", 0)
        ExplosionPalabrasCorrectAnswersResultTv.text = score.toString()
        ExplosionPalabrasResultsTv.text = "Conseguiches $score puntos."

        ExplosionPalabrasShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        ExplosionPalabrasFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeExplosionPalabrasStatistics()

        val dificultade = intent.getStringExtra("dificultade") ?: "facil"
        val maxScoreKey = when (dificultade) {
            "facil" -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY
            "dificil" -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT
            else -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY
        }
        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(maxScoreKey, 0)
        ExplosionPalabrasRecordTv.text = "O teu récord é de $record puntos."
        if (record < 100) {
            ExplosionPalabrasRecordTv.text = "O teu récord é de $record puntos.\n\nObtén 100 puntos para conseguir unha insignia!"
        }

        setOnBackPressed(this, ExplosionPalabrasInicioActivity::class.java)
    }

    private fun changeExplosionPalabrasStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dificultade = intent.getStringExtra("dificultade") ?: "facil"
        val maxScoreKey = when (dificultade) {
            "facil" -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY
            "dificil" -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT
            else -> SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY
        }

        var maxScore = 0
        if (sharedPreferences.contains(maxScoreKey)) {
            maxScore = sharedPreferences.getInt(maxScoreKey, 0)
        }

        val score = intent.getIntExtra("PUNTOS_EXPLOSION_PALABRAS", 0)
        if (score > maxScore) {
            editor.putInt(maxScoreKey, score)
            editor.apply()
        }
        Log.d("ExplosionPalabrasResultsActivity", "Puntuación máxima $maxScoreKey")

        updateCurrentStreak(this)

        var experience = 0
        when (dificultade) {
            "facil" -> experience = updateUserExperience(this, score.div(10))
            "dificil" -> experience = updateUserExperience(this, score)
        }
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}