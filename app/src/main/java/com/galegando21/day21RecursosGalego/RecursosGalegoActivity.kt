package com.galegando21.day21RecursosGalego

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.Evento
import com.galegando21.utils.getEventos
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed
import com.galegando21.utils.updateCurrentStreak
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecursosGalegoActivity : AppCompatActivity() {
    private lateinit var spotifyIcon : ImageView
    private lateinit var eventosButton: Button
    private lateinit var aGalegaWebIcon: ImageView
    private lateinit var aGalegaMobileIcon: ImageView
    private lateinit var landRoberIcon: ImageView
    private lateinit var maliciaNoticiasIcon: ImageView
    private lateinit var eraVistoIcon: ImageView
    private lateinit var estacheBoIcon: ImageView
    private lateinit var atrapameSePodesIcon: ImageView
    private lateinit var xabarinClubIcon: ImageView
    private lateinit var luarIcon: ImageView

    private var currentEventIndex = 0
    private var eventos: List<Evento> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursos_galego)

        spotifyIcon = findViewById(R.id.spotify_icon)
        eventosButton = findViewById(R.id.eventos_button)
        aGalegaWebIcon = findViewById(R.id.agalega_web_icon)
        aGalegaMobileIcon = findViewById(R.id.agalega_mobile_icon)
        landRoberIcon = findViewById(R.id.land_rober_icon)
        maliciaNoticiasIcon = findViewById(R.id.malicia_noticias_icon)
        eraVistoIcon = findViewById(R.id.era_visto_icon)
        estacheBoIcon = findViewById(R.id.estache_bo_icon)
        atrapameSePodesIcon = findViewById(R.id.atrapame_se_podes_icon)
        xabarinClubIcon = findViewById(R.id.xabarin_club_icon)
        luarIcon = findViewById(R.id.luar_icon)

        setBanner(this, R.string.dias_co_galego_e_mais)

        spotifyIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0ArqOLd6zTZpVqtTSXDTZC?si=4pA47GITQ8af7kJu3thHVQ")
            startActivity(openURL)
        }

        eventosButton.setOnClickListener {
            obterEventos()
        }

        aGalegaWebIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/")
            startActivity(openURL)
        }

        aGalegaMobileIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://play.google.com/store/apps/details?id=com.interactvty.agalega&hl=es_419")
            startActivity(openURL)
        }

        landRoberIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/16615-land-rober-tunai-show")
            startActivity(openURL)
        }

        maliciaNoticiasIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/16805-malicia-noticias")
            startActivity(openURL)
        }

        eraVistoIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/15252-era-visto")
            startActivity(openURL)
        }

        estacheBoIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/16806-estache-bo")
            startActivity(openURL)
        }

        atrapameSePodesIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/16748-atrapame-se-podes")
            startActivity(openURL)
        }

        xabarinClubIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://xabarin.gal/")
            startActivity(openURL)
        }

        luarIcon.setOnClickListener {
            updateCurrentStreak(this)
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://agalega.gal/videos/category/16804-luar")
            startActivity(openURL)
        }

        setOnBackPressed(this, MainActivity::class.java)
    }

    private fun obterEventos() {
        CoroutineScope(Dispatchers.Main).launch {
            val fetchedEventos = getEventos()
            if (fetchedEventos.isNotEmpty()) {
                eventos = fetchedEventos
                showEventDialog(eventos[currentEventIndex])
            }
        }
    }

    private fun showEventDialog(evento: Evento) {
        val builder = AlertDialog.Builder(this)
            .setTitle(evento.nome)
            .setMessage("Data: ${evento.data}\n\nUbicación: ${evento.ubicacion}\n\nDescrición: ${evento.descricion}")
            .setPositiveButton("Seguinte") { _, _ ->
                currentEventIndex = (currentEventIndex + 1) % eventos.size
                showEventDialog(eventos[currentEventIndex])
            }
            .setNegativeButton("Anterior") { _, _ ->
                currentEventIndex = (currentEventIndex - 1 + eventos.size) % eventos.size
                showEventDialog(eventos[currentEventIndex])
            }
        builder.show()
    }
}