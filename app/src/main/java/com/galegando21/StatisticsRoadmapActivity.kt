package com.galegando21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setOnBackPressed

class StatisticsRoadmapActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var currentStreakStatistics : TextView
    private lateinit var longestStreakStatistics : TextView

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
    private lateinit var ruletaSorteScore: TextView
    private lateinit var explosionPalabrasScoreEasy: TextView
    private lateinit var explosionPalabrasScoreDificult: TextView
    private lateinit var atrapameSePodesScore: TextView
    private lateinit var adivinhaPersonaxeScore: TextView
    private lateinit var verdadeOuMentiraScore: TextView
    private lateinit var adivinhaEscudoScore: TextView
    private lateinit var anagramasScoreEasy: TextView
    private lateinit var anagramasScoreDificult: TextView
    private lateinit var probaVelocidadeScore: TextView
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
        ruletaSorteScore = findViewById(R.id.ruleta_sorte_statistics)
        explosionPalabrasScoreEasy = findViewById(R.id.explosion_palabras_statistics_easy)
        explosionPalabrasScoreDificult = findViewById(R.id.explosion_palabras_statistics_dificult)
        atrapameSePodesScore = findViewById(R.id.atrapame_se_podes_statistics)
        adivinhaPersonaxeScore = findViewById(R.id.adivinha_personaxe_statistics)
        verdadeOuMentiraScore = findViewById(R.id.verdade_ou_mentira_statistics)
        adivinhaEscudoScore = findViewById(R.id.adivinha_escudo_statistics)
        anagramasScoreEasy = findViewById(R.id.anagramas_statistics_easy)
        anagramasScoreDificult = findViewById(R.id.anagramas_statistics_dificult)
        probaVelocidadeScore = findViewById(R.id.proba_velocidade_statistics)
        aforcadoScoreEasy = findViewById(R.id.aforcado_statistics_easy)
        aforcadoScoreDificult = findViewById(R.id.aforcado_statistics_dificult)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)

        val currentStreak = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
        currentStreakStatistics.text = "$currentStreak"
        val longestStreak = sharedPreferences.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
        longestStreakStatistics.text = "$longestStreak"

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

        ruletaSorteScore.text = sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0).toString() + " €"

        explosionPalabrasScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY, 0).toString() + " puntos"
        explosionPalabrasScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT, 0).toString() + " puntos"

        atrapameSePodesScore.text = "Necesitaches " + sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0).toString() + " preguntas para subir 5 escalóns"

        adivinhaPersonaxeScore.text = "Necesitaches " + sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0).toString() + " pistas para adiviñar o personaxe"

        verdadeOuMentiraScore.text = sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0).toString() + " respostas correctas seguidas"

        adivinhaEscudoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0).toString() + " acertos"

        anagramasScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY, 0).toString() + " acertos"
        anagramasScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT, 0).toString() + " acertos"

        probaVelocidadeScore.text = sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0).toString() + " segundos"

        aforcadoScoreEasy.text = "Nivel fácil: " + sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY, 0).toString() + " acertos seguidos"
        aforcadoScoreDificult.text = "Nivel difícil: " + sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT, 0).toString() + " acertos seguidos"

    }
}