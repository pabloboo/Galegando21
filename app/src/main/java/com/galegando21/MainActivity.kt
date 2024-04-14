package com.galegando21

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.databinding.ActivityMainBinding
import com.galegando21.day02Musica.MusicaActivity
import java.util.Calendar
import android.view.View
import com.galegando21.day01Pasagalego.PasagalegoInicioActivity
import com.galegando21.day03AtrapameSePodes.AtrapameSePodesInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonQuestionActivity
import com.galegando21.day05Aforcado.AforcadoInicioActivity
import com.galegando21.day06Conexions.ConexionsInicioActivity
import com.galegando21.day07verdadeOuMentira.VerdadeOuMentiraInicioActivity
import com.galegando21.day08Wordle.WordleGameActivity
import com.galegando21.day08Wordle.WordleInicioActivity
import com.galegando21.day09AdivinhaEscudo.AdivinhaEscudoInicioActivity
import com.galegando21.day09AdivinhaEscudo.AdivinhaEscudoQuestionActivity
import com.galegando21.day10AdivinhaAnoFoto.AdivinhaAnoFotoGameActivity
import com.galegando21.day10AdivinhaAnoFoto.AdivinhaAnoFotoInicioActivity
import com.galegando21.day11AgoraCaigo.AgoraCaigoQuestionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bannerFragment: BannerFragment
    private lateinit var day01Button: Button
    private lateinit var day02Button: Button
    private lateinit var day03Button: Button
    private lateinit var day04Button: Button
    private lateinit var day05Button: Button
    private lateinit var day06Button: Button
    private lateinit var day07Button: Button
    private lateinit var day08Button: Button
    private lateinit var day09Button: Button
    private lateinit var day10Button: Button
    private lateinit var day11Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("onboarding_completed", MODE_PRIVATE)
        val onboardingCompleted = sharedPreferences.getBoolean("is_onboarding_completed", false)
        if (!onboardingCompleted) {
            Intent(this@MainActivity, OnboardingActivity::class.java).also {
                startActivity(it)
            }
        }

        updateButtonState()

        day01Button = findViewById(R.id.btnDay1)
        day02Button = findViewById(R.id.btnDay2)
        day03Button = findViewById(R.id.btnDay3)
        day04Button = findViewById(R.id.btnDay4)
        day05Button = findViewById(R.id.btnDay5)
        day06Button = findViewById(R.id.btnDay6)
        day07Button = findViewById(R.id.btnDay7)
        day08Button = findViewById(R.id.btnDay8)
        day09Button = findViewById(R.id.btnDay9)
        day10Button = findViewById(R.id.btnDay10)
        day11Button = findViewById(R.id.btnDay11)

        // Asegurarse de que el fragmento esté agregado antes de llamar a setBannerText
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        // Llamar a setBannerText después de que el fragmento haya inflado su vista
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.app_name))
        }.commit()

        day01Button.setOnClickListener {
            Intent(this@MainActivity, PasagalegoInicioActivity::class.java). also {
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

        day03Button.setOnClickListener {
            Intent(this@MainActivity, AtrapameSePodesInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day04Button.setOnClickListener {
            Intent(this@MainActivity, AtrapaUnMillonInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day05Button.setOnClickListener {
            Intent(this@MainActivity, AforcadoInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day06Button.setOnClickListener {
            Intent(this@MainActivity, ConexionsInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day07Button.setOnClickListener {
            Intent(this@MainActivity, VerdadeOuMentiraInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day08Button.setOnClickListener {
            Intent(this@MainActivity, WordleInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day09Button.setOnClickListener {
            Intent(this@MainActivity, AdivinhaEscudoInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day10Button.setOnClickListener {
            Intent(this@MainActivity, AdivinhaAnoFotoInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        day11Button.setOnClickListener {
            Intent(this@MainActivity, AgoraCaigoQuestionActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        binding.fab.setOnClickListener { view ->
            unlockAllButtons() // Llama a la función para desbloquear todos los botones
            Snackbar.make(view, "Botóns desbloqueados", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    // Función para verificar y actualizar el estado de los botones
    private fun updateButtonState() {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        // Inicializar SharedPreferences para almacenar el estado de los botones desbloqueados
        val sharedPreferences = getSharedPreferences("BotonesDesbloqueados", Context.MODE_PRIVATE)
        // Obtener último día en el que se desbloqueó un botón
        val lastDayUnlocked = sharedPreferences.getInt("lastDayUnlocked", -1)

        if (currentDay != lastDayUnlocked) {
            // Se desbloquea un nuevo botón cada día
            val unlockedButtonCount = sharedPreferences.getInt("unlockedButtonCount", 0)
            if (unlockedButtonCount < 21) {
                sharedPreferences.edit().putInt("unlockedButtonCount", unlockedButtonCount + 1)
                sharedPreferences.edit().putInt("lastDayUnlocked", currentDay)
                sharedPreferences.edit().apply()
            }
        }

        // Configurar la visibilidad de los botones según su estado de desbloqueo
        val unlockedButtonCount = sharedPreferences.getInt("unlockedButtonCount", 0)
        for (i in 2..11) {
            Log.d("DAY", "btnDay$i")
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.visibility = if (i <= unlockedButtonCount) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun unlockAllButtons() {
        for (i in 2..11) {
            Log.d("DAY", "btnDay$i")
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.visibility = View.VISIBLE
        }
    }

}