package com.galegando21.day17ProbaVelocidade

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
import com.galegando21.utils.screenShot
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience

class ProbaVelocidadeResultsActivity : AppCompatActivity() {
    private lateinit var probaVelocidadeCorrectAnswersResultTv : TextView
    private lateinit var probaVelocidadeResultsTv : TextView
    private lateinit var probaVelocidadeRecordTv: TextView
    private lateinit var probaVelocidadeShareButton: TextView
    private lateinit var probaVelocidadeFinishButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proba_velocidade_results)

        probaVelocidadeCorrectAnswersResultTv = findViewById(R.id.proba_velocidade_correct_answers_results_text_view)
        probaVelocidadeResultsTv = findViewById(R.id.proba_velocidade_result_tv)
        probaVelocidadeRecordTv = findViewById(R.id.proba_velocidade_record_tv)
        probaVelocidadeShareButton = findViewById(R.id.proba_velocidade_share_btn)
        probaVelocidadeFinishButton = findViewById(R.id.proba_velocidade_finish_btn)

        setBanner(this, R.string.proba_velocidade)
        setOnBackPressed(this, ProbaVelocidadeInicioActivity::class.java)

        val score = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_PROBA_VELOCIDADE, 0)
        probaVelocidadeCorrectAnswersResultTv.text = score.toString()
        probaVelocidadeResultsTv.text = "Tardaches un total de $score segundos."

        probaVelocidadeShareButton.setOnClickListener {
            val bitmap = screenShot(window.decorView.rootView)
            shareScreenshot(bitmap, this)
        }

        probaVelocidadeFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        changeProbaVelocidadeStatistics()

        val record = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE).getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0)
        probaVelocidadeRecordTv.text = "O teu récord é de $record segundos."
        if (record > 40) {
            probaVelocidadeRecordTv.text = "O teu récord é de $record segundos.\n\nCompletao en menos de 40 segundos para conseguir unha insignia!"
        }
    }

    private fun changeProbaVelocidadeStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var minTime = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME)) {
            minTime = sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0)
        }

        val score = intent.getIntExtra(QuestionRuletaDaSorteConstants.SCORE_PROBA_VELOCIDADE, 0)
        if (minTime == 0 || score < minTime) {
            editor.putInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, score)
            editor.apply()
        }
        Log.d("ProbaVelocidadeResultsActivity", "minTime: ${sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0)}")

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, 30)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}