package com.galegando21

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.databinding.ActivityMainBinding
import com.galegando21.day21RecursosGalego.RecursosGalegoActivity
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arezoo.fliptimerview.FlipTimerDigitView
import com.arezoo.fliptimerview.FlipTimerView
import com.galegando21.day01Pasagalego.PasagalegoInicioActivity
import com.galegando21.day11AtrapameSePodes.AtrapameSePodesInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonInicioActivity
import com.galegando21.day19Aforcado.AforcadoInicioActivity
import com.galegando21.day08Conexions.ConexionsInicioActivity
import com.galegando21.day13VerdadeOuMentira.VerdadeOuMentiraInicioActivity
import com.galegando21.day15Wordle.WordleInicioActivity
import com.galegando21.day14AdivinhaEscudo.AdivinhaEscudoInicioActivity
import com.galegando21.day05AdivinhaAnoFoto.AdivinhaAnoFotoInicioActivity
import com.galegando21.day07AgoraCaigo.AgoraCaigoInicioActivity
import com.galegando21.day17ProbaVelocidade.ProbaVelocidadeInicioActivity
import com.galegando21.day09RuletaDaSorte.RuletaDaSorteInicioActivity
import com.galegando21.day20DebuxaEAdivinha.DebuxaEAdivinhaInicioActivity
import com.galegando21.day02OndeEstan.OndeEstanInicioActivity
import com.galegando21.day06SopaLetras.SopaLetrasInicioActivity
import com.galegando21.day16Anagramas.AnagramasInicioActivity
import com.galegando21.day12AdivinhaPersonaxe.AdivinhaPersonaxeInicioActivity
import com.galegando21.day03XogoPalabras.XogoPalabrasInicioActivity
import com.galegando21.day10ExplosionDePalabras.ExplosionPalabrasInicioActivity
import com.galegando21.day18PalabrasEncadeadas.PalabrasEncadeadasInicioActivity
import com.galegando21.onboarding.OnboardingActivity
import com.galegando21.utils.ENVIRONMENT
import com.galegando21.utils.NUMBER_OF_DAYS
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.showBannerMenu
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var goldenHourTextView: TextView
    private lateinit var flipTimerView: FlipTimerView
    private lateinit var helpButtonTimeLeft: ImageButton
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
    private lateinit var day14Button: ImageButton
    private lateinit var day15Button: ImageButton
    private lateinit var day16Button: ImageButton
    private lateinit var day17Button: ImageButton
    private lateinit var day18Button: ImageButton
    private lateinit var day19Button: ImageButton
    private lateinit var day20Button: ImageButton
    private lateinit var day21Button: ImageButton

    private val PERMISSION_REQUEST_CODE = 1
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Galegando21)
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
        val unlockButtonsWorkRequest = PeriodicWorkRequestBuilder<UnlockButtonsWorker>(24, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "UnlockButtonsWork",
            ExistingPeriodicWorkPolicy.KEEP, // Esta política determina qué hacer si ya existe un trabajo periódico con el mismo nombre. KEEP significa que se mantendrá el trabajo existente y se ignorará el nuevo trabajo.
            unlockButtonsWorkRequest
        )
        // guardar la hora de la primera vez que se ejecuta el worker
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val firstUnlockTime = sharedPreferences.getLong(SharedPreferencesKeys.NEXT_UNLOCK_TIME, 0)
        if (firstUnlockTime == 0L) {
            editor.putLong(SharedPreferencesKeys.NEXT_UNLOCK_TIME, System.currentTimeMillis()+TimeUnit.HOURS.toMillis(24))
            editor.apply()
        }

        goldenHourTextView = findViewById(R.id.goldenHourTextView)
        flipTimerView = findViewById(R.id.flipTimerView)
        helpButtonTimeLeft = findViewById(R.id.helpButtonTimeLeft)
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
        day14Button = findViewById(R.id.btnDay14)
        day15Button = findViewById(R.id.btnDay15)
        day16Button = findViewById(R.id.btnDay16)
        day17Button = findViewById(R.id.btnDay17)
        day18Button = findViewById(R.id.btnDay18)
        day19Button = findViewById(R.id.btnDay19)
        day20Button = findViewById(R.id.btnDay20)
        day21Button = findViewById(R.id.btnDay21)

        setBanner(this, R.string.app_name)

        setGoldenHourTextView()

        setTimeLeftTextView()

        helpButtonTimeLeft.setOnClickListener {
            showHelpTimeLeftDialog()
        }

        day01Button.setOnClickListener {
            Intent(this@MainActivity, PasagalegoInicioActivity::class.java). also {
                startActivity(it)
                finish()
            }
        }

        buttonsInitialState()

        if (ENVIRONMENT == "production") {
            binding.fab.visibility = View.GONE
            binding.fab.isEnabled = false
        } else {
            binding.fab.visibility = View.VISIBLE
            binding.fab.isEnabled = true
            binding.fab.setOnClickListener { view ->
                unlockAllButtons() // Llama a la función para desbloquear todos los botones
                Snackbar.make(view, "Botóns desbloqueados", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    finishAffinity()
                    return
                }

                backPressedOnce = true
                Toast.makeText(this@MainActivity, "Presiona de novo para sair", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    backPressedOnce = false
                }, 2000)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        updateButtonState()
        showBannerMenu(this)
    }

    private fun setGoldenHourTextView() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        if (currentHour in 19..20) {
            goldenHourTextView.visibility = View.VISIBLE
        }
    }

    private fun setTimeLeftTextView() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, MODE_PRIVATE)

        // Si ya se han desbloqueado todos los botones, no se muestra el temporizador
        val unlockedButtonCount = sharedPreferences.getInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 0)
        if (unlockedButtonCount == NUMBER_OF_DAYS) {
            flipTimerView.visibility = View.GONE
            helpButtonTimeLeft.visibility = View.GONE
            return
        }

        val nextUnlockTime = sharedPreferences.getLong(SharedPreferencesKeys.NEXT_UNLOCK_TIME, 0)
        val currentTime = System.currentTimeMillis()
        val timeLeft = nextUnlockTime - currentTime

        flipTimerView.findViewById<FlipTimerDigitView>(com.arezoo.fliptimerview.R.id.digitDays).visibility = View.GONE
        flipTimerView.findViewById<AppCompatTextView>(com.arezoo.fliptimerview.R.id.colonDivider1).visibility = View.GONE
        flipTimerView.findViewById<TextView>(com.arezoo.fliptimerview.R.id.dayTextView).visibility = View.GONE
        flipTimerView.findViewById<TextView>(com.arezoo.fliptimerview.R.id.hourTextView).visibility = View.GONE
        flipTimerView.findViewById<TextView>(com.arezoo.fliptimerview.R.id.minuteTextView).visibility = View.GONE
        flipTimerView.findViewById<TextView>(com.arezoo.fliptimerview.R.id.secondTextView).visibility = View.GONE
        flipTimerView.startCountDown(timeLeft)
    }

    private fun showHelpTimeLeftDialog() {
        AlertDialog.Builder(this)
            .setTitle("Tempo restante")
            .setMessage("Horas restantes para desbloquear un novo xogo.\n\nDesbloquease novo contido cada 24 horas.")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun buttonsInitialState() {
        for (i in 2..NUMBER_OF_DAYS) {
            val buttonId = resources.getIdentifier("btnDay$i", "id", packageName)
            val button = findViewById<ImageButton>(buttonId)
            button.setOnClickListener {
                Toast.makeText(this, "Aínda non desbloqueaches este xogo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para verificar y actualizar el estado de los botones
    private fun updateButtonState() {
        // Configurar la visibilidad de los botones según su estado de desbloqueo
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, Context.MODE_PRIVATE)
        val unlockedButtonCount = sharedPreferences.getInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 0)
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
                Intent(this@MainActivity, OndeEstanInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay3 -> {
                Intent(this@MainActivity, XogoPalabrasInicioActivity::class.java).also {
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
                Intent(this@MainActivity, AdivinhaAnoFotoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay6 -> {
                Intent(this@MainActivity, SopaLetrasInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay7 -> {
                Intent(this@MainActivity, AgoraCaigoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay8 -> {
                Intent(this@MainActivity, ConexionsInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay9 -> {
                Intent(this@MainActivity, RuletaDaSorteInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay10 -> {
                Intent(this@MainActivity, ExplosionPalabrasInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay11 -> {
                Intent(this@MainActivity, AtrapameSePodesInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay12 -> {
                Intent(this@MainActivity, AdivinhaPersonaxeInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay13 -> {
                Intent(this@MainActivity, VerdadeOuMentiraInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay14 -> {
                Intent(this@MainActivity, AdivinhaEscudoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay15 -> {
                Intent(this@MainActivity, WordleInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay16 -> {
                Intent(this@MainActivity, AnagramasInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay17 -> {
                Intent(this@MainActivity, ProbaVelocidadeInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay18 -> {
                Intent(this@MainActivity, PalabrasEncadeadasInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay19 -> {
                Intent(this@MainActivity, AforcadoInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay20 -> {
                Intent(this@MainActivity, DebuxaEAdivinhaInicioActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            R.id.btnDay21 -> {
                Intent(this@MainActivity, RecursosGalegoActivity::class.java).also {
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
                    val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.APP_PREFERENCES, MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    val hasUserDeniedPermissionBefore = sharedPreferences.getBoolean(SharedPreferencesKeys.HAS_USER_DENIED_PERMISSIONS_BEFORE, false)
                    if (!hasUserDeniedPermissionBefore) {
                        Toast.makeText(this, "Sen este permiso non serás notificado dos novos contidos", Toast.LENGTH_SHORT).show()
                        editor.putBoolean(SharedPreferencesKeys.HAS_USER_DENIED_PERMISSIONS_BEFORE, true)
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
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
        val isOnboardingCompleted = sharedPreferences.getBoolean(SharedPreferencesKeys.IS_ONBOARDING_COMPLETED, false)
        Log.d("MainActivity", "is_onboarding_completed: $isOnboardingCompleted")
        if (!isOnboardingCompleted) {
            Intent(this@MainActivity, OnboardingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}