package com.galegando21.day17ProbaVelocidade

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

class ProbaVelocidadeInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonNormal: RadioButton
    private lateinit var radioButtonRefrans: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proba_velocidade_inicio)

        setBanner(this, R.string.proba_velocidade)

        radioGroup = findViewById(R.id.proba_velocidade_radio_group)
        radioButtonNormal = findViewById(R.id.proba_velocidade_radio_btn_normal)
        radioButtonRefrans = findViewById(R.id.proba_velocidade_radio_btn_refrans)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_proba_velocidade)
        comezarButton = findViewById(R.id.start_btn_proba_velocidade)

        comezarButton.setOnClickListener {
            Intent(this@ProbaVelocidadeInicioActivity, ProbaVelocidadeGameActivity::class.java).also {
                when (radioGroup.checkedRadioButtonId) {
                    radioButtonNormal.id -> it.putExtra("modo", "normal")
                    radioButtonRefrans.id -> it.putExtra("modo", "refrans")
                }
                startActivity(it)
                finish()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonNormal.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo os tableiros serán obtidos do programa orixinal."
                }
                radioButtonRefrans.id -> {
                    explicacionModoXogoTextView.text = "En este modo de xogo os tableiros serán obtidos de unha lista de refráns."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}