package com.galegando21

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.databinding.ActivityMainBinding
import com.galegando21.day02Musica.MusicaActivity
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.galegando21.day01Pasagalego.PasagalegoInicioActivity
import com.galegando21.day03AtrapameSePodes.AtrapameSePodesInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonInicioActivity
import com.galegando21.day05Aforcado.AforcadoInicioActivity
import com.galegando21.day06Conexions.ConexionsInicioActivity
import com.galegando21.day07verdadeOuMentira.VerdadeOuMentiraInicioActivity
import com.galegando21.day08Wordle.WordleInicioActivity
import com.galegando21.day09AdivinhaEscudo.AdivinhaEscudoInicioActivity
import com.galegando21.day10AdivinhaAnoFoto.AdivinhaAnoFotoInicioActivity
import com.galegando21.day11AgoraCaigo.AgoraCaigoInicioActivity
import com.galegando21.day12ProbaVelocidade.ProbaVelocidadeInicioActivity
import com.galegando21.day13RuletaDaSorte.RuletaDaSorteGameActivity
import com.galegando21.onboarding.OnboardingActivity
import com.galegando21.utils.NUMBER_OF_DAYS
import com.galegando21.utils.setBanner
import com.galegando21.utils.showBannerMenu
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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
    private lateinit var day12Button: ImageButton
    private lateinit var day13Button: ImageButton

    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIsOnboardingCompleted()

        // Comprobar si ya se ha concedido el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Si no se ha concedido, solicitar el permiso
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), PERMISSION_REQUEST_CODE)
        }

        // Programar el worker para que se ejecute cada 24 horas
        val unlockButtonsWorkRequest = PeriodicWorkRequestBuilder<UnlockButtonsWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "UnlockButtonsWork",
            ExistingPeriodicWorkPolicy.KEEP, // Esta política determina qué hacer si ya existe un trabajo periódico con el mismo nombre. KEEP significa que se mantendrá el trabajo existente y se ignorará el nuevo trabajo.
            unlockButtonsWorkRequest
        )

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
        day12Button = findViewById(R.id.btnDay12)
        day13Button = findViewById(R.id.btnDay13)

        setBanner(this, R.string.app_name)

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

    override fun onResume() {
        super.onResume()
        updateButtonState()
        showBannerMenu(this)
    }

    // Función para verificar y actualizar el estado de los botones
    private fun updateButtonState() {
        // Configurar la visibilidad de los botones según su estado de desbloqueo
        val sharedPreferences = getSharedPreferences("BotonesDesbloqueados", Context.MODE_PRIVATE)
        val unlockedButtonCount = sharedPreferences.getInt("unlockedButtonCount", 0)
        for (i in 2..NUMBER_OF_DAYS) {
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
        for (i in 2..NUMBER_OF_DAYS) {
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
            R.id.btnDay12 -> {
                Intent(this@MainActivity, ProbaVelocidadeInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay13 -> {
                Intent(this@MainActivity, RuletaDaSorteGameActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    val hasUserDeniedPermissionBefore = sharedPreferences.getBoolean("hasUserDeniedPermissionBefore", false)
                    if (!hasUserDeniedPermissionBefore) {
                        Toast.makeText(this, "Sen este permiso non serás notificado dos novos contidos", Toast.LENGTH_SHORT).show()
                        editor.putBoolean("hasUserDeniedPermissionBefore", true)
                        editor.apply()
                    }
                }
                return
            }
            else -> {
            }
        }
    }

    private fun checkIsOnboardingCompleted() {
        val sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE)
        val isOnboardingCompleted = sharedPreferences.getBoolean("is_onboarding_completed", false)
        Log.d("MainActivity", "is_onboarding_completed: $isOnboardingCompleted")
        if (!isOnboardingCompleted) {
            Intent(this@MainActivity, OnboardingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}