package com.galegando21.day22Brisca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class BriscaInicioActivity : AppCompatActivity() {
    private lateinit var helpButton: ImageButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonFacil: RadioButton
    private lateinit var radioButtonDificil: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brisca_inicio)

        setBanner(this, R.string.brisca)

        radioGroup = findViewById(R.id.brisca_radio_group)
        radioButtonFacil = findViewById(R.id.brisca_radio_btn_facil)
        radioButtonDificil = findViewById(R.id.brisca_radio_btn_dificil)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_brisca)
        helpButton = findViewById(R.id.brisca_help_btn)
        comezarButton = findViewById(R.id.start_btn_brisca)

        comezarButton.setOnClickListener {
            Intent(this@BriscaInicioActivity, BriscaGameActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonFacil.id -> it.putExtra("modo", "facil")
                    radioButtonDificil.id -> it.putExtra("modo", "dificil")
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonFacil.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo a máquina seleccionará unha carta aleatoria da súa man."
                }
                radioButtonDificil.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo a máquina seleccionará a mellor carta, en base a unha serie de regras simples, da súa man."
                }
            }
        }

        helpButton.setOnClickListener {
            showHelpDialog()
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Valor das cartas")
            .setMessage("As: 11 puntos.\n" +
                    "Tres: 10 puntos.\n" +
                    "Rey: 4 puntos.\n" +
                    "Cabalo: 3 puntos.\n" +
                    "Sota: 2 puntos.\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}