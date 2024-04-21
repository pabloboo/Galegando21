package com.galegando21.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.galegando21.R
import com.galegando21.utils.setBanner

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setBanner(this, R.string.app_name)
    }
}