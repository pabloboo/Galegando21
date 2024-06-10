package com.galegando21.day06SopaLetras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SopaLetrasConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class SopaLetrasInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonFacil: RadioButton
    private lateinit var radioButtonMedio: RadioButton
    private lateinit var radioButtonDificil: RadioButton
    private lateinit var explicacionImageView: ImageView
    private lateinit var explicacionTextView: TextView
    private lateinit var comezarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopa_letras_inicio)

        setBanner(this, R.string.sopa_de_letras)

        radioGroup = findViewById(R.id.sopa_letras_radio_group)
        radioButtonFacil = findViewById(R.id.sopa_letras_facil)
        radioButtonMedio = findViewById(R.id.sopa_letras_medio)
        radioButtonDificil = findViewById(R.id.sopa_letras_dificil)
        explicacionImageView = findViewById(R.id.explicacion_sopa_letras_image_view)
        explicacionTextView = findViewById(R.id.explicacion_sopa_letras_text_view)
        comezarButton = findViewById(R.id.start_btn_sopa_letras)

        comezarButton.setOnClickListener {
            Intent(this@SopaLetrasInicioActivity, SopaLetrasGameActivity::class.java).also {
                when(radioGroup.checkedRadioButtonId) {
                    radioButtonFacil.id -> it.putExtra("dificultade", SopaLetrasConstants.NIVEL_FACIL)
                    radioButtonMedio.id -> it.putExtra("dificultade", SopaLetrasConstants.NIVEL_MEDIO)
                    radioButtonDificil.id -> it.putExtra("dificultade", SopaLetrasConstants.NIVEL_DIFICIL)
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                radioButtonFacil.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_facil)
                    explicacionTextView.text = "Busca as 3 palabras mostradas en un tableiro de 5x5. \n As palabras só están en dirección vertical ou horizontal."
                }
                radioButtonMedio.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_medio)
                    explicacionTextView.text = "Busca 3 palabras, axudandote da pista dada, en un tableiro de 5x5, en menos de 1 minuto. \n As palabras poden estar en calquera dirección."
                }
                radioButtonDificil.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_dificil)
                    explicacionTextView.text = "Busca as 3 palabras mostradas en un tableiro de 6x6, en menos de 1 minuto. \n As palabras poden estar en calquera dirección."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}