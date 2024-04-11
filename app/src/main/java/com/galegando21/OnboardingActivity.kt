package com.galegando21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.galegando21.day01Pasagalego.PasagalegoQuestionActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.app_name))
        }.commit()

        comezarButton = findViewById(R.id.start_btn_onboarding)

        comezarButton.setOnClickListener {
            Intent(this@OnboardingActivity, MainActivity::class.java).also {
                val sharedPreferences = getSharedPreferences("onboarding_completed", MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("is_onboarding_completed", true).apply()
                startActivity(it)
                finish()
            }
        }
    }
}