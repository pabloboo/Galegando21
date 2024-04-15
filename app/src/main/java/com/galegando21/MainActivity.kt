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
import android.widget.ImageButton
import android.widget.TextView
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
import com.galegando21.day11AgoraCaigo.AgoraCaigoInicioActivity
import com.galegando21.day11AgoraCaigo.AgoraCaigoQuestionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bannerFragment: BannerFragment
    private lateinit var day01Button: ImageButton
    private lateinit var day02Button: ImageButton
    private lateinit var day03Button: ImageButton
    private lateinit var day04Button: ImageButton
    private lateinit var day05Button: ImageButton
    private lateinit var day06Button: ImageButton
    private lateinit var day07Button: ImageButton
    private lateinit var day08Button: ImageButton
    private lateinit var day09Button: ImageButton
    private lateinit var day10Button: ImageButton
    private lateinit var day11Button: ImageButton

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
            val button = findViewById<ImageButton>(buttonId)
            if (i <= unlockedButtonCount) {
                button.setOnClickListener {
                    setOnClickListenerMain(buttonId)
                }
                button.background = getDrawable(R.drawable.main_activity_button_background)
            }
        }
    }

    private fun unlockAllButtons() {
        for (i in 2..11) {
            Log.d("DAY", "btnDay$i")
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<ImageButton>(buttonId)
            button.setOnClickListener {
                setOnClickListenerMain(buttonId)
            }
            button.background = getDrawable(R.drawable.main_activity_button_background)
        }
    }

    private fun setOnClickListenerMain(id: Int) {
        when (id) {
            R.id.btnDay2 -> {
                Intent(this@MainActivity, MusicaActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay3 -> {
                Intent(this@MainActivity, AtrapameSePodesInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay4 -> {
                Intent(this@MainActivity, AtrapaUnMillonInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay5 -> {
                Intent(this@MainActivity, AforcadoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay6 -> {
                Intent(this@MainActivity, ConexionsInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay7 -> {
                Intent(this@MainActivity, VerdadeOuMentiraInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay8 -> {
                Intent(this@MainActivity, WordleInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay9 -> {
                Intent(this@MainActivity, AdivinhaEscudoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay10 -> {
                Intent(this@MainActivity, AdivinhaAnoFotoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay11 -> {
                Intent(this@MainActivity, AgoraCaigoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

}