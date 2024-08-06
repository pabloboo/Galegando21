package com.galegando21.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.DictionaryConstants
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AxustesActivity : AppCompatActivity() {
    private lateinit var nomeEditText: EditText

    private lateinit var radioGroupNivelFacil: RadioGroup
    private lateinit var radioButtonPalabrasComuns: RadioButton
    private lateinit var radioButtonPalabrasPet: RadioButton
    private lateinit var explicacionPalabrasNivelFacilTextView: TextView

    private lateinit var radioGroupNivelDificil: RadioGroup
    private lateinit var radioButtonDiccionarioRag: RadioButton
    private lateinit var radioButtonDiccionarioDigalego: RadioButton
    private lateinit var explicacionDiccionarioNivelDificilTextView: TextView

    private lateinit var gardarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_axustes)

        nomeEditText = findViewById(R.id.editTextName)
        radioGroupNivelFacil = findViewById(R.id.sources_modo_facil_radio_group)
        radioButtonPalabrasComuns = findViewById(R.id.axustes_radio_btn_sources_palabras_comuns)
        radioButtonPalabrasPet = findViewById(R.id.axustes_radio_btn_sources_palabras_pet)
        explicacionPalabrasNivelFacilTextView = findViewById(R.id.info_sources_modo_facil_tv)
        radioGroupNivelDificil = findViewById(R.id.sources_modo_dificil_radio_group)
        radioButtonDiccionarioRag = findViewById(R.id.axustes_radio_btn_sources_dictionary_rag)
        radioButtonDiccionarioDigalego = findViewById(R.id.axustes_radio_btn_sources_dictionary_digalego)
        explicacionDiccionarioNivelDificilTextView = findViewById(R.id.info_sources_modo_dificil_tv)
        gardarButton = findViewById(R.id.gardarButtonAxustes)

        setBanner(this, R.string.axustes)

        val sharedPreferencesOnboarding = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
        val nome = sharedPreferencesOnboarding.getString(SharedPreferencesKeys.NOME, "")
        nomeEditText.setText(nome)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val source = sharedPreferences.getString(SharedPreferencesKeys.PALABRAS_BASICAS_SOURCE, "palabras_basicas.json")
        when (source) {
            "palabras_basicas.json" -> {
                radioButtonPalabrasComuns.isChecked = true
                explicacionPalabrasNivelFacilTextView.text = "As palabras serán seleccionadas de unha lista de unhas 1000 palabras comúns en español traducidas ao galego coas súas correspondentes definicións no diccionario da Real Academia Galega."
            }
            "palabras_basicas_pet.json" -> {
                radioButtonPalabrasPet.isChecked = true
                explicacionPalabrasNivelFacilTextView.text = "As palabras serán seleccionadas de unha lista de unhas 1600 palabras do nivel PET de Cambridge traducidas ao galego coas súas correspondentes definicións no diccionario da Real Academia Galega."
            }
        }

        val dictionarySource = sharedPreferences.getString(SharedPreferencesKeys.DICTIONARY_SOURCE, "real_academia_galega_source")
        when (dictionarySource) {
            DictionaryConstants.RAG_SOURCE -> {
                radioButtonDiccionarioRag.isChecked = true
                explicacionDiccionarioNivelDificilTextView.text = "En este modo usaranse as palabras e definición do diccionario da Real Academia Galega."
            }
            DictionaryConstants.DIGALEGO_SOURCE -> {
                radioButtonDiccionarioDigalego.isChecked = true
                explicacionDiccionarioNivelDificilTextView.text = "En este modo usaranse as palabras e definición do diccionario 'Digalego'."
            }
        }

        radioGroupNivelFacil.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonPalabrasComuns.id -> {
                    explicacionPalabrasNivelFacilTextView.text = "As palabras serán seleccionadas de unha lista de unhas 1000 palabras comúns en español traducidas ao galego coas súas correspondentes definicións no diccionario 'DiGalego'."
                }
                radioButtonPalabrasPet.id -> {
                    explicacionPalabrasNivelFacilTextView.text = "As palabras serán seleccionadas de unha lista de unhas 1600 palabras do nivel PET de Cambridge traducidas ao galego coas súas correspondentes definicións no diccionario 'DiGalego'."
                }
            }
        }

        radioGroupNivelDificil.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                radioButtonDiccionarioRag.id -> {
                    explicacionDiccionarioNivelDificilTextView.text = "En este modo usaranse as palabras e definición do diccionario da Real Academia Galega."
                }
                radioButtonDiccionarioDigalego.id -> {
                    explicacionDiccionarioNivelDificilTextView.text = "En este modo usaranse as palabras e definición do diccionario 'Digalego'."
                }
            }
        }

        gardarButton.setOnClickListener {
            val nome = nomeEditText.text.toString()
            if (nome.isNotEmpty()) {
                val sharedPreferencesOnboarding = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
                val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)

                val source = when (radioGroupNivelFacil.checkedRadioButtonId) {
                    radioButtonPalabrasComuns.id -> "palabras_basicas.json"
                    radioButtonPalabrasPet.id -> "palabras_basicas_pet.json"
                    else -> "palabras_basicas.json"
                }

                val dictionarySource = when (radioGroupNivelDificil.checkedRadioButtonId) {
                    radioButtonDiccionarioRag.id -> DictionaryConstants.RAG_SOURCE
                    radioButtonDiccionarioDigalego.id -> DictionaryConstants.DIGALEGO_SOURCE
                    else -> DictionaryConstants.RAG_SOURCE
                }

                with(sharedPreferencesOnboarding.edit()) {
                    putString(SharedPreferencesKeys.NOME, nome)
                    commit()
                }

                with(sharedPreferences.edit()) {
                    putString(SharedPreferencesKeys.PALABRAS_BASICAS_SOURCE, source)
                    putString(SharedPreferencesKeys.DICTIONARY_SOURCE, dictionarySource)
                    commit()
                }

                Toast.makeText(this, "Datos gardados correctamente", Toast.LENGTH_SHORT).show()
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                Toast.makeText(this, "O nome non pode estar baleiro", Toast.LENGTH_SHORT).show()
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }
}