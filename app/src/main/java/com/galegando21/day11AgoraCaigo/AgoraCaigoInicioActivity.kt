package com.galegando21.day11AgoraCaigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.day10AdivinhaAnoFoto.AdivinhaAnoFotoGameActivity

class AgoraCaigoInicioActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_caigo_inicio)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.agora_caigo))
        }.commit()

        comezarButton = findViewById(R.id.start_btn_agora_caigo)

        comezarButton.setOnClickListener {
            Intent(this@AgoraCaigoInicioActivity, AgoraCaigoQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AgoraCaigoInicioActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}