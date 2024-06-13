package com.galegando21.day16Anagramas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AnagramasInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonFacil: RadioButton
    private lateinit var radioButtonDificil: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anagramas_inicio)

        radioGroup = findViewById(R.id.anagramas_radio_group)
        radioButtonFacil = findViewById(R.id.anagramas_radio_btn_facil)
        radioButtonDificil = findViewById(R.id.anagramas_radio_btn_dificil)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_anagramas)
        comezarButton = findViewById(R.id.start_btn_anagramas)

        setBanner(this, R.string.anagramas)

        comezarButton.setOnClickListener {
            Intent(this@AnagramasInicioActivity, AnagramasGameActivity::class.java).also {
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
                    explicacionModoXogoTextView.text = "En este modo de xogo as preguntas e definicións serán obtidas de unha lista de palabras comúns."
                }
                radioButtonDificil.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo as pistas e palabras serán obtidas aleatoriamente de un diccionario.."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}