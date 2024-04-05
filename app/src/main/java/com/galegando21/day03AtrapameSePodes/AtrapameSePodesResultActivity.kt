package com.galegando21.day03AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapameSePodesConstants

class AtrapameSePodesResultActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var atrapameSePodesResultTV: TextView
    private lateinit var atrapameSePodesFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_result)

        atrapameSePodesResultTV = findViewById(R.id.atrapame_se_podes_result_tv)
        atrapameSePodesFinishButton = findViewById(R.id.atrapame_se_podes_finish_btn)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapame_se_podes))
        }.commit()

        val score = intent.getIntExtra(AtrapameSePodesConstants.SCORE, 0)
        atrapameSePodesResultTV.text = "Necesitaches un total de $score preguntas para subir 5 escalóns."

        atrapameSePodesFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapameSePodesResultActivity, AtrapameSePodesInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}