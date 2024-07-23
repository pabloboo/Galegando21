package com.galegando21.day11AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapameSePodesConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AtrapameSePodesResultActivity : AppCompatActivity() {
    private lateinit var atrapameSePodesShortResultTv: TextView
    private lateinit var atrapameSePodesResultTV: TextView
    private lateinit var atrapameSePodesRecordTv: TextView
    private lateinit var atrapameSePodesShareButton: Button
    private lateinit var atrapameSePodesFinishButton: Button

    private var score = 999999999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_result)

        atrapameSePodesShortResultTv = findViewById(R.id.atrapame_se_podes_short_results_text_view)
        atrapameSePodesResultTV = findViewById(R.id.atrapame_se_podes_result_tv)
        atrapameSePodesRecordTv = findViewById(R.id.atrapame_se_podes_record_tv)
        atrapameSePodesShareButton = findViewById(R.id.atrapame_se_podes_share_btn)
        atrapameSePodesFinishButton = findViewById(R.id.atrapame_se_podes_finish_btn)

        setBanner(this, R.string.atrapame_se_podes)

        score = intent.getIntExtra(AtrapameSePodesConstants.SCORE, 999999999)
        atrapameSePodesShortResultTv.text = "$score"
        atrapameSePodesResultTV.text = "Necesitaches un total de $score preguntas para subir 5 escalóns."

        atrapameSePodesShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        atrapameSePodesFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAtrapameSePodesStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0)
        atrapameSePodesRecordTv.text = "No teu récord necesitaches $record preguntas para subir 5 escalóns."
        if (record < 5) {
            atrapameSePodesRecordTv.text = "No teu récord necesitaches $record preguntas para subir 5 escalóns.\n\nNon falles ningunha pregunta para conseguir unha insignia!"
        }

        setOnBackPressed(this, AtrapameSePodesInicioActivity::class.java)
    }

    private fun changeAtrapameSePodesStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var minQuestionsNeeded = 999999999
        if (sharedPreferences.contains(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED)) {
            minQuestionsNeeded = sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 999999999)
        }

        var currentQuestionsNeeded = score
        if (currentQuestionsNeeded < minQuestionsNeeded) {
            editor.putInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, currentQuestionsNeeded)
            editor.apply()
        }
        Log.d("AtrapameSePodesResultActivity", "Preguntas necesarias: ${sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 999999999)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, 30)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}