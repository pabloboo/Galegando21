package com.galegando21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AxustesActivity : AppCompatActivity() {
    private lateinit var nomeEditText: EditText
    private lateinit var gardarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_axustes)

        nomeEditText = findViewById(R.id.editTextName)
        gardarButton = findViewById(R.id.gardarButtonAxustes)

        setBanner(this, R.string.axustes)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
        val nome = sharedPreferences.getString(SharedPreferencesKeys.NOME, "")
        nomeEditText.setText(nome)

        gardarButton.setOnClickListener {
            val nome = nomeEditText.text.toString()
            if (nome.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean(SharedPreferencesKeys.IS_ONBOARDING_COMPLETED, true)
                    putString(SharedPreferencesKeys.NOME, nome)
                    commit()
                }
                Toast.makeText(this, "Nome gardado correctamente", Toast.LENGTH_SHORT).show()
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                Toast.makeText(this, "O nome non pode estar baleiro", Toast.LENGTH_SHORT).show()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}