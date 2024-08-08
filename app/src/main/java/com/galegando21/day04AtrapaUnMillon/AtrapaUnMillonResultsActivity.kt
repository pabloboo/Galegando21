package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapaUnMillonConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.showSurvey
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class AtrapaUnMillonResultsActivity : AppCompatActivity() {
    private lateinit var atrapaUnMillonShortResult: TextView
    private lateinit var atrapaUnMillonResultsTv : TextView
    private lateinit var atrapaUnMillonRecordTv: TextView
    private lateinit var atrapaUnMillonShareButton: Button
    private lateinit var atrapaUnMillonFinishButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_results)

        atrapaUnMillonShortResult = findViewById(R.id.atrapa_un_millon_short_result_text_view)
        atrapaUnMillonResultsTv = findViewById(R.id.atrapa_un_millon_results_textView)
        atrapaUnMillonRecordTv = findViewById(R.id.atrapa_un_millon_record_textView)
        atrapaUnMillonShareButton = findViewById(R.id.atrapa_un_millon_share_btn)
        atrapaUnMillonFinishButton = findViewById(R.id.atrapa_un_millon_finish_btn)

        setBanner(this, R.string.atrapa_un_millon)
        showSurvey(this, SharedPreferencesKeys.ENQUISA_ATRAPA_UN_MILLON, findViewById(R.id.enquisaTextView))

        val cash = intent.getIntExtra(AtrapaUnMillonConstants.SCORE, 0).toString()
        atrapaUnMillonShortResult.text = "$cash€"
        if (cash.toInt() == 0) {
            atrapaUnMillonResultsTv.text = "Non gañaches nada. \n Mellor sorte a próxima vez!"
        } else {
            atrapaUnMillonResultsTv.text = "Parabéns! gañaches unha cantidade \n total de $cash€"
        }

        atrapaUnMillonShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        atrapaUnMillonFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeAtrapaUnMillonStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0)
        atrapaUnMillonRecordTv.text = "O teu récord é de $record€"
        if (record != 1000000) {
            atrapaUnMillonRecordTv.text = "O teu récord é de $record€.\n\nObtén 1.000.000€ para conseguir unha insignia!"
        }

        setOnBackPressed(this, AtrapaUnMillonInicioActivity::class.java)
    }

    private fun changeAtrapaUnMillonStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxCash = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH)) {
            maxCash = sharedPreferences.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0)
        }

        val cash = intent.getIntExtra(AtrapaUnMillonConstants.SCORE, 0)
        if (cash > maxCash) {
            editor.putInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, cash)
            editor.apply()
        }
        Log.d("AtrapaUnMillonResults", "Max cash: ${sharedPreferences.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, cash.div(10000))
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}