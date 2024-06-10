package com.galegando21.day11AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapameSePodesConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak

class AtrapameSePodesResultActivity : AppCompatActivity() {
    private lateinit var stepsFragment: AtrapameSePodesStepsFragment
    private lateinit var atrapameSePodesResultTV: TextView
    private lateinit var atrapameSePodesFinishButton: Button

    private var score = 999999999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_result)

        atrapameSePodesResultTV = findViewById(R.id.atrapame_se_podes_result_tv)
        atrapameSePodesFinishButton = findViewById(R.id.atrapame_se_podes_finish_btn)

        setBanner(this, R.string.atrapame_se_podes)

        // Settear el steps fragment
        stepsFragment = supportFragmentManager.findFragmentById(R.id.atrapame_se_podes_level_fragment_container_result) as AtrapameSePodesStepsFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            stepsFragment.setStepsImage(5)
        }.commit()

        score = intent.getIntExtra(AtrapameSePodesConstants.SCORE, 999999999)
        atrapameSePodesResultTV.text = "Necesitaches un total de $score preguntas para subir 5 escalóns."

        atrapameSePodesFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAtrapameSePodesStatistics()

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
        Log.d("AtrapameSePodesResultActivity", "Preguntas necesarias: ${sharedPreferences.getInt("atrapame_se_podes_questions", 999999999)}")

        updateCurrentStreak(this)
    }
}