package com.galegando21.day06SopaLetras

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.VideoView
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
    private lateinit var demoButton: ImageButton
    private lateinit var explicacionImageView: ImageView
    private lateinit var explicacionTextView: TextView
    private lateinit var comezarButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopa_letras_inicio)

        setBanner(this, R.string.sopa_de_letras)

        radioGroup = findViewById(R.id.sopa_letras_radio_group)
        radioButtonFacil = findViewById(R.id.sopa_letras_facil)
        radioButtonMedio = findViewById(R.id.sopa_letras_medio)
        radioButtonDificil = findViewById(R.id.sopa_letras_dificil)
        demoButton = findViewById(R.id.sopa_letras_demo_btn)
        explicacionImageView = findViewById(R.id.explicacion_sopa_letras_image_view)
        explicacionTextView = findViewById(R.id.explicacion_sopa_letras_text_view)
        comezarButton = findViewById(R.id.start_btn_sopa_letras)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)

        comezarButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
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

        demoButton.setOnClickListener {
            showDemoDialog()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                radioButtonFacil.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_facil)
                    explicacionTextView.text = "Busca as 3 palabras mostradas en un tableiro de 5x5. \n As palabras só están en dirección vertical ou horizontal."
                }
                radioButtonMedio.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_medio)
                    explicacionTextView.text = "Busca 3 palabras, axudandote da pista dada, en un tableiro de 6x6, en menos de 1 minuto. \n As palabras poden estar en calquera dirección."
                }
                radioButtonDificil.id -> {
                    explicacionImageView.setImageResource(R.drawable.demo_sopa_letras_dificil)
                    explicacionTextView.text = "Busca as 3 palabras mostradas en un tableiro de 6x6, en menos de 30 segundos. \n As palabras poden estar en calquera dirección."
                }
            }
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    override fun onStop() {
        super.onStop()

        loadingProgressBar.visibility = View.GONE
    }

    private fun showDemoDialog() {
        // Crear un nuevo diálogo
        val dialog = Dialog(this)

        // Establecer el layout del diálogo
        dialog.setContentView(R.layout.dialog_video)

        // Encontrar el VideoView en el layout
        val videoView = dialog.findViewById<VideoView>(R.id.videoView)

        // Encontrar el TextView en el layout y establecer el título
        val titleView = dialog.findViewById<TextView>(R.id.dialogTitle)
        titleView.text = "Como xogar?"

        // Establecer la ruta del video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.sopa_de_letras_demo
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener {
            Log.i("VideoView", "Video is prepared and ready to start.")

            // Iniciar el video
            videoView.start()
        }

        dialog.show()
    }
}