package com.galegando21.menu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.isLoggedIn
import com.galegando21.utils.login
import com.galegando21.utils.replaceSharedPreferencesWithSupabase
import com.galegando21.utils.sendResetPasswordEmail
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.signOut
import com.galegando21.utils.signUp
import com.galegando21.utils.storeSharedPreferencesInSupabase
import kotlinx.coroutines.launch

class SincronizarDatosActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button
    private lateinit var resetPasswordButton: Button
    private lateinit var signOutButton: Button
    private lateinit var gardarDatosButton: Button
    private lateinit var obterDatosButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sincronizar_datos)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpButton = findViewById(R.id.signUpButton)
        loginButton = findViewById(R.id.logInButton)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        signOutButton = findViewById(R.id.signOutButton)
        gardarDatosButton = findViewById(R.id.gardarDatosButton)
        obterDatosButton = findViewById(R.id.obterDatosButton)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        setBanner(this, R.string.sincronizar_datos)
        setOnBackPressed(this, AxustesActivity::class.java)

        checkIfLoggedIn()

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                signUp(this@SincronizarDatosActivity, email, password)
                checkIfLoggedIn()
            }
            hideKeyboard(this@SincronizarDatosActivity)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                login(this@SincronizarDatosActivity, email, password)
                checkIfLoggedIn()
            }
            hideKeyboard(this@SincronizarDatosActivity)
        }

        resetPasswordButton.setOnClickListener {
            if (emailEditText.text.isEmpty()) {
                Toast.makeText(this, "Debes introducir un correo electrónico.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val email = emailEditText.text.toString()
                lifecycleScope.launch {
                    sendResetPasswordEmail(this@SincronizarDatosActivity, email)
                }
                hideKeyboard(this@SincronizarDatosActivity)
            }
        }

        signOutButton.setOnClickListener {
            lifecycleScope.launch {
                signOut(this@SincronizarDatosActivity)
                checkIfLoggedIn()
            }
        }

        val sharedPreferencesUnlockedButtons = getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, MODE_PRIVATE)
        val unlockedButtons = sharedPreferencesUnlockedButtons.getInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 0)
        if (unlockedButtons < 21) {
            gardarDatosButton.setBackgroundColor(getColor(R.color.inactiveBlue))
            gardarDatosButton.setOnClickListener {
                Toast.makeText(this, "Debes desbloquear todos os xogos para poder gardar os datos.", Toast.LENGTH_SHORT).show()
            }
        } else {
            gardarDatosButton.setOnClickListener {
                AlertDialog.Builder(this@SincronizarDatosActivity)
                    .setTitle("Confirmación")
                    .setMessage("Estás seguro de que queres gardar os datos? Ten en conta que vas sobrescribir os datos existentes na túa conta en liña.")
                    .setPositiveButton("Sí") { _, _ ->
                        // Gardar datos en Supabase
                        lifecycleScope.launch {
                            storeSharedPreferencesInSupabase(this@SincronizarDatosActivity)
                        }
                    }
                    .setNegativeButton("Non", null)
                    .show()
            }
        }

        obterDatosButton.setOnClickListener {
            AlertDialog.Builder(this@SincronizarDatosActivity)
                .setTitle("Confirmación")
                .setMessage("Estás seguro de que queres obter os datos? Ten en conta que vas sobrescribir os datos existentes no teu dispositivo.")
                .setPositiveButton("Sí") { _, _ ->
                    // Obter datos de Supabase
                    lifecycleScope.launch {
                        replaceSharedPreferencesWithSupabase(this@SincronizarDatosActivity)
                    }
                }
                .setNegativeButton("Non", null)
                .show()
        }
    }

    private fun checkIfLoggedIn() {
        loadingProgressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            val isLoggedIn = isLoggedIn(this@SincronizarDatosActivity)
            if (isLoggedIn) {
                emailEditText.visibility = View.GONE
                passwordEditText.visibility = View.GONE
                signUpButton.visibility = View.GONE
                loginButton.visibility = View.GONE
                resetPasswordButton.visibility = View.GONE
                signOutButton.visibility = View.VISIBLE
                gardarDatosButton.visibility = View.VISIBLE
                obterDatosButton.visibility = View.VISIBLE
            } else {
                emailEditText.visibility = View.VISIBLE
                passwordEditText.visibility = View.VISIBLE
                signUpButton.visibility = View.VISIBLE
                loginButton.visibility = View.VISIBLE
                resetPasswordButton.visibility = View.VISIBLE
                signOutButton.visibility = View.GONE
                gardarDatosButton.visibility = View.GONE
                obterDatosButton.visibility = View.GONE
            }
            loadingProgressBar.visibility = View.GONE
        }
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        // Verifica si no hay vista enfocada, ya que en ese caso el teclado se ocultará
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}