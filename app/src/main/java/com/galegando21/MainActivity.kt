package com.galegando21

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.databinding.ActivityMainBinding
import com.galegando21.day01Pasagalego.PasagalegoQuestionActivity
import com.galegando21.day02Musica.MusicaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bannerFragment: BannerFragment
    private lateinit var day01Button: Button
    private lateinit var day02Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        day01Button = findViewById(R.id.day01Button)
        day02Button = findViewById(R.id.day02Button)

        // Asegurarse de que el fragmento esté agregado antes de llamar a setBannerText
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        // Llamar a setBannerText después de que el fragmento haya inflado su vista
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.app_name))
        }.commit()

        day01Button.setOnClickListener {
            Intent(this@MainActivity, PasagalegoQuestionActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day02Button.setOnClickListener {
            Intent(this@MainActivity, MusicaActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}