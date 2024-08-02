package com.galegando21.day09RuletaDaSorte

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

class RuletaDaSorteInicioActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonNormal: RadioButton
    private lateinit var radioButtonRefrans: RadioButton
    private lateinit var explicacionModoXogoTextView: TextView
    private lateinit var comezarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruleta_da_sorte_inicio)

        setBanner(this, R.string.ruleta_da_sorte)

        radioGroup = findViewById(R.id.ruleta_da_sorte_radio_group)
        radioButtonNormal = findViewById(R.id.ruleta_da_sorte_radio_btn_normal)
        radioButtonRefrans = findViewById(R.id.ruleta_da_sorte_radio_btn_refrans)
        explicacionModoXogoTextView = findViewById(R.id.modo_xogo_info_tv_ruleta_da_sorte)
        comezarButton = findViewById(R.id.start_btn_ruleta_da_sorte)

        comezarButton.setOnClickListener {
            Intent(this@RuletaDaSorteInicioActivity, RuletaDaSorteGameActivity::class.java).also {
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