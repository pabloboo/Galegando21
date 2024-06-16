package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapaUnMillonInicioActivity : AppCompatActivity() {
    private lateinit var helpButton: ImageButton
    private lateinit var comezarButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_inicio)

        setBanner(this, R.string.atrapa_un_millon)

        comezarButton = findViewById(R.id.start_btn_atrapame_un_millon)
        helpButton = findViewById(R.id.atrapa_un_millon_help_btn)

        comezarButton.setOnClickListener {
            Intent(this@AtrapaUnMillonInicioActivity, AtrapaUnMillonQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        helpButton.setOnClickListener {
            showHelpDialog()
        }

        setOnBackPressed(this, MainActivity::class.java)

    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Puntuación por ronda")
            .setMessage("O xogo consiste en unha serie de 8 preguntas.\n" +
                    "Cada pregunta ten 4 respostas posibles, das cales só unha é correcta.\n" +
                    "As preguntas 5,6 e 7 só teñen 3 respostas posibles.\n" +
                    "A pregunta 8 ten 2 respostas posibles e terás que depositar todo o diñeiro en unha delas.\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}