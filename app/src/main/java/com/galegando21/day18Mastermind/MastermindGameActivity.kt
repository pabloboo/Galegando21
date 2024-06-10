package com.galegando21.day18Mastermind

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class MastermindGameActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var zonaSolucion : LinearLayout
    private lateinit var clearBtn: Button
    private lateinit var enviarRespostaBtn: Button
    private lateinit var pistasTv: TextView
    private lateinit var xogarDeNovoBtn: Button
    private lateinit var finalizarBtn: Button

    val colorMap: Map<Int, Char> = mapOf(
        R.color.errorRed to 'R',
        R.color.primaryBlue to 'B',
        R.color.correctGreen to 'G',
        R.color.yellow to 'Y',
        R.color.orange to 'O',
        R.color.pink to 'P'
    )
    val longitudCombinacion = 4
    val intentosMaximos = 10
    private lateinit var combinacionSecreta: String

    var intentos = 0
    var ganado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mastermind_game)

        recyclerView = findViewById(R.id.recycler_view_mastermind)
        zonaSolucion = findViewById(R.id.solution_zone_mastermind)
        clearBtn = findViewById(R.id.clear_btn_mastermind)
        enviarRespostaBtn = findViewById(R.id.enviar_resposta_button_mastermind)
        pistasTv = findViewById(R.id.pistas_mastermind_tv)
        xogarDeNovoBtn = findViewById(R.id.xogar_de_novo_button_mastermind)
        finalizarBtn = findViewById(R.id.finalizar_button_mastermind)

        setBanner(this, R.string.mastermind)
        setOnBackPressed(this, MastermindInicioActivity::class.java)

        // Inicializar RecyclerView y establecer su adaptador
        val colors = colorMap.keys.toList()
        val adapter = ColorAdapter(colors, colorMap, object : ColorAdapter.StartDragListener {
            override fun requestDrag(viewHolder: RecyclerView.ViewHolder) {
                val dragShadowBuilder = View.DragShadowBuilder(viewHolder.itemView)
                viewHolder.itemView.startDragAndDrop(null, dragShadowBuilder, viewHolder.itemView, 0)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Zona de solución
        setOnDragListenerZonaSolucion()
        // Eliminar elementos de la zona de solución
        setOnDragListenerMainLayout()

        combinacionSecreta = generarCombinacionSecreta(colorMap.values.toList(), longitudCombinacion)
        Log.d("MastermindGameActivity", "Combinación secreta: $combinacionSecreta")

        enviarRespostaBtn.setOnClickListener {
            enviarResposta()
        }

        xogarDeNovoBtn.setOnClickListener {
            xogarDeNovo()
        }

        finalizarBtn.setOnClickListener {
            finalizarPartida()
        }

        clearBtn.setOnClickListener {
            limpiarZonaSolucion()
        }
    }

    // Función para enviar una respuesta
    fun enviarResposta() {
         if (!ganado) {
            if (intentos < intentosMaximos) {
                val suposicion = StringBuilder()
                for (i in 0 until zonaSolucion.childCount) {
                    val cardView = zonaSolucion.getChildAt(i) as CardView
                    val colorCard = cardView.findViewById<ImageView>(R.id.color_image_mastermind)
                    val color = colorCard.tag as Char
                    suposicion.append(color)
                }

                if (suposicion.length != longitudCombinacion || !suposicion.all { it in colorMap.values }) {
                    Toast.makeText(this, "Arrastra 4 cores á zona grisácea.", Toast.LENGTH_SHORT).show()
                } else {
                    val (correctos, malColocados) = evaluarSuposicion(combinacionSecreta, suposicion.toString())
                    pistasTv.text = "Pistas: $correctos correcto(s), $malColocados mal colocado(s).\n\n Intentos restantes: ${intentosMaximos - intentos}"

                    if (correctos == longitudCombinacion) {
                        ganado = true
                    }

                    intentos++
                }
            } else {
                pistasTv.text = "Síntoo, agotaches os teus $intentosMaximos intentos. Podes ver a combinación segreda na zona de solución."
                mostrarCombinacionSecreta()
                xogarDeNovoBtn.visibility = View.VISIBLE
                finalizarBtn.visibility = View.VISIBLE
            }
        }

        if (ganado) {
            pistasTv.text = "Felicidades! Adiviñaches a combinación segreda en $intentos intentos."
            xogarDeNovoBtn.visibility = View.VISIBLE
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
            }
        }

        Log.d("MastermindGameActivity", "Combinación secreta restante: $combinacionSecretaList")
        for (i in suposicionList.indices) {
            val index = combinacionSecretaList.indexOf(suposicionList[i])
            if (index != -1) { // Si el color está en la combinación secreta
                malColocados++
                combinacionSecretaList[index] = ' '
            }
        }

        return Pair(correctos, malColocados)
    }

    fun xogarDeNovo() {
        Intent(this, MastermindGameActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    fun finalizarPartida() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    // Función para establecer el listener de drag and drop en la zona de solución
    fun setOnDragListenerZonaSolucion() {
        zonaSolucion.setOnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    // Cambia el color de fondo de la zona de solución para indicar que se puede soltar
                    v.setBackgroundColor(Color.LTGRAY)
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // Cambia el color de fondo de la zona de solución a su estado normal
                    v.setBackgroundColor(Color.DKGRAY)
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // Añade el color a la solución y actualiza la interfaz de usuario
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = v as LinearLayout
                    container.addView(view)
                    view.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    // Vuelve a cambiar el color de fondo de la zona de solución a su estado normal
                    v.setBackgroundColor(Color.DKGRAY)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    // Función para establecer el listener de drag and drop en el layout principal (permitir eliminar elementos da zona de solución)
    fun setOnDragListenerMainLayout() {
        // En tu método onCreate
        val mainLayout = findViewById<ViewGroup>(R.id.mainLayoutMastermind)
        mainLayout.setOnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // Si la vista donde se soltó el elemento no es la solutionZone, elimina el elemento de la solutionZone
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    if (v.id != R.id.solution_zone_mastermind) {
                        owner.removeView(view)
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun limpiarZonaSolucion() {
        zonaSolucion.removeAllViews()
    }

    fun charToColorResource(c: Char): Int {
        val invertedColorMap = colorMap.entries.associateBy({ it.value }) { it.key }
        return invertedColorMap[c] ?: throw IllegalArgumentException("Invalid color character: $c")
    }

    fun mostrarCombinacionSecreta() {
        limpiarZonaSolucion()

        for (c in combinacionSecreta) {
            val colorCard = layoutInflater.inflate(R.layout.color_card_mastermind, zonaSolucion, false) as CardView
            val colorImage = colorCard.findViewById<ImageView>(R.id.color_image_mastermind)
            colorImage.setImageResource(charToColorResource(c))
            zonaSolucion.addView(colorCard)
        }
    }
}