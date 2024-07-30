package com.galegando21

import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.galegando21.day01Pasagalego.PasagalegoInicioActivity
import com.galegando21.day02OndeEstan.OndeEstanInicioActivity
import com.galegando21.day03XogoPalabras.XogoPalabrasInicioActivity
import com.galegando21.day04AtrapaUnMillon.AtrapaUnMillonInicioActivity
import com.galegando21.day05AdivinhaAnoFoto.AdivinhaAnoFotoInicioActivity
import com.galegando21.day06SopaLetras.SopaLetrasInicioActivity
import com.galegando21.day07AgoraCaigo.AgoraCaigoInicioActivity
import com.galegando21.day08Conexions.ConexionsInicioActivity
import com.galegando21.day09RuletaDaSorte.RuletaDaSorteInicioActivity
import com.galegando21.day10ExplosionDePalabras.ExplosionPalabrasInicioActivity
import com.galegando21.day11AtrapameSePodes.AtrapameSePodesInicioActivity
import com.galegando21.day12AdivinhaPersonaxe.AdivinhaPersonaxeInicioActivity
import com.galegando21.day13VerdadeOuMentira.VerdadeOuMentiraInicioActivity
import com.galegando21.day14AdivinhaEscudo.AdivinhaEscudoInicioActivity
import com.galegando21.day15Wordle.WordleInicioActivity
import com.galegando21.day16Anagramas.AnagramasInicioActivity
import com.galegando21.day17ProbaVelocidade.ProbaVelocidadeInicioActivity
import com.galegando21.day18PalabrasEncadeadas.PalabrasEncadeadasInicioActivity
import com.galegando21.day19Aforcado.AforcadoInicioActivity
import com.galegando21.day20DebuxaEAdivinha.DebuxaEAdivinhaInicioActivity
import com.galegando21.utils.NUMBER_OF_DAYS
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.getChallenge
import com.galegando21.utils.setOnBackPressed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsRoadmapActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var currentStreakStatistics : TextView
    private lateinit var longestStreakStatistics : TextView

    private lateinit var helpImageButtonExperience: ImageButton
    private lateinit var totalExperienceStatistics: TextView
    private lateinit var maxDayExperienceStatistics: TextView
    private lateinit var maxDayExperienceStatisticsDate: TextView

    private lateinit var insigniaRacha7 : ImageView
    private lateinit var lockInsigniaRacha7 : ImageView
    private lateinit var insigniaRacha21 : ImageView
    private lateinit var lockInsigniaRacha21 : ImageView
    private lateinit var insigniaRacha50 : ImageView
    private lateinit var lockInsigniaRacha50 : ImageView
    private lateinit var insigniaRacha100 : ImageView
    private lateinit var lockInsigniaRacha100 : ImageView
    private lateinit var insigniaPasagalego : ImageView
    private lateinit var lockInsigniaPasagalego : ImageView
    private lateinit var insigniaOndeEstan: ImageView
    private lateinit var lockInsigniaOndeEstan: ImageView
    private lateinit var insigniaXogoPalabras : ImageView
    private lateinit var lockInsigniaXogoPalabras : ImageView
    private lateinit var insigniaAtrapaUnMillon : ImageView
    private lateinit var lockInsigniaAtrapaUnMillon : ImageView
    private lateinit var insigniaAdivinhaAnoFoto : ImageView
    private lateinit var lockInsigniaAdivinhaAnoFoto : ImageView
    private lateinit var insigniaSopaLetras: ImageView
    private lateinit var lockInsigniaSopaLetras: ImageView
    private lateinit var insigniaAgoraCaigo: ImageView
    private lateinit var lockInsigniaAgoraCaigo: ImageView
    private lateinit var insigniaConexions: ImageView
    private lateinit var lockInsigniaConexions: ImageView
    private lateinit var insigniaRuletaDaSorte: ImageView
    private lateinit var lockInsigniaRuletaDaSorte: ImageView
    private lateinit var insigniaExplosionPalabras: ImageView
    private lateinit var lockInsigniaExplosionPalabras: ImageView
    private lateinit var insigniaAtrapameSePodes: ImageView
    private lateinit var lockInsigniaAtrapameSePodes: ImageView
    private lateinit var insigniaAdivinhaPersonaxe: ImageView
    private lateinit var lockInsigniaAdivinhaPersonaxe: ImageView
    private lateinit var insigniaVerdadeOuMentira: ImageView
    private lateinit var lockInsigniaVerdadeOuMentira: ImageView
    private lateinit var insigniaAdivinhaEscudo: ImageView
    private lateinit var lockInsigniaAdivinhaEscudo: ImageView
    private lateinit var insigniaWordle: ImageView
    private lateinit var lockInsigniaWordle: ImageView
    private lateinit var insigniaAnagramas: ImageView
    private lateinit var lockInsigniaAnagramas: ImageView
    private lateinit var insigniaProbaVelocidade: ImageView
    private lateinit var lockInsigniaProbaVelocidade: ImageView
    private lateinit var insigniaPalabrasEncadeadas: ImageView
    private lateinit var lockInsigniaPalabrasEncadeadas: ImageView
    private lateinit var insigniaAforcado: ImageView
    private lateinit var lockInsigniaAforcado: ImageView
    private lateinit var insigniaExperiencia10k: ImageView
    private lateinit var lockInsigniaExperiencia10k: ImageView
    private lateinit var insigniaExperiencia50k: ImageView
    private lateinit var lockInsigniaExperiencia50k: ImageView
    private lateinit var insigniaExperiencia100k: ImageView
    private lateinit var lockInsigniaExperiencia100k: ImageView
    private lateinit var insigniaExperiencia1m: ImageView
    private lateinit var lockInsigniaExperiencia1m: ImageView

    private lateinit var obterDesafioPersonalizadoButton: Button

    private lateinit var correctAnswersPasagalegoDiccionarioFacil : TextView
    private lateinit var errorAnswersPasagalegoDiccionarioFacil : TextView
    private lateinit var timePasagalegoDiccionarioFacil : TextView
    private lateinit var correctAnswersPasagalegoDiccionario : TextView
    private lateinit var errorAnswersPasagalegoDiccionario : TextView
    private lateinit var timePasagalegoDiccionario : TextView
    private lateinit var correctAnswersPasagalegoOrixinal : TextView
    private lateinit var errorAnswersPasagalegoOrixinal : TextView
    private lateinit var timePasagalegoOrixinal : TextView

    private lateinit var ondeEstanScore: TextView
    private lateinit var xogoPalabrasScore: TextView
    private lateinit var atrapaUnMillonScore: TextView
    private lateinit var adivinhaAnoFotoScore: TextView
    private lateinit var sopaLetrasScore: TextView
    private lateinit var agoraCaigoScore: TextView
    private lateinit var conexionsScore: TextView
    private lateinit var ruletaSorteScore: TextView
    private lateinit var explosionPalabrasScoreEasy: TextView
    private lateinit var explosionPalabrasScoreDificult: TextView
    private lateinit var atrapameSePodesScore: TextView
    private lateinit var adivinhaPersonaxeScore: TextView
    private lateinit var verdadeOuMentiraScore: TextView
    private lateinit var adivinhaEscudoScore: TextView
    private lateinit var wordleScore: TextView
    private lateinit var anagramasScoreEasy: TextView
    private lateinit var anagramasScoreDificult: TextView
    private lateinit var probaVelocidadeScore: TextView
    private lateinit var palabrasEncadeadasScore: TextView
    private lateinit var aforcadoScoreEasy: TextView
    private lateinit var aforcadoScoreDificult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics_roadmap)

        val sharedPreferencesOnboarding = getSharedPreferences(SharedPreferencesKeys.ONBOARDING, MODE_PRIVATE)
        var username = sharedPreferencesOnboarding.getString(SharedPreferencesKeys.NOME, "Xogador")
        if (username?.isEmpty() == true) {
            username = "Xogador"
        }
        bannerFragment = supportFragmentManager.findFragmentById(R.id.bannerFragmentStatistics) as BannerFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            bannerFragment.setBannerText(getString(R.string.estadisticas_usuario, username))
        }.commit()

        setOnBackPressed(this, MainActivity::class.java)

        currentStreakStatistics = findViewById(R.id.current_streak_statistics)
        longestStreakStatistics = findViewById(R.id.longest_streak_statistics)

        helpImageButtonExperience = findViewById(R.id.helpButtonStatisticsExperience)
        totalExperienceStatistics = findViewById(R.id.total_experience_statistics)
        maxDayExperienceStatistics = findViewById(R.id.max_day_experience_statistics)
        maxDayExperienceStatisticsDate = findViewById(R.id.max_day_experience_statistics_day)

        insigniaRacha7 = findViewById(R.id.insignia_racha_7)
        lockInsigniaRacha7 = findViewById(R.id.lock_insignia_racha_7)
        insigniaRacha21 = findViewById(R.id.insignia_racha_21)
        lockInsigniaRacha21 = findViewById(R.id.lock_insignia_racha_21)
        insigniaRacha50 = findViewById(R.id.insignia_racha_50)
        lockInsigniaRacha50 = findViewById(R.id.lock_insignia_racha_50)
        insigniaRacha100 = findViewById(R.id.insignia_racha_100)
        lockInsigniaRacha100 = findViewById(R.id.lock_insignia_racha_100)
        insigniaPasagalego = findViewById(R.id.insignia_pasagalego)
        lockInsigniaPasagalego = findViewById(R.id.lock_insignia_pasagalego)
        insigniaOndeEstan = findViewById(R.id.insignia_onde_estan)
        lockInsigniaOndeEstan = findViewById(R.id.lock_insignia_onde_estan)
        insigniaXogoPalabras = findViewById(R.id.insignia_xogo_palabras)
        lockInsigniaXogoPalabras = findViewById(R.id.lock_insignia_xogo_palabras)
        insigniaAtrapaUnMillon = findViewById(R.id.insignia_atrapa_1m)
        lockInsigniaAtrapaUnMillon = findViewById(R.id.lock_insignia_atrapa_1m)
        insigniaAdivinhaAnoFoto = findViewById(R.id.insignia_adivinhar_ano_foto)
        lockInsigniaAdivinhaAnoFoto = findViewById(R.id.lock_insignia_adivinhar_ano_foto)
        insigniaSopaLetras = findViewById(R.id.insignia_sopa_letras)
        lockInsigniaSopaLetras = findViewById(R.id.lock_insignia_sopa_letras)
        insigniaAgoraCaigo = findViewById(R.id.insignia_agora_caigo)
        lockInsigniaAgoraCaigo = findViewById(R.id.lock_insignia_agora_caigo)
        insigniaConexions = findViewById(R.id.insignia_conexions)
        lockInsigniaConexions = findViewById(R.id.lock_insignia_conexions)
        insigniaRuletaDaSorte = findViewById(R.id.insignia_ruleta_sorte)
        lockInsigniaRuletaDaSorte = findViewById(R.id.lock_insignia_ruleta_sorte)
        insigniaExplosionPalabras = findViewById(R.id.insignia_explosion_palabras)
        lockInsigniaExplosionPalabras = findViewById(R.id.lock_insignia_explosion_palabras)
        insigniaAtrapameSePodes = findViewById(R.id.insignia_atrapame_se_podes)
        lockInsigniaAtrapameSePodes = findViewById(R.id.lock_insignia_atrapame_se_podes)
        insigniaAdivinhaPersonaxe = findViewById(R.id.insignia_adivinha_personaxe)
        lockInsigniaAdivinhaPersonaxe = findViewById(R.id.lock_insignia_adivinha_personaxe)
        insigniaVerdadeOuMentira = findViewById(R.id.insignia_verdade_ou_mentira)
        lockInsigniaVerdadeOuMentira = findViewById(R.id.lock_insignia_verdade_ou_mentira)
        insigniaAdivinhaEscudo = findViewById(R.id.insignia_adivinhar_escudo)
        lockInsigniaAdivinhaEscudo = findViewById(R.id.lock_insignia_adivinhar_escudo)
        insigniaWordle = findViewById(R.id.insignia_wordle)
        lockInsigniaWordle = findViewById(R.id.lock_insignia_wordle)
        insigniaAnagramas = findViewById(R.id.insignia_anagramas)
        lockInsigniaAnagramas = findViewById(R.id.lock_insignia_anagramas)
        insigniaProbaVelocidade = findViewById(R.id.insignia_proba_velocidade)
        lockInsigniaProbaVelocidade = findViewById(R.id.lock_insignia_proba_velocidade)
        insigniaPalabrasEncadeadas = findViewById(R.id.insignia_palabras_encadeadas)
        lockInsigniaPalabrasEncadeadas = findViewById(R.id.lock_insignia_palabras_encadeadas)
        insigniaAforcado = findViewById(R.id.insignia_aforcado)
        lockInsigniaAforcado = findViewById(R.id.lock_insignia_aforcado)
        insigniaExperiencia10k = findViewById(R.id.insignia_experiencia_10k)
        lockInsigniaExperiencia10k = findViewById(R.id.lock_insignia_experiencia_10k)
        insigniaExperiencia50k = findViewById(R.id.insignia_experiencia_50k)
        lockInsigniaExperiencia50k = findViewById(R.id.lock_insignia_experiencia_50k)
        insigniaExperiencia100k = findViewById(R.id.insignia_experiencia_100k)
        lockInsigniaExperiencia100k = findViewById(R.id.lock_insignia_experiencia_100k)
        insigniaExperiencia1m = findViewById(R.id.insignia_experiencia_1m)
        lockInsigniaExperiencia1m = findViewById(R.id.lock_insignia_experiencia_1m)

        obterDesafioPersonalizadoButton = findViewById(R.id.obter_desafio_personalizado_button)

        correctAnswersPasagalegoDiccionarioFacil = findViewById(R.id.correct_answers_pasagalego_statistics_diccionario_facil)
        errorAnswersPasagalegoDiccionarioFacil = findViewById(R.id.error_answers_pasagalego_statistics_diccionario_facil)
        timePasagalegoDiccionarioFacil = findViewById(R.id.time_pasagalego_statistics_diccionario_facil)
        correctAnswersPasagalegoDiccionario = findViewById(R.id.correct_answers_pasagalego_statistics_diccionario)
        errorAnswersPasagalegoDiccionario = findViewById(R.id.error_answers_pasagalego_statistics_diccionario)
        timePasagalegoDiccionario = findViewById(R.id.time_pasagalego_statistics_diccionario)
        correctAnswersPasagalegoOrixinal = findViewById(R.id.correct_answers_pasagalego_statistics_orixinal)
        errorAnswersPasagalegoOrixinal = findViewById(R.id.error_answers_pasagalego_statistics_orixinal)
        timePasagalegoOrixinal = findViewById(R.id.time_pasagalego_statistics_orixinal)

        ondeEstanScore = findViewById(R.id.onde_estan_time_statistics)
        xogoPalabrasScore = findViewById(R.id.xogo_palabras_statistics)
        atrapaUnMillonScore = findViewById(R.id.atrapa_un_millon_statistics)
        adivinhaAnoFotoScore = findViewById(R.id.adivinha_ano_foto_statistics)
        sopaLetrasScore = findViewById(R.id.sopa_letras_statistics)
        agoraCaigoScore = findViewById(R.id.agora_caigo_statistics)
        conexionsScore = findViewById(R.id.conexions_statistics)
        ruletaSorteScore = findViewById(R.id.ruleta_sorte_statistics)
        explosionPalabrasScoreEasy = findViewById(R.id.explosion_palabras_statistics_easy)
        explosionPalabrasScoreDificult = findViewById(R.id.explosion_palabras_statistics_dificult)
        atrapameSePodesScore = findViewById(R.id.atrapame_se_podes_statistics)
        adivinhaPersonaxeScore = findViewById(R.id.adivinha_personaxe_statistics)
        verdadeOuMentiraScore = findViewById(R.id.verdade_ou_mentira_statistics)
        adivinhaEscudoScore = findViewById(R.id.adivinha_escudo_statistics)
        wordleScore = findViewById(R.id.wordle_statistics)
        anagramasScoreEasy = findViewById(R.id.anagramas_statistics_easy)
        anagramasScoreDificult = findViewById(R.id.anagramas_statistics_dificult)
        probaVelocidadeScore = findViewById(R.id.proba_velocidade_statistics)
        palabrasEncadeadasScore = findViewById(R.id.palabras_encadeadas_statistics)
        aforcadoScoreEasy = findViewById(R.id.aforcado_statistics_easy)
        aforcadoScoreDificult = findViewById(R.id.aforcado_statistics_dificult)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)

        val currentStreak = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
        currentStreakStatistics.text = "$currentStreak"
        val longestStreak = sharedPreferences.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
        longestStreakStatistics.text = "$longestStreak"

        helpImageButtonExperience.setOnClickListener {
            showHelpDialogExperience()
        }
        totalExperienceStatistics.text = sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString() + " pts"
        maxDayExperienceStatistics.text = sharedPreferences.getInt(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS, 0).toString() + " pts"
        maxDayExperienceStatisticsDate.text = sharedPreferences.getString(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS_DATE, "")
        if (maxDayExperienceStatisticsDate.text.isEmpty()) {
            maxDayExperienceStatisticsDate.visibility = View.GONE
        }

        correctAnswersPasagalegoDiccionarioFacil.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY_EASY, 0).toString()
        errorAnswersPasagalegoDiccionarioFacil.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY_EASY, 0).toString()
        timePasagalegoDiccionarioFacil.text = sharedPreferences.getString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY_EASY, "00:00")
        correctAnswersPasagalegoDiccionario.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY, 0).toString()
        errorAnswersPasagalegoDiccionario.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY, 0).toString()
        timePasagalegoDiccionario.text = sharedPreferences.getString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY, "00:00")
        correctAnswersPasagalegoOrixinal.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_ORIXINAL, 0).toString()
        errorAnswersPasagalegoOrixinal.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_ORIXINAL, 0).toString()
        timePasagalegoOrixinal.text = sharedPreferences.getString(SharedPreferencesKeys.PASAGALEGO_TIME_ORIXINAL, "00:00")

        ondeEstanScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0).toString()+" segundos"

        xogoPalabrasScore.text = sharedPreferences.getFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, 0F).toString() + " % palabras encontradas"

        atrapaUnMillonScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0).toString() + " €"

        adivinhaAnoFotoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0).toString() + " puntos"

        sopaLetrasScore.text = sharedPreferences.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0).toString() + " puntos"

        agoraCaigoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0).toString() + " respostas correctas"

        conexionsScore.text = "Na túa mellor partida quedaronche " + sharedPreferences.getInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, 0).toString() + " vidas restantes"

        ruletaSorteScore.text = sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0).toString() + " €"

        explosionPalabrasScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY, 0).toString() + " puntos"
        explosionPalabrasScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT, 0).toString() + " puntos"

        atrapameSePodesScore.text = "Necesitaches " + sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0).toString() + " preguntas para subir 5 escalóns"

        adivinhaPersonaxeScore.text = "Necesitaches " + sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0).toString() + " pistas para adiviñar o personaxe"

        verdadeOuMentiraScore.text = sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0).toString() + " respostas correctas seguidas"

        adivinhaEscudoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0).toString() + " acertos"

        wordleScore.text = "Acertaches " + sharedPreferences.getInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, 0).toString() + " palabras na túa mellor partida"

        anagramasScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY, 0).toString() + " acertos"
        anagramasScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT, 0).toString() + " acertos"

        probaVelocidadeScore.text = sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0).toString() + " segundos"

        palabrasEncadeadasScore.text = "Encadeaches " + sharedPreferences.getInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, 0).toString() + " palabras"

        aforcadoScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY, 0).toString() + " acertos seguidos"
        aforcadoScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT, 0).toString() + " acertos seguidos"

        setInsignias()

        obterDesafioPersonalizadoButton.setOnClickListener {
            obterDesafioPersonalizado()
        }

    }

    private fun showHelpDialogExperience() {
        AlertDialog.Builder(this)
            .setTitle("Como se obteñen os puntos de experiencia?")
            .setMessage("En cada xogo, dependendo do teu desempeño, recibirás puntos de experiencia. \n\nAdemáis, recibirás 1 punto de experiencia adicional por cada día de racha actual ao finalizar cada xogo. \n\nDurante a Hora Dourada (de 19:00 a 20:00) recibirás o dobre de puntos de experiencia.")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setInsignias() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f) // Escala de grises
        val filter = ColorMatrixColorFilter(colorMatrix)

        val longestStreak = sharedPreferences.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0).toString().toInt()
        if (longestStreak <= 7) {
            insigniaRacha7.colorFilter = filter
            lockInsigniaRacha7.visibility = View.VISIBLE
        }
        if (longestStreak <= 21) {
            insigniaRacha21.colorFilter = filter
            lockInsigniaRacha21.visibility = View.VISIBLE
        }
        if (longestStreak <= 50) {
            insigniaRacha50.colorFilter = filter
            lockInsigniaRacha50.visibility = View.VISIBLE
        }
        if (longestStreak <= 100) {
            insigniaRacha100.colorFilter = filter
            lockInsigniaRacha100.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY, 0).toString().toInt() != 23) {
            insigniaPasagalego.colorFilter = filter
            lockInsigniaPasagalego.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0).toString().toInt() > 20 || sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0).toString().toInt() == 0) {
            insigniaOndeEstan.colorFilter = filter
            lockInsigniaOndeEstan.visibility = View.VISIBLE
        }

        if (sharedPreferences.getFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, 0F).toString().toFloat() < 100F) {
            insigniaXogoPalabras.colorFilter = filter
            lockInsigniaXogoPalabras.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0).toString().toInt() < 1000000) {
            insigniaAtrapaUnMillon.colorFilter = filter
            lockInsigniaAtrapaUnMillon.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0).toString().toInt() < 50) {
            insigniaAdivinhaAnoFoto.colorFilter = filter
            lockInsigniaAdivinhaAnoFoto.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0).toString().toInt() < 500) {
            insigniaSopaLetras.colorFilter = filter
            lockInsigniaSopaLetras.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0).toString().toInt() < 50) {
            insigniaAgoraCaigo.colorFilter = filter
            lockInsigniaAgoraCaigo.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, 0).toString().toInt() < 5) {
            insigniaConexions.colorFilter = filter
            lockInsigniaConexions.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0).toString().toInt() < 4000 || sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0).toString().toInt() == 0) {
            insigniaRuletaDaSorte.colorFilter = filter
            lockInsigniaRuletaDaSorte.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT, 0).toString().toInt() < 100) {
            insigniaExplosionPalabras.colorFilter = filter
            lockInsigniaExplosionPalabras.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0).toString().toInt() < 5) {
            insigniaAtrapameSePodes.colorFilter = filter
            lockInsigniaAtrapameSePodes.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0).toString().toInt() > 2 || sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0).toString().toInt() == 0) {
            insigniaAdivinhaPersonaxe.colorFilter = filter
            lockInsigniaAdivinhaPersonaxe.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0).toString().toInt() < 30) {
            insigniaVerdadeOuMentira.colorFilter = filter
            lockInsigniaVerdadeOuMentira.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0).toString().toInt() < 10) {
            insigniaAdivinhaEscudo.colorFilter = filter
            lockInsigniaAdivinhaEscudo.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, 0).toString().toInt() < 10) {
            insigniaWordle.colorFilter = filter
            lockInsigniaWordle.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT, 0).toString().toInt() < 20) {
            insigniaAnagramas.colorFilter = filter
            lockInsigniaAnagramas.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0).toString().toInt() < 40 || sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0).toString().toInt() == 0) {
            insigniaProbaVelocidade.colorFilter = filter
            lockInsigniaProbaVelocidade.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, 0).toString().toInt() < 20) {
            insigniaPalabrasEncadeadas.colorFilter = filter
            lockInsigniaPalabrasEncadeadas.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT, 0).toString().toInt() < 10) {
            insigniaAforcado.colorFilter = filter
            lockInsigniaAforcado.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString().toInt() < 10000) {
            insigniaExperiencia10k.colorFilter = filter
            lockInsigniaExperiencia10k.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString().toInt() < 50000) {
            insigniaExperiencia50k.colorFilter = filter
            lockInsigniaExperiencia50k.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString().toInt() < 100000) {
            insigniaExperiencia100k.colorFilter = filter
            lockInsigniaExperiencia100k.visibility = View.VISIBLE
        }

        if (sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString().toInt() < 1000000) {
            insigniaExperiencia1m.colorFilter = filter
            lockInsigniaExperiencia1m.visibility = View.VISIBLE
        }

    }

    private fun obterDesafioPersonalizado() {
        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        val experience = sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString().toInt()

        CoroutineScope(Dispatchers.Main).launch {
            val challenge = getChallenge(experience)
            val builder = AlertDialog.Builder(this@StatisticsRoadmapActivity)
                .setTitle("Desafío personalizado")
                .setMessage("$challenge")
                .setPositiveButton("Cerrar") { dialog, _ ->
                    dialog.dismiss()
                }

            setChallengeButton(challenge, builder)

            builder.show()
        }
    }

    private fun setChallengeButton(challenge: String, builder: AlertDialog.Builder) {
        val sharedPreferencesButtons = getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, Context.MODE_PRIVATE)
        val unlockedButtonCount = sharedPreferencesButtons.getInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 0)

        when {
            challenge.contains("Pasagalego") -> {
                setButton(builder, PasagalegoInicioActivity::class.java)
            }
            challenge.contains("Onde están?") && unlockedButtonCount >= 2 -> {
                setButton(builder, OndeEstanInicioActivity::class.java)
            }
            challenge.contains("Xogo de Palabras") && unlockedButtonCount >= 3 -> {
                setButton(builder, XogoPalabrasInicioActivity::class.java)
            }
            challenge.contains("Atrapa un millón") && unlockedButtonCount >= 4 -> {
                setButton(builder, AtrapaUnMillonInicioActivity::class.java)
            }
            challenge.contains("Adiviña o ano da foto") && unlockedButtonCount >= 5 -> {
                setButton(builder, AdivinhaAnoFotoInicioActivity::class.java)
            }
            challenge.contains("Sopa de letras") && unlockedButtonCount >= 6 -> {
                setButton(builder, SopaLetrasInicioActivity::class.java)
            }
            challenge.contains("Agora Caio") && unlockedButtonCount >= 7 -> {
                setButton(builder, AgoraCaigoInicioActivity::class.java)
            }
            challenge.contains("Conexións") && unlockedButtonCount >= 8 -> {
                setButton(builder, ConexionsInicioActivity::class.java)
            }
            challenge.contains("Ruleta da sorte") && unlockedButtonCount >= 9 -> {
                setButton(builder, RuletaDaSorteInicioActivity::class.java)
            }
            challenge.contains("Explosión de palabras") && unlockedButtonCount >= 10 -> {
                setButton(builder, ExplosionPalabrasInicioActivity::class.java)
            }
            challenge.contains("Atrápame se podes") && unlockedButtonCount >= 11 -> {
                setButton(builder, AtrapameSePodesInicioActivity::class.java)
            }
            challenge.contains("Adiviña o personaxe") && unlockedButtonCount >= 12 -> {
                setButton(builder, AdivinhaPersonaxeInicioActivity::class.java)
            }
            challenge.contains("Verdade ou Mentira") && unlockedButtonCount >= 13 -> {
                setButton(builder, VerdadeOuMentiraInicioActivity::class.java)
            }
            challenge.contains("Adiviña o escudo") && unlockedButtonCount >= 14 -> {
                setButton(builder, AdivinhaEscudoInicioActivity::class.java)
            }
            challenge.contains("Wordle") && unlockedButtonCount >= 15 -> {
                setButton(builder, WordleInicioActivity::class.java)
            }
            challenge.contains("Anagramas") && unlockedButtonCount >= 16 -> {
                setButton(builder, AnagramasInicioActivity::class.java)
            }
            challenge.contains("Proba de velocidade") && unlockedButtonCount >= 17 -> {
                setButton(builder, ProbaVelocidadeInicioActivity::class.java)
            }
            challenge.contains("Palabras encadeadas") && unlockedButtonCount >= 18 -> {
                setButton(builder, PalabrasEncadeadasInicioActivity::class.java)
            }
            challenge.contains("Aforcado") && unlockedButtonCount >= 19 -> {
                setButton(builder, AforcadoInicioActivity::class.java)
            }
            challenge.contains("Debuxa e adiviña") && unlockedButtonCount >= 20 -> {
                setButton(builder, DebuxaEAdivinhaInicioActivity::class.java)
            }
        }
    }

    private fun setButton(builder: AlertDialog.Builder, activityClass: Class<*>) {
        builder.setNeutralButton("Xogar") { _, _ ->
            val intent = Intent(this@StatisticsRoadmapActivity, activityClass)
            startActivity(intent)
        }
    }
}