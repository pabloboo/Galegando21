package com.galegando21.day09RuletaDaSorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.QuestionRuletaDaSorteConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.showSurvey
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class RuletaDaSorteResultsActivity : AppCompatActivity() {
    private lateinit var ruletaDaSorteCashResultTv : TextView
    private lateinit var ruletaDaSorteResultsTv : TextView
    private lateinit var ruletaDaSorteRecordTv : TextView
    private lateinit var ruletaDaSorteShareButton : Button
    private lateinit var ruletaDaSorteFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_results)

        ruletaDaSorteCashResultTv = findViewById(R.id.ruleta_sorte_cash_results_text_view)
        ruletaDaSorteResultsTv = findViewById(R.id.ruleta_sorte_result_tv)
        ruletaDaSorteRecordTv = findViewById(R.id.ruleta_sorte_record_tv)
        ruletaDaSorteShareButton = findViewById(R.id.ruleta_sorte_share_btn)
        ruletaDaSorteFinishButton = findViewById(R.id.ruleta_sorte_finish_btn)

        setBanner(this, R.string.ruleta_da_sorte)
        showSurvey(this, SharedPreferencesKeys.ENQUISA_RULETA_DA_SORTE, findViewById(R.id.enquisaTextView))
        setOnBackPressed(this, RuletaDaSorteInicioActivity::class.java)

        val cash = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_RULETA_DA_SORTE, 0)
        ruletaDaSorteCashResultTv.text = cash.toString()
        ruletaDaSorteResultsTv.text = "Gañaches un total de $cash€."

        ruletaDaSorteShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        ruletaDaSorteFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeRuletaDaSorteStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0)
        ruletaDaSorteRecordTv.text = "O teu récord é de $record€"
        if (record < 4000) {
            ruletaDaSorteRecordTv.text = "O teu récord é de $record€.\n\nObtén 4000€ para conseguir unha insignia!"
        }
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