package com.galegando21.day08Conexions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import com.galegando21.R
import com.galegando21.model.Conexions
import com.galegando21.utils.ConexionsGameConstants.getConexions
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import com.galegando21.utils.updateUserExperience
import kotlin.random.Random

class ConexionsGameActivity : AppCompatActivity() {
    private lateinit var palabrasGrid: GridLayout
    private lateinit var lifesImageView: ImageView
    private lateinit var deselectAllButton : Button
    private lateinit var sendButton : Button
    private lateinit var solutionGroup1Tv : TextView
    private lateinit var solutionGroup2Tv : TextView
    private lateinit var solutionGroup3Tv : TextView
    private lateinit var solutionGroup4Tv : TextView

    private var selectedTextView = mutableListOf<TextView>()
    private lateinit var conexionsData : Conexions

    private var correctAnswers = 0
    private var currentLifes = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexions_game)

        palabrasGrid = findViewById(R.id.gridPalabrasConexions)
        lifesImageView = findViewById(R.id.ConexionsLifesImageView)
        deselectAllButton = findViewById(R.id.deseleccionarConexionsButton)
        sendButton = findViewById(R.id.enviarConexionsButton)
        solutionGroup1Tv = findViewById(R.id.solution_grupo1)
        solutionGroup2Tv = findViewById(R.id.solution_grupo2)
        solutionGroup3Tv = findViewById(R.id.solution_grupo3)
        solutionGroup4Tv = findViewById(R.id.solution_grupo4)

        setBanner(this, R.string.conexions)

        initializeGame()

        palabrasGrid.children.forEach {
                wordTextView ->
                    if (wordTextView is TextView) {
                        wordTextView.setOnClickListener {
                            selectTextView(wordTextView)
                        }
                    }
        }

        deselectAllButton.setOnClickListener {
            deselectAll()
        }

        sendButton.setOnClickListener {
            enviarGrupo()
        }

        setOnBackPressed(this, ConexionsInicioActivity::class.java)
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
            wordTextView.background = getDrawable(R.drawable.conexions_textview_background_selected)
            wordTextView.setTextColor(resources.getColor(R.color.white, theme))
        } else {
            unselectTextView(wordTextView)
        }
    }

    private fun unselectTextView(wordTextView: TextView) {
        selectedTextView.remove(wordTextView)
        wordTextView.background = getDrawable(R.drawable.conexions_textview_background)
        wordTextView.setTextColor(resources.getColor(R.color.black, theme))
    }

    private fun deselectAll() {
        val selectedTextViewCopy = ArrayList(selectedTextView) // Copiar la lista
        selectedTextView.clear() // Limpiar la lista original

        for (textView in selectedTextViewCopy) {
            unselectTextView(textView)
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

                solutionGroup1Tv.visibility = View.VISIBLE
                solutionGroup1Tv.text =
                    conexionsData.grupo1Categoria + ": " + conexionsData.grupo1.toString()
                return
            }

            if (conexionsData.grupo2.contains(selectedTextView[0].text) &&
                conexionsData.grupo2.contains(selectedTextView[1].text) &&
                conexionsData.grupo2.contains(selectedTextView[2].text) &&
                conexionsData.grupo2.contains(selectedTextView[3].text)) {
                saveSolution()

                solutionGroup2Tv.visibility = View.VISIBLE
                solutionGroup2Tv.text =
                    conexionsData.grupo2Categoria + ": " + conexionsData.grupo2.toString()
                return
            }

            if (conexionsData.grupo3.contains(selectedTextView[0].text) &&
                conexionsData.grupo3.contains(selectedTextView[1].text) &&
                conexionsData.grupo3.contains(selectedTextView[2].text) &&
                conexionsData.grupo3.contains(selectedTextView[3].text)) {
                saveSolution()

                solutionGroup3Tv.visibility = View.VISIBLE
                solutionGroup3Tv.text =
                    conexionsData.grupo3Categoria + ": " + conexionsData.grupo3.toString()
                return
            }

            if (conexionsData.grupo4.contains(selectedTextView[0].text) &&
                conexionsData.grupo4.contains(selectedTextView[1].text) &&
                conexionsData.grupo4.contains(selectedTextView[2].text) &&
                conexionsData.grupo4.contains(selectedTextView[3].text)) {
                saveSolution()

                solutionGroup4Tv.visibility = View.VISIBLE
                solutionGroup4Tv.text =
                    conexionsData.grupo4Categoria + ": " + conexionsData.grupo4.toString()
                return
            }

            currentLifes--
            updateLifes()

        } else {
            Toast.makeText(this, "Selecciona 4 elementos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLifes() {
        when (currentLifes) {
            6 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_5)
            5 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_4)
            4 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_3)
            3 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_2)
            2 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_1)
            1 -> lifesImageView.setImageResource(R.drawable.conexions_lifes_0)
            else -> lifesImageView.setImageResource(R.drawable.conexions_lifes_0)
        }
        if (currentLifes === 0) {
            sendButton.text="Xogar de novo"
            sendButton.setOnClickListener {
                Intent(this@ConexionsGameActivity, ConexionsGameActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            showSolution()
        }
    }

    private fun showSolution() {
        solutionGroup1Tv.visibility = View.VISIBLE
        solutionGroup1Tv.text = conexionsData.grupo1Categoria + ": " + conexionsData.grupo1.toString()
        solutionGroup2Tv.visibility = View.VISIBLE
        solutionGroup2Tv.text = conexionsData.grupo2Categoria + ": " + conexionsData.grupo2.toString()
        solutionGroup3Tv.visibility = View.VISIBLE
        solutionGroup3Tv.text = conexionsData.grupo3Categoria + ": " + conexionsData.grupo3.toString()
        solutionGroup4Tv.visibility = View.VISIBLE
        solutionGroup4Tv.text = conexionsData.grupo4Categoria + ": " + conexionsData.grupo4.toString()
    }

    private fun saveSolution() {
        for (wordTextView : TextView in selectedTextView) {
            wordTextView.background = getDrawable(R.drawable.conexions_textview_background_correct)
            wordTextView.setTextColor(resources.getColor(R.color.black, theme))
            wordTextView.setOnClickListener{}
        }
        selectedTextView = mutableListOf() //borrar selección
        correctAnswers++
        if (correctAnswers === 4) {
            endGame()
            changeConexionsStatistics()
        }
    }

    private fun endGame() {
        sendButton.text="Xogar de novo"
        sendButton.setOnClickListener {
            Intent(this@ConexionsGameActivity, ConexionsGameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun changeConexionsStatistics() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var maxLifes = 0
        if (sharedPreferences.contains(SharedPreferencesKeys.CONEXIONS_MAX_SCORE)) {
            maxLifes = sharedPreferences.getInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, 0)
        }

        if (currentLifes > maxLifes) {
            editor.putInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, currentLifes)
            editor.apply()
        }
        Log.d("maxStreak", sharedPreferences.getInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, 0).toString())

        updateCurrentStreak(this)

        val experience = updateUserExperience(this, currentLifes*10)
        Toast.makeText(this, "Gañaches $experience puntos de experiencia", Toast.LENGTH_SHORT).show()
    }
}