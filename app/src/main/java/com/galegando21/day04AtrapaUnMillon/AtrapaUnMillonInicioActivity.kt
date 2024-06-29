package com.galegando21.day04AtrapaUnMillon

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapaUnMillonInicioActivity : AppCompatActivity() {
    private lateinit var helpButton: ImageButton
    private lateinit var demoButton: ImageButton
    private lateinit var comezarButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_inicio)

        setBanner(this, R.string.atrapa_un_millon)

        comezarButton = findViewById(R.id.start_btn_atrapame_un_millon)
        helpButton = findViewById(R.id.atrapa_un_millon_help_btn)
        demoButton = findViewById(R.id.atrapa_un_millon_demo_btn)

        comezarButton.setOnClickListener {
            Intent(this@AtrapaUnMillonInicioActivity, AtrapaUnMillonQuestionActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        helpButton.setOnClickListener {
            showHelpDialog()
        }

        demoButton.setOnClickListener {
            showDemoDialog()
        }

        setOnBackPressed(this, MainActivity::class.java)

    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Explicación das preguntas")
            .setMessage("O xogo consiste en unha serie de 8 preguntas.\n" +
                    "Cada pregunta ten 4 respostas posibles, das cales só unha é correcta.\n" +
                    "As preguntas 5,6 e 7 só teñen 3 respostas posibles.\n" +
                    "A pregunta 8 ten 2 respostas posibles e terás que depositar todo o diñeiro en unha delas.\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
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
        val videoPath = "android.resource://" + packageName + "/" + R.raw.atrapa_un_millon_demo
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Iniciar el video
        videoView.start()

        dialog.show()
    }
}