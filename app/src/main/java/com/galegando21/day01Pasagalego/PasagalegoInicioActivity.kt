package com.galegando21.day01Pasagalego

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

class PasagalegoInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonDiccionario: RadioButton
    private lateinit var radioButtonOrixinal: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasagalego_inicio)

        setBanner(this, R.string.pasagalego)

        radioGroup = findViewById(R.id.pasagalego_radio_group)
        radioButtonDiccionario = findViewById(R.id.pasagalego_radio_btn_diccionario)
        radioButtonOrixinal = findViewById(R.id.pasagalego_radio_btn_orixinal)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_pasagalego)
        comezarButton = findViewById(R.id.start_btn_pasagalego)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        comezarButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            Intent(this@PasagalegoInicioActivity, PasagalegoQuestionActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonDiccionario.id -> it.putExtra("modo", "diccionario")
                    radioButtonOrixinal.id -> it.putExtra("modo", "orixinal")
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonDiccionario.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo as preguntas e definicións serán obtidas do diccionario 'Digalego'."
                }
                radioButtonOrixinal.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo as preguntas e definicións serán obtidas do programa televisivo 'Pasapalabra'."
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