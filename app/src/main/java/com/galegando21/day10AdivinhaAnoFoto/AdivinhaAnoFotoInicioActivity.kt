package com.galegando21.day10AdivinhaAnoFoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.galegando21.BannerFragment
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.day09AdivinhaEscudo.AdivinhaEscudoQuestionActivity

class AdivinhaAnoFotoInicioActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_inicio)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.adivinha_ano_foto))
        }.commit()

        comezarButton = findViewById(R.id.start_btn_adivinhaAnoFoto)

        comezarButton.setOnClickListener {
            Intent(this@AdivinhaAnoFotoInicioActivity, AdivinhaAnoFotoGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@AdivinhaAnoFotoInicioActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }
}