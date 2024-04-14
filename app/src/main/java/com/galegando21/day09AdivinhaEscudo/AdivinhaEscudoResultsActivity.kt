package com.galegando21.day09AdivinhaEscudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AdivinhaEscudoConstants

class AdivinhaEscudoResultsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var AdivinhaEscudoCorrectAnswersResultTv : TextView
    private lateinit var AdivinhaEscudoResultsTv : TextView
    private lateinit var AdivinhaEscudoFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_escudo_results)

        AdivinhaEscudoCorrectAnswersResultTv = findViewById(R.id.adivinha_escudo_correct_answers_results_text_view)
        AdivinhaEscudoResultsTv = findViewById(R.id.adivinha_escudo_result_tv)
        AdivinhaEscudoFinishButton = findViewById(R.id.adivinha_escudo_finish_btn)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.adivinha_escudo))
        }.commit()

        val score = intent.getIntExtra(AdivinhaEscudoConstants.SCORE, 0)
        AdivinhaEscudoCorrectAnswersResultTv.text = score.toString()
        AdivinhaEscudoResultsTv.text = "Acertaches un total de $score preguntas."

        AdivinhaEscudoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AdivinhaEscudoResultsActivity, AdivinhaEscudoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        })
    }
}