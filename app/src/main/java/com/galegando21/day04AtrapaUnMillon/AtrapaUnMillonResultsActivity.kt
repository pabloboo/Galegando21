package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapaUnMillonConstants

class AtrapaUnMillonResultsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var atrapaUnMillonResultsTv : TextView
    private lateinit var atrapaUnMillonFinishButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_results)

        atrapaUnMillonResultsTv = findViewById(R.id.atrapa_un_millon_results_textView)
        atrapaUnMillonFinishButton = findViewById(R.id.atrapa_un_millon_finish_btn)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapa_un_millon))
        }.commit()

        val cash = intent.getIntExtra(AtrapaUnMillonConstants.SCORE, 0).toString()
        if (cash.toInt() == 0) {
            atrapaUnMillonResultsTv.text = "Non gañaches nada. \n Mellor sorte a próxima vez!"
        } else {
            atrapaUnMillonResultsTv.text = "Parabéns! gañaches unha cantidade \n total de $cash€"
        }

        atrapaUnMillonFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapaUnMillonResultsActivity, AtrapaUnMillonInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}