package com.galegando21.day10AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaAnoFotoConstants

class AdivinhaAnoFotoResultsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var AdivinhaAnoFotoCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaAnoFotoResultsTv : TextView
    private lateinit var AdivinhaAnoFotoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_results)

        AdivinhaAnoFotoCorrectAnswersResultTv = findViewById(R.id.adivinhaAnoFoto_correct_answers_results_text_view)
        AdivinhaAnoFotoResultsTv = findViewById(R.id.adivinhaAnoFoto_result_tv)
        AdivinhaAnoFotoFinishButton = findViewById(R.id.adivinhaAnoFoto_finish_btn)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.adivinha_ano_foto))
        }.commit()

        val score = intent.getIntExtra(AdivinhaAnoFotoConstants.SCORE, 0)
        AdivinhaAnoFotoCorrectAnswersResultTv.text = score.toString()
        AdivinhaAnoFotoResultsTv.text = "Conseguiches un total de $score puntos."

        AdivinhaAnoFotoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AdivinhaAnoFotoResultsActivity, AdivinhaAnoFotoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        })
    }
}