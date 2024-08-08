package com.galegando21.menu

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.galegando21.R
import com.galegando21.utils.isLoggedIn
import com.galegando21.utils.login
import com.galegando21.utils.setBanner
import com.galegando21.utils.signOut
import com.galegando21.utils.signUp
import kotlinx.coroutines.launch

class SincronizarDatosActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button
    private lateinit var signOutButton: Button
    private lateinit var gardarDatosButton: Button
    private lateinit var obterDatosButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sincronizar_datos)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpButton = findViewById(R.id.signUpButton)
        loginButton = findViewById(R.id.logInButton)
        signOutButton = findViewById(R.id.signOutButton)
        gardarDatosButton = findViewById(R.id.gardarDatosButton)
        obterDatosButton = findViewById(R.id.obterDatosButton)

        setBanner(this, R.string.sincronizar_datos)

        checkIfLoggedIn()

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                signUp(this@SincronizarDatosActivity, email, password)
                checkIfLoggedIn()
            }
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                login(this@SincronizarDatosActivity, email, password)
                checkIfLoggedIn()
            }
        }

        signOutButton.setOnClickListener {
            lifecycleScope.launch {
                signOut(this@SincronizarDatosActivity)
                checkIfLoggedIn()
            }
        }
    }

    private fun checkIfLoggedIn() {
        lifecycleScope.launch {
            val isLoggedIn = isLoggedIn(this@SincronizarDatosActivity)
            if (isLoggedIn) {
                emailEditText.visibility = View.GONE
                passwordEditText.visibility = View.GONE
                signUpButton.visibility = View.GONE
                loginButton.visibility = View.GONE
                signOutButton.visibility = View.VISIBLE
                gardarDatosButton.visibility = View.VISIBLE
                obterDatosButton.visibility = View.VISIBLE
            } else {
                emailEditText.visibility = View.VISIBLE
                passwordEditText.visibility = View.VISIBLE
                signUpButton.visibility = View.VISIBLE
                loginButton.visibility = View.VISIBLE
                signOutButton.visibility = View.GONE
                gardarDatosButton.visibility = View.GONE
                obterDatosButton.visibility = View.GONE
            }
        }
    }
}