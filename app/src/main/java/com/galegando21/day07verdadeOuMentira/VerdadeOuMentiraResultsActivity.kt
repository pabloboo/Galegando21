package com.galegando21.day07verdadeOuMentira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.VerdadeOuMentiraConstants

class VerdadeOuMentiraResultsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var verdadeOuMentiraCorrectAnswersResultTv : TextView
    private lateinit var verdadeOuMentiraResultsTv : TextView
    private lateinit var verdadeOuMentiraFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdade_ou_mentira_results)

        verdadeOuMentiraCorrectAnswersResultTv = findViewById(R.id.verdade_ou_mentira_correct_answers_results_text_view)
        verdadeOuMentiraResultsTv = findViewById(R.id.verdade_ou_mentira_result_tv)
        verdadeOuMentiraFinishButton = findViewById(R.id.verdade_ou_mentira_finish_btn)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.verdadeOuMentira))
        }.commit()

        val score = intent.getIntExtra(VerdadeOuMentiraConstants.SCORE, 0)
        verdadeOuMentiraCorrectAnswersResultTv.text = score.toString()
        verdadeOuMentiraResultsTv.text = "Acertaches un total de $score preguntas seguidas."

        verdadeOuMentiraFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@VerdadeOuMentiraResultsActivity, VerdadeOuMentiraInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}