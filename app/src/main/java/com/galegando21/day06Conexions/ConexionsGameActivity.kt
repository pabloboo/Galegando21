package com.galegando21.day06Conexions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.galegando21.BannerFragment
import com.galegando21.R
import com.galegando21.model.Conexions
import com.galegando21.utils.ConexionsGameConstants.getConexions
import kotlin.random.Random

class ConexionsGameActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment
    private lateinit var palabrasLayout: ConstraintLayout
    private var selectedTextView = mutableListOf<TextView>()
    private lateinit var conexionsData : Conexions
    private lateinit var sendButton : Button

    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexions_game)

        palabrasLayout = findViewById(R.id.conexionsPalabrasLayout)
        sendButton = findViewById(R.id.enviarConexionsButton)

        // Settear el banner
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.conexions))
        }.commit()

        initializeGame()

        palabrasLayout.children.forEach {
                wordTextView ->
                    if (wordTextView is TextView) {
                        wordTextView.setOnClickListener {
                            selectTextView(wordTextView)
                        }
                    }
        }

        sendButton.setOnClickListener {
            enviarGrupo()
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Intent(this@ConexionsGameActivity, ConexionsInicioActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun initializeGame() {
        val conexionList : MutableList<Conexions> = getConexions()
        conexionsData = conexionList[Random.nextInt(0, conexionList.size)]
        var palabra1 : TextView = findViewById(R.id.conexionsTv1)
        palabra1.text = conexionsData.palabra1
        var palabra2 : TextView = findViewById(R.id.conexionsTv2)
        palabra2.text = conexionsData.palabra2
        var palabra3 : TextView = findViewById(R.id.conexionsTv3)
        palabra3.text = conexionsData.palabra3
        var palabra4 : TextView = findViewById(R.id.conexionsTv4)
        palabra4.text = conexionsData.palabra4
        var palabra5 : TextView = findViewById(R.id.conexionsTv5)
        palabra5.text = conexionsData.palabra5
        var palabra6 : TextView = findViewById(R.id.conexionsTv6)
        palabra6.text = conexionsData.palabra6
        var palabra7 : TextView = findViewById(R.id.conexionsTv7)
        palabra7.text = conexionsData.palabra7
        var palabra8 : TextView = findViewById(R.id.conexionsTv8)
        palabra8.text = conexionsData.palabra8
        var palabra9 : TextView = findViewById(R.id.conexionsTv9)
        palabra9.text = conexionsData.palabra9
        var palabra10 : TextView = findViewById(R.id.conexionsTv10)
        palabra10.text = conexionsData.palabra10
        var palabra11 : TextView = findViewById(R.id.conexionsTv11)
        palabra11.text = conexionsData.palabra11
        var palabra12 : TextView = findViewById(R.id.conexionsTv12)
        palabra12.text = conexionsData.palabra12
        var palabra13 : TextView = findViewById(R.id.conexionsTv13)
        palabra13.text = conexionsData.palabra13
        var palabra14 : TextView = findViewById(R.id.conexionsTv14)
        palabra14.text = conexionsData.palabra14
        var palabra15 : TextView = findViewById(R.id.conexionsTv15)
        palabra15.text = conexionsData.palabra15
        var palabra16 : TextView = findViewById(R.id.conexionsTv16)
        palabra16.text = conexionsData.palabra16
    }

    private fun selectTextView(wordTextView: TextView) {
        if (!selectedTextView.contains(wordTextView)) {
            selectedTextView.add(wordTextView)
            wordTextView.background = getDrawable(R.color.darkGray)
            wordTextView.setTextColor(resources.getColor(R.color.white, theme))
        } else {
            selectedTextView.remove(wordTextView)
            wordTextView.background = getDrawable(R.color.canela)
            wordTextView.setTextColor(resources.getColor(R.color.black, theme))
        }
    }

    private fun enviarGrupo() {
        if (selectedTextView.size===4) {
            Log.d("SELECTED", "enviarGrupo: ${selectedTextView[0].text}")
            Log.d("SELECTED", "enviarGrupo: ${selectedTextView[1].text}")
            Log.d("SELECTED", "enviarGrupo: ${selectedTextView[2].text}")
            Log.d("SELECTED", "enviarGrupo: ${selectedTextView[3].text}")
            if (conexionsData.grupo1.contains(selectedTextView[0].text) &&
                conexionsData.grupo1.contains(selectedTextView[1].text) &&
                conexionsData.grupo1.contains(selectedTextView[2].text) &&
                conexionsData.grupo1.contains(selectedTextView[3].text)) {
                saveSolution()
                return
            }

            if (conexionsData.grupo2.contains(selectedTextView[0].text) &&
                conexionsData.grupo2.contains(selectedTextView[1].text) &&
                conexionsData.grupo2.contains(selectedTextView[2].text) &&
                conexionsData.grupo2.contains(selectedTextView[3].text)) {
                saveSolution()
                return
            }

            if (conexionsData.grupo3.contains(selectedTextView[0].text) &&
                conexionsData.grupo3.contains(selectedTextView[1].text) &&
                conexionsData.grupo3.contains(selectedTextView[2].text) &&
                conexionsData.grupo3.contains(selectedTextView[3].text)) {
                saveSolution()
                return
            }

            if (conexionsData.grupo4.contains(selectedTextView[0].text) &&
                conexionsData.grupo4.contains(selectedTextView[1].text) &&
                conexionsData.grupo4.contains(selectedTextView[2].text) &&
                conexionsData.grupo4.contains(selectedTextView[3].text)) {
                saveSolution()
                return
            }
        } else {
            Toast.makeText(this, "Selecciona 4 elementos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveSolution() {
        for (wordTextView : TextView in selectedTextView) {
            wordTextView.background = getDrawable(R.color.correctGreen)
            wordTextView.setTextColor(resources.getColor(R.color.white, theme))
            wordTextView.setOnClickListener{}
        }
        selectedTextView = mutableListOf() //borrar selección
        correctAnswers++
        if (correctAnswers === 4) {
            endGame()
        }
    }

    private fun endGame() {
        sendButton.text="Xogar de novo"
        sendButton.setOnClickListener {
            Intent(this@ConexionsGameActivity, ConexionsGameActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}