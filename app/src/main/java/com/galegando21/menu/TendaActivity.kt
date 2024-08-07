package com.galegando21.menu

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.TendaConstants
import com.galegando21.utils.TendaItem
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class TendaActivity : AppCompatActivity() {
    private lateinit var coinsTv: TextView
    private lateinit var helpBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenda)

        setBanner(this, R.string.tenda)

        coinsTv = findViewById(R.id.tenda_coins_tv)
        helpBtn = findViewById(R.id.tenda_help_btn)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, MODE_PRIVATE)
        val coins = sharedPreferences.getInt(SharedPreferencesKeys.COINS, 0)

        coinsTv.text = "$coins moedas virtuais"

        helpBtn.setOnClickListener {
            showHelpDialog()
        }

        val items = TendaConstants.getTendaItems()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = TendaAdapter(items, this, coinsTv)

        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Moedas virtuais")
            .setMessage("\nUsa as túas moedas para mercar elementos coleccionables.\n" +
                    "Cada 1000 puntos de experiencia que consigas obterás unha moeda virtual.\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

class TendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.item_image)
    val descriptionView: TextView = itemView.findViewById(R.id.item_description)
    val priceView: TextView = itemView.findViewById(R.id.item_price)
    val buyButton: Button = itemView.findViewById(R.id.item_buy_button)
}

class TendaAdapter(private val items: List<TendaItem>, private val context: Context, private val coinsTv: TextView) : RecyclerView.Adapter<TendaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TendaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tenda, parent, false)
        return TendaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TendaViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.image)
        holder.descriptionView.text = item.description
        holder.priceView.text = "${item.price} moedas."

        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, AppCompatActivity.MODE_PRIVATE)
        val coins = sharedPreferences.getInt(SharedPreferencesKeys.COINS, 0)
        holder.priceView.setTextColor(context.getColor(if (item.price <= coins) R.color.correctGreen else R.color.errorRed))
        val isPurchased = sharedPreferences.getBoolean(item.sharedPreferencesKey, false)

        // Restablecer las vistas a su estado original (las vistas se reciclan al haber muchos elementos en la lista)
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(1f)
        val filter = ColorMatrixColorFilter(colorMatrix)
        holder.imageView.colorFilter = filter
        holder.buyButton.isEnabled = true
        holder.buyButton.text = "Comprar"
        holder.buyButton.background = context.getDrawable(R.color.correctGreen)

        if (!isPurchased) {
            // Imagen en escala de grises
            colorMatrix.setSaturation(0f) // Escala de grises
            val filter = ColorMatrixColorFilter(colorMatrix)
            holder.imageView.colorFilter = filter
        } else {
            holder.buyButton.text = "Comprado"
            holder.buyButton.background = context.getDrawable(R.color.inactiveBlue)
            holder.buyButton.isEnabled = false
        }

        if (coins < item.price) {
            holder.buyButton.isEnabled = false
        }

        holder.buyButton.setOnClickListener {
            sharedPreferences.edit().putBoolean(item.sharedPreferencesKey, true).apply()
            val newCoins = coins - item.price
            sharedPreferences.edit().putInt(SharedPreferencesKeys.COINS, newCoins).apply()
            it.isEnabled = false
            holder.buyButton.text = "Comprado"
            holder.buyButton.background = context.getDrawable(R.color.inactiveBlue)
            holder.imageView.colorFilter = null
            coinsTv.text = "$newCoins moedas virtuais"
            Toast.makeText(context, "Elemento comprado!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = items.size
}