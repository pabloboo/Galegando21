package com.galegando21.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.galegando21.R

object TendaConstants {

    fun getTendaItems(): List<TendaItem> {
        val items = listOf(
            TendaItem(R.drawable.tenda_catedral_santiago_aliens, "Aliens peregrinando a Santiago de Compostela.", 1, SharedPreferencesKeys.ITEM_1),
            TendaItem(R.drawable.tenda_torre_de_hercules_parque_atraccions, "'Atraccións' na Torre de Hércules.", 0, SharedPreferencesKeys.ITEM_2),
            TendaItem(R.drawable.tenda_botafumeiro, "Botafumeiro.", 1, SharedPreferencesKeys.ITEM_3),
            TendaItem(R.drawable.tenda_catedral_santiago, "Catedral de Santiago.", 2, SharedPreferencesKeys.ITEM_4),
            TendaItem(R.drawable.tenda_torre_hercules, "Torre de Hércules.", 2, SharedPreferencesKeys.ITEM_5),
            TendaItem(R.drawable.tenda_natureza, "Disfruta da serenidade de unha paisaxe natural con un atardecer sobre as montañas.", 2, SharedPreferencesKeys.ITEM_6),
            TendaItem(R.drawable.tenda_cultura_pop, "Unha ilustración que presenta iconos famosos da cultura pop de películas e cómics.", 2, SharedPreferencesKeys.ITEM_7),
            TendaItem(R.drawable.tenda_ciencia_ficcion, "Unha cidade futurista en un planeta lonxano con tecnoloxía avanzada.", 2, SharedPreferencesKeys.ITEM_8),
            TendaItem(R.drawable.tenda_bosque_tropical, "Unha escena vibrante de unha selva tropical.", 2, SharedPreferencesKeys.ITEM_9),
            TendaItem(R.drawable.tenda_medieval, "Dragóns voando sobre un castelo medieval.", 2, SharedPreferencesKeys.ITEM_10),
            TendaItem(R.drawable.tenda_titanic, "Póster da película 'Titanic'.", 5, SharedPreferencesKeys.ITEM_11),
            TendaItem(R.drawable.tenda_matrix, "Póster da película 'Matrix'.", 5, SharedPreferencesKeys.ITEM_12),
            TendaItem(R.drawable.tenda_grease, "Póster da película 'Grease'.", 5, SharedPreferencesKeys.ITEM_13),
            TendaItem(R.drawable.tenda_in_time, "Póster da película 'In time'.", 5, SharedPreferencesKeys.ITEM_14),
            TendaItem(R.drawable.tenda_pulp_fiction, "Póster da película 'Pulp fiction'.", 5, SharedPreferencesKeys.ITEM_15),
            TendaItem(R.drawable.tenda_spiderman, "Póster da película 'Spiderman'.", 5, SharedPreferencesKeys.ITEM_16),
            TendaItem(R.drawable.tenda_star_wars, "Póster da película 'Star Wars'.", 5, SharedPreferencesKeys.ITEM_17),
            TendaItem(R.drawable.tenda_senhor_dos_aneis, "Póster da película 'O señor dos aneis'.", 5, SharedPreferencesKeys.ITEM_18),
            TendaItem(R.drawable.tenda_interstellar, "Póster da película 'Interstellar'.", 5, SharedPreferencesKeys.ITEM_19),
            TendaItem(R.drawable.tenda_toy_story, "Póster da película 'Toy story'.", 5, SharedPreferencesKeys.ITEM_20),
            TendaItem(R.drawable.tenda_rei_leon, "Póster da película 'O rei león'.", 5, SharedPreferencesKeys.ITEM_21),
            TendaItem(R.drawable.tenda_walle, "Póster da película 'Wall-e'.", 5, SharedPreferencesKeys.ITEM_22),
            TendaItem(R.drawable.tenda_harry_potter, "Póster da película 'Harry Potter'.", 5, SharedPreferencesKeys.ITEM_23),
            TendaItem(R.drawable.tenda_himym, "Póster da serie 'How I met your mother'.", 5, SharedPreferencesKeys.ITEM_24),
            TendaItem(R.drawable.tenda_simpsons, "Póster da película 'Los Simpsons'.", 5, SharedPreferencesKeys.ITEM_25),
            TendaItem(R.drawable.tenda_mona_lisa, "Cadro 'Gioconda' de Leonardo da Vinci con un toque moderno.", 3, SharedPreferencesKeys.ITEM_26),
            TendaItem(R.drawable.tenda_xoven_da_perla, "Cadro 'La joven de la perla' de Johannes Vermeer con un toque moderno.", 3, SharedPreferencesKeys.ITEM_27),
            TendaItem(R.drawable.tenda_noite_estrelada, "Cadro 'Noche estrellada' de Vincent van Gogh de con un toque moderno.", 3, SharedPreferencesKeys.ITEM_28),
            TendaItem(R.drawable.tenda_ultima_cea, "Cadro 'Última cena' de Leonardo da Vinci con un toque moderno.", 3, SharedPreferencesKeys.ITEM_29),
        )
        return items
    }

    fun getPurchasedItems(context: Context): List<PurchasedItem> {
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, MODE_PRIVATE)
        val purchasedItems = mutableListOf<PurchasedItem>()
        val items = getTendaItems()
        items.forEach { item ->
            val purchased = sharedPreferences.getBoolean(item.sharedPreferencesKey, false)
            if (purchased) {
                purchasedItems.add(PurchasedItem(item.image, item.description))
            }
        }
        return purchasedItems
    }

}

data class TendaItem(
    val image: Int,
    val description: String,
    val price: Int,
    val sharedPreferencesKey: String
)

data class PurchasedItem(
    val image: Int,
    val description: String
)