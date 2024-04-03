package com.galegando21.day01Pasagalego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.PasagalegoConstants

class PasagalegoResultActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var pasagalegoResult_tv : TextView
    private lateinit var pasagalegoFinishButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_result)

        pasagalegoResult_tv = findViewById(R.id.pasagalego_result_tv)
        pasagalegoFinishButton = findViewById(R.id.pasagalego_finish_btn)

        // Settear banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.pasagalego))
        }.commit()

        val score = intent.getIntExtra(PasagalegoConstants.SCORE, 0)
        pasagalegoResult_tv.text = "Acertaches un total de $score palabras"

        pasagalegoFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@PasagalegoResultActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })

    }
}