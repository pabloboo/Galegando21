package com.galegando21.day01Pasagalego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.galegando21.BannerFragment
import com.galegando21.R

class PasagalegoQuestionActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var letter_tv : TextView
    private lateinit var question_tv : TextView
    private lateinit var userAnswerText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_question)

        letter_tv = findViewById(R.id.tv_pasagalego_letter)
        question_tv = findViewById(R.id.tv_pasagalego_question)

        // Settear banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.pasagalego))
        }.commit()

        letter_tv.text = "Comeza pola letra 'A'"
        question_tv.text = "Separación das partes dun todo para\n" +
                "                coñecer os elementos que o constitúen."
    }
}