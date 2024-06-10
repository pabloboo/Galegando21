package com.galegando21.day03XogoPalabras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class XogoPalabrasInicioActivity : AppCompatActivity() {
    private lateinit var helpButton: ImageButton
    private lateinit var comezarButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xogo_palabras_inicio)

        helpButton = findViewById(R.id.xogo_palabras_help_btn)
        comezarButton = findViewById(R.id.start_btn_xogo_palabras)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        setBanner(this, R.string.xogo_de_palabras)

        helpButton.setOnClickListener {
            showHelpDialog()
        }

        comezarButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            Intent(this@XogoPalabrasInicioActivity, XogoPalabrasGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    override fun onStop() {
        super.onStop()

        loadingProgressBar.visibility = View.GONE
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Puntuación")
            .setMessage("\n1 punto: 4 letras\n" +
                    "2 puntos: 5 letras\n" +
                    "3 puntos: 6 letras\n" +
                    "5 puntos: 7 letras\n" +
                    "10 puntos adicionais: Usar todas as letras\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}