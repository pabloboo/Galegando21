package com.galegando21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.setOnBackPressed

class StatisticsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var currentStreakStatistics : TextView

    private lateinit var correctAnswersPasagalego : TextView
    private lateinit var errorAnswersPasagalego : TextView
    private lateinit var timePasagalego : TextView

    private lateinit var questionsNeededAtrapameSePodes : TextView
    private lateinit var cashAtrapaUnMillon : TextView
    private lateinit var aforcadoMaxStreak : TextView
    private lateinit var verdadeOuMentiraScore : TextView
    private lateinit var adivinhaEscudoScore: TextView
    private lateinit var adivinhaAnoFotoScore: TextView
    private lateinit var agoraCaigoScore: TextView
    private lateinit var probaVelocidadeScore: TextView
    private lateinit var ruletaDaSorteCash: TextView
    private lateinit var ondeEstanScore: TextView
    private lateinit var sopaLetrasScore: TextView
    private lateinit var anagramasScore: TextView
    private lateinit var adivinhaPersonaxeScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

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
        correctAnswersPasagalego = findViewById(R.id.correct_answers_pasagalego_statistics)
        errorAnswersPasagalego = findViewById(R.id.error_answers_pasagalego_statistics)
        timePasagalego = findViewById(R.id.time_pasagalego_statistics)
        questionsNeededAtrapameSePodes = findViewById(R.id.questions_needed_atrapame_se_podes_statistics)
        cashAtrapaUnMillon = findViewById(R.id.cash_atrapa_un_millon_statistics)
        aforcadoMaxStreak = findViewById(R.id.aforcado_streak_statistics)

        verdadeOuMentiraScore = findViewById(R.id.verdade_ou_mentira_correct_answers_statistics)
        adivinhaEscudoScore = findViewById(R.id.adivinha_escudo_correct_answers_statistics)
        adivinhaAnoFotoScore = findViewById(R.id.adivinha_ano_foto_correct_answers_statistics)
        agoraCaigoScore = findViewById(R.id.agora_caigo_correct_answers_statistics)
        probaVelocidadeScore = findViewById(R.id.proba_velocidade_time_statistics)
        ruletaDaSorteCash = findViewById(R.id.ruleta_da_sorte_cash_statistics)
        ondeEstanScore = findViewById(R.id.onde_estan_time_statistics)
        sopaLetrasScore = findViewById(R.id.sopa_letras_score_statistics)
        anagramasScore = findViewById(R.id.anagramas_score_statistics)
        adivinhaPersonaxeScore = findViewById(R.id.adivinha_personaxe_score_statistics)

        val sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)

        val currentStreak = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
        currentStreakStatistics.text = "$currentStreak"

        correctAnswersPasagalego.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS, 0).toString()
        errorAnswersPasagalego.text = sharedPreferences.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS, 0).toString()
        timePasagalego.text = sharedPreferences.getString(SharedPreferencesKeys.PASAGALEGO_TIME, "00:00")

        val questionsNeededAtrapameSePodesValue = sharedPreferences.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0).toString()
        questionsNeededAtrapameSePodes.text = "Necesitaches un total de $questionsNeededAtrapameSePodesValue preguntas."

        val cashAtrapaUnMillonValue = sharedPreferences.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0).toString()
        cashAtrapaUnMillon.text = "$cashAtrapaUnMillonValue€"

        val aforcadoMaxStreakValue = sharedPreferences.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK, 0).toString()
        aforcadoMaxStreak.text = "Racha: $aforcadoMaxStreakValue"

        verdadeOuMentiraScore.text = sharedPreferences.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0).toString()

        adivinhaEscudoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0).toString()

        adivinhaAnoFotoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0).toString() + " pts."

        agoraCaigoScore.text = sharedPreferences.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0).toString()

        probaVelocidadeScore.text = sharedPreferences.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0).toString()

        ruletaDaSorteCash.text = sharedPreferences.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0).toString()

        ondeEstanScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0).toString()

        sopaLetrasScore.text = sharedPreferences.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0).toString()

        anagramasScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE, 0).toString()

        adivinhaPersonaxeScore.text = sharedPreferences.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0).toString()

    }
}