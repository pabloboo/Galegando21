package com.galegando21.day03AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R

class AtrapameSePodesInicioActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_inicio)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapame_se_podes))
        }.commit()

        comezarButton = findViewById(R.id.start_btn_atrapame_se_podes)

        comezarButton.setOnClickListener {
            Intent(this@AtrapameSePodesInicioActivity, AtrapameSePodesQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapameSePodesInicioActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}