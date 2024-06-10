package com.galegando21.day10ExplosionDePalabras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class ExplosionPalabrasInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonFacil: RadioButton
    private lateinit var radioButtonDificil: RadioButton
    private lateinit var explicacionDificultadeXogoTextView: TextView
    private lateinit var comezarButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explosion_palabras_inicio)

        setBanner(this, R.string.explosion_de_palabras)

        radioGroup = findViewById(R.id.explosion_palabras_radio_group)
        radioButtonFacil = findViewById(R.id.explosion_palabras_radio_btn_facil)
        radioButtonDificil = findViewById(R.id.explosion_palabras_radio_btn_dificil)
        explicacionDificultadeXogoTextView = findViewById(R.id.dificultade_xogo_info_tv_explosion_palabras)
        comezarButton = findViewById(R.id.start_btn_explosion_palabras)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        comezarButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            Intent(this@ExplosionPalabrasInicioActivity, ExplosionPalabrasGameActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonFacil.id -> it.putExtra("dificultade", "facil")
                    radioButtonDificil.id -> it.putExtra("dificultade", "dificil")
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonFacil.id -> {
                    explicacionDificultadeXogoTextView.text = "En este nivel de dificultade aparecerá unha nova letra cada 3 segundos."
                }
                radioButtonDificil.id -> {
                    explicacionDificultadeXogoTextView.text = "En este nivel de dificultade aparecerá unha nova letra cada segundo."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    override fun onStop() {
        super.onStop()

        loadingProgressBar.visibility = View.GONE
    }
}