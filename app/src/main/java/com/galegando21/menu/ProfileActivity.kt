package com.galegando21.menu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.PurchasedItem
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.TendaConstants
import com.galegando21.utils.screenShot
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.shareScreenshot

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileCardView: CardView
    private lateinit var profileImage: ImageView
    private lateinit var nameTv: TextView
    private lateinit var levelTv: TextView
    private lateinit var maxStreakTv: TextView
    private lateinit var experienceTv: TextView
    private lateinit var numberOfBadgesTv: TextView
    private lateinit var virtualCoinsTv: TextView
    private lateinit var numberOfColeccionables: TextView
    private lateinit var helpBtn: ImageButton
    private lateinit var shareBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setOnBackPressed(this, MainActivity::class.java)

        profileCardView = findViewById(R.id.profile_card)
        profileImage = findViewById(R.id.profile_image)
        nameTv = findViewById(R.id.profile_name_tv)
        levelTv = findViewById(R.id.profile_level_tv)
        maxStreakTv = findViewById(R.id.profile_max_streak_tv)
        experienceTv = findViewById(R.id.profile_experience_tv)
        numberOfBadgesTv = findViewById(R.id.profile_badges_number_tv)
        virtualCoinsTv = findViewById(R.id.profile_virtual_coins_tv)
        numberOfColeccionables = findViewById(R.id.profile_coleccionables_number_tv)
        helpBtn = findViewById(R.id.profile_help_btn)
        shareBtn = findViewById(R.id.share_btn_profile)

        val sharedPreferencesOnboarding = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
        val nome = sharedPreferencesOnboarding.getString(SharedPreferencesKeys.NOME, "")
        nameTv.text = nome

        val level = getLevel()
        levelTv.text = "Nivel: $level."

        val sharedPreferencesStatistics = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val longestStreak = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
        maxStreakTv.text = "Racha máxima: $longestStreak."

        val experience = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0)
        experienceTv.text = "Experiencia: $experience puntos."

        val numberOfBadges = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.NUMBER_OF_BADGES, 0)
        numberOfBadgesTv.text = "Nº de insignias: $numberOfBadges."

        val sharedPreferencesVirtualCoins = getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, MODE_PRIVATE)
        val virtualCoins = sharedPreferencesVirtualCoins.getInt(SharedPreferencesKeys.COINS, 0)
        virtualCoinsTv.text = "Moedas virtuais: $virtualCoins."

        // Elementos coleccionables
        val items = TendaConstants.getPurchasedItems(this)
        numberOfColeccionables.text = "Nº de coleccionables: ${items.size}."

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ColeccionablesAdapter(items)

        helpBtn.setOnClickListener {
            showHelpDialog()
        }

        shareBtn.setOnClickListener {
            val bitmap = screenShot(profileCardView)
            shareScreenshot(bitmap, this)
        }
    }

    private fun getLevel(): Int {
        val sharedPreferencesStatistics = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val experience = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0)
        val numberOfBadges = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.NUMBER_OF_BADGES, 0)

        if (experience >= 35001 && numberOfBadges >= 28) return 10

        when (experience) {
            in 0..1000 -> return 1
            in 1001..5000 -> return 2
            in 5001..10000 -> return 3
            in 10001..15000 -> return 4
            in 15001..20000 -> return 5
            in 20001..25000 -> return 6
            in 25001..30000 -> return 7
            in 30001..35000 -> return 8
            in 35001..Int.MAX_VALUE -> return 9
            else -> return 0
        }
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Niveis")
            .setMessage("\nNivel 2: Máis de 1000 pts de exp.\n" +
                    "Nivel 3: Máis de 5000 pts de exp.\n" +
                    "Nivel 4: Máis de 10000 pts de exp.\n" +
                    "Nivel 5: Máis de 15000 pts de exp.\n" +
                    "Nivel 6: Máis de 20000 pts de exp.\n" +
                    "Nivel 7: Máis de 25000 pts de exp.\n" +
                    "Nivel 8: Máis de 30000 pts de exp.\n" +
                    "Nivel 9: Máis de 35000 pts de exp.\n" +
                    "Nivel 10: Máis de 35000 pts de exp. e 28 insignias.\n")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

class ColeccionablesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.item_image)
    val descriptionView: TextView = itemView.findViewById(R.id.item_description)
}

class ColeccionablesAdapter(private val items: List<PurchasedItem>) : RecyclerView.Adapter<ColeccionablesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionablesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coleccionable, parent, false)
        return ColeccionablesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColeccionablesViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.image)
        holder.descriptionView.text = item.description
    }

    override fun getItemCount() = items.size
}