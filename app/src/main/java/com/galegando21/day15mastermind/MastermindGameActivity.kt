package com.galegando21.day15mastermind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class MastermindGameActivity : AppCompatActivity() {
    private lateinit var coloresDisponiblesTv: TextView
    private lateinit var respostaUsuarioEt: EditText
    private lateinit var enviarRespostaBtn: Button
    private lateinit var pistasTv: TextView
    private lateinit var finalizarBtn: Button

    val coloresDisponibles = "Vermello, Rosa, Azul, Branco, Laranxa, Violeta, Negro, Marrón"
    val coloresDisponiblesIniciales = listOf('V', 'R', 'A', 'B', 'L', 'V', 'N', 'M')
    val longitudCombinacion = 4
    val intentosMaximos = 10
    private lateinit var combinacionSecreta: String

    var intentos = 0
    var ganado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mastermind_game)

        coloresDisponiblesTv = findViewById(R.id.colores_disponibles_mastermind_tv)
        respostaUsuarioEt = findViewById(R.id.resposta_usuario_mastermind_et)
        respostaUsuarioEt.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(longitudCombinacion))
        enviarRespostaBtn = findViewById(R.id.enviar_resposta_button_mastermind)
        pistasTv = findViewById(R.id.pistas_mastermind_tv)
        finalizarBtn = findViewById(R.id.finalizar_button_mastermind)

        setBanner(this, R.string.mastermind)
        setOnBackPressed(this, MainActivity::class.java)

        coloresDisponiblesTv.text = "Cores dispoñibles: $coloresDisponibles"

        combinacionSecreta = generarCombinacionSecreta(coloresDisponiblesIniciales, longitudCombinacion)
        Log.d("MastermindGameActivity", "Combinación secreta: $combinacionSecreta")

        enviarRespostaBtn.setOnClickListener {
            enviarResposta()
        }

        finalizarBtn.setOnClickListener {
            finalizarPartida()
        }
    }

    // Función para enviar una respuesta
    fun enviarResposta() {
        if (intentos < intentosMaximos) {
            val suposicion = respostaUsuarioEt.text.toString()
            if (suposicion.length != longitudCombinacion || !suposicion.all { it in coloresDisponiblesIniciales }) {
                Toast.makeText(this, "Ingresa unha suposición válida", Toast.LENGTH_SHORT).show()
            } else {
                val (correctos, malColocados) = evaluarSuposicion(combinacionSecreta, suposicion)
                pistasTv.text = "Pistas: $correctos correcto(s), $malColocados mal colocado(s)."

                if (correctos == longitudCombinacion) {
                    ganado = true
                }

                intentos++
            }
        } else {
            pistasTv.text = "Sintoo, agotaches os teus $intentosMaximos intentos. A combinación segreda era '$combinacionSecreta'."
            finalizarBtn.visibility = View.VISIBLE
        }

        if (ganado) {
            pistasTv.text = "Felicidades! Adiviñaches a combinación segreda '$combinacionSecreta' en $intentos intentos."
            finalizarBtn.visibility = View.VISIBLE
        }
    }

    // Función para generar una combinación secreta aleatoria
    fun generarCombinacionSecreta(coloresDisponibles: List<Char>, longitud: Int): String {
        return (1..longitud).map { coloresDisponibles.random() }.joinToString("")
    }

    // Función para evaluar una suposición y devolver las pistas
    fun evaluarSuposicion(combinacionSecreta: String, suposicion: String): Pair<Int, Int> {
        var correctos = 0
        var malColocados = 0

        val combinacionSecretaList = combinacionSecreta.toList().toMutableList()
        val suposicionList = suposicion.toList().toMutableList()

        for (i in suposicionList.indices) {
            if (suposicionList[i] == combinacionSecretaList[i]) {
                correctos++
                // Para evitar que se cuenten dos veces los correctos en el siguiente paso
                combinacionSecretaList[i] = ' '
                suposicionList[i] = ' '
            }
        }

        for (i in suposicionList.indices) {
            val index = combinacionSecretaList.indexOf(suposicionList[i])
            if (index != -1) { // Si el color está en la combinación secreta
                malColocados++
                combinacionSecretaList[index] = ' '
            }
        }

        return Pair(correctos, malColocados)
    }

    fun finalizarPartida() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }
}