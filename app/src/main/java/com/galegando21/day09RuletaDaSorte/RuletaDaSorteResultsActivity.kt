package com.galegando21.day09RuletaDaSorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class RuletaDaSorteResultsActivity : AppCompatActivity() {
    private lateinit var ruletaDaSorteCashResultTv : TextView
    private lateinit var ruletaDaSorteResultsTv : TextView
    private lateinit var ruletaDaSorteFinishButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_results)

        ruletaDaSorteCashResultTv = findViewById(R.id.ruleta_sorte_cash_results_text_view)
        ruletaDaSorteResultsTv = findViewById(R.id.ruleta_sorte_result_tv)
        ruletaDaSorteFinishButton = findViewById(R.id.ruleta_sorte_finish_btn)

        setBanner(this, R.string.ruleta_da_sorte)
        setOnBackPressed(this, RuletaDaSorteInicioActivity::class.java)

        val cash = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, 0)
        ruletaDaSorteCashResultTv.text = cash.toString()
        ruletaDaSorteResultsTv.text = "Gañaches un total de $cash€."

        ruletaDaSorteFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeRuletaDaSorteStatistics()
    }

    private fun changeRuletaDaSorteStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxCash = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH)) {
            maxCash = sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0)
        }

        val cash = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, 0)
        if (cash > maxCash) {
            editor.putInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, cash)
            editor.apply()
        }
        Log.d("RuletaDaSorteResultsActivity", "maxCash: ${sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, cash.div(100))
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}