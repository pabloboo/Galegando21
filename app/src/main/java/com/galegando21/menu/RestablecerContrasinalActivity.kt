package com.galegando21.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.resetPassword
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.launch

class RestablecerContrasinalActivity : AppCompatActivity() {
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var resetPasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restablecer_contrasinal)

        newPasswordEditText = findViewById(R.id.newPassword)
        confirmPasswordEditText = findViewById(R.id.confirmPassword)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)

        setBanner(this, R.string.restablecer_contrasinal)
        setOnBackPressed(this, MainActivity::class.java)

        val email = intent.getStringExtra("email")
        val token = intent.getStringExtra("token")

        resetPasswordButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            if (newPassword == confirmPassword) {
                lifecycleScope.launch {
                    resetPassword(this@RestablecerContrasinalActivity, email!!, token!!, newPassword)
                }
            } else {
                Toast.makeText(this, "As contrasinais non coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}