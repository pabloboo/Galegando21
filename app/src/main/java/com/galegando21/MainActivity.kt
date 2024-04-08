package com.galegando21

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.databinding.ActivityMainBinding
import com.galegando21.day01Pasagalego.PasagalegoQuestionActivity
import com.galegando21.day02Musica.MusicaActivity
import java.util.Calendar
import android.view.View
import com.galegando21.day01Pasagalego.PasagalegoInicioActivity
import com.galegando21.day03AtrapameSePodes.AtrapameSePodesInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonInicioActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bannerFragment: BannerFragment
    private lateinit var day01Button: Button
    private lateinit var day02Button: Button
    private lateinit var day03Button: Button
    private lateinit var day04Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateButtonState()

        day01Button = findViewById(R.id.btnDay1)
        day02Button = findViewById(R.id.btnDay2)
        day03Button = findViewById(R.id.btnDay3)
        day04Button = findViewById(R.id.btnDay4)

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
        for (i in 1..4) {
            Log.d("DAY", "btnDay$i")
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.visibility = if (i <= unlockedButtonCount) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun unlockAllButtons() {
        for (i in 1..4) {
            Log.d("DAY", "btnDay$i")
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.visibility = View.VISIBLE
        }
    }

}