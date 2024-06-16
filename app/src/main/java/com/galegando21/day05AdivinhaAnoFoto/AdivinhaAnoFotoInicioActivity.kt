package com.galegando21.day05AdivinhaAnoFoto

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

class AdivinhaAnoFotoInicioActivity : AppCompatActivity() {
    private lateinit var helpButton: ImageButton
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivinha_ano_foto_inicio)

        setBanner(this, R.string.adivinha_ano_foto)

        comezarButton = findViewById(R.id.start_btn_adivinhaAnoFoto)
        helpButton = findViewById(R.id.adivinhaAnoFoto_help_btn)

        comezarButton.setOnClickListener {
            Intent(this@AdivinhaAnoFotoInicioActivity, AdivinhaAnoFotoGameActivity::class.java).also {
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
            .setMessage("\nAcertar o ano: 10 puntos\n" +
                    "1 ano de diferenza: 8 puntos\n" +
                    "2-3 anos de diferenza: 6 puntos\n" +
                    "4-5 anos de diferenza: 4 puntos\n" +
                    "6-10 anos de diferenza: 2 puntos\n" +
                    "11-20 anos de diferenza: 1 punto\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}