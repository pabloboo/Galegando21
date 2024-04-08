package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.day03AtrapameSePodes.AtrapameSePodesQuestionActivity

class AtrapaUnMillonInicioActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var comezarButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_inicio)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.atrapa_un_millon))
        }.commit()

        comezarButton = findViewById(R.id.start_btn_atrapame_un_millon)

        comezarButton.setOnClickListener {
            Intent(this@AtrapaUnMillonInicioActivity, AtrapaUnMillonQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AtrapaUnMillonInicioActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })

    }
}