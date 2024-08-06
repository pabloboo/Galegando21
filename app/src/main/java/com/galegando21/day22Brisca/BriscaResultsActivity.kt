package com.galegando21.day22Brisca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot

class BriscaResultsActivity : AppCompatActivity() {
    private lateinit var BriscaCorrectAnswersResultTv : TextView
    private lateinit var BriscaResultsTv : TextView
    private lateinit var BriscaRecordTv : TextView
    private lateinit var BriscaShareButton : Button
    private lateinit var BriscaFinishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brisca_results)

        BriscaCorrectAnswersResultTv = findViewById(R.id.brisca_correct_answers_results_text_view)
        BriscaResultsTv = findViewById(R.id.brisca_result_tv)
        BriscaRecordTv = findViewById(R.id.brisca_record_tv)
        BriscaShareButton = findViewById(R.id.brisca_share_btn)
        BriscaFinishButton = findViewById(R.id.brisca_finish_btn)

        setBanner(this, R.string.brisca)

        val playerScore = intent.getIntExtra("player_points_brisca", 0)
        val computerScore = intent.getIntExtra("computer_points_brisca", 0)
        BriscaCorrectAnswersResultTv.text = playerScore.toString()
        if (playerScore > computerScore) {
            BriscaResultsTv.text = "Gañaches $playerScore a $computerScore."
        } else if (playerScore < computerScore) {
            BriscaResultsTv.text = "Perdiches $playerScore a $computerScore."
        } else {
            BriscaResultsTv.text = "Empataches $playerScore a $computerScore."
        }

        BriscaShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        BriscaFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeBriscaStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.BRISCA_MAX_SCORE, 0)
        BriscaRecordTv.text = "O teu récord é de $record puntos."
        if (record < 10) {
            BriscaRecordTv.text = "O teu récord é de $record puntos.\n\nObtén 120 puntos para conseguir unha insignia!"
        }

        setOnBackPressed(this, BriscaInicioActivity::class.java)
    }

    private fun changeBriscaStatistics() {
        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.BRISCA_MAX_SCORE, 0)
        val playerScore = intent.getIntExtra("player_points_brisca", 0)
        if (playerScore > record) {
            getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).edit().putInt(SharedPreferencesKeys.BRISCA_MAX_SCORE, playerScore).apply()
        }
    }
}