package com.galegando21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.galegando21.utils.setOnBackPressed

class StatisticsActivity : AppCompatActivity() {
    private lateinit var bannerFragment: BannerFragment

    private lateinit var currentStreakStatistics : TextView

    private lateinit var correctAnswersPasagalego : TextView
    private lateinit var errorAnswersPasagalego : TextView
    private lateinit var timePasagalego : TextView

    private lateinit var questionsNeededAtrapameSePodes : TextView
    private lateinit var cashAtrapaUnMillon : TextView
    private lateinit var verdadeOuMentiraScore : TextView
    private lateinit var adivinhaEscudoScore: TextView
    private lateinit var adivinhaAnoFotoScore: TextView
    private lateinit var agoraCaigoScore: TextView
    private lateinit var probaVelocidadeScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val sharedPreferencesOnboarding = getSharedPreferences("onboarding", MODE_PRIVATE)
        var username = sharedPreferencesOnboarding.getString("name", "Xogador")
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
        verdadeOuMentiraScore = findViewById(R.id.verdade_ou_mentira_correct_answers_statistics)
        adivinhaEscudoScore = findViewById(R.id.adivinha_escudo_correct_answers_statistics)
        adivinhaAnoFotoScore = findViewById(R.id.adivinha_ano_foto_correct_answers_statistics)
        agoraCaigoScore = findViewById(R.id.agora_caigo_correct_answers_statistics)
        probaVelocidadeScore = findViewById(R.id.proba_velocidade_time_statistics)

        val sharedPreferences = getSharedPreferences("statistics", MODE_PRIVATE)

        val currentStreak = sharedPreferences.getInt("current_streak", 0)
        currentStreakStatistics.text = "$currentStreak"

        correctAnswersPasagalego.text = sharedPreferences.getInt("pasagalego_correct_answers", 0).toString()
        errorAnswersPasagalego.text = sharedPreferences.getInt("pasagalego_error_answers", 0).toString()
        timePasagalego.text = sharedPreferences.getString("pasagalego_time", "00:00")

        val questionsNeededAtrapameSePodesValue = sharedPreferences.getInt("atrapame_se_podes_questions_needed", 0).toString()
        questionsNeededAtrapameSePodes.text = "Necesitaches un total de $questionsNeededAtrapameSePodesValue preguntas."

        val cashAtrapaUnMillonValue = sharedPreferences.getInt("atrapa_un_millon_max_cash", 0).toString()
        cashAtrapaUnMillon.text = "$cashAtrapaUnMillonValue€"

        verdadeOuMentiraScore.text = sharedPreferences.getInt("verdade_ou_mentira_max_score", 0).toString()

        adivinhaEscudoScore.text = sharedPreferences.getInt("adivinha_escudo_max_score", 0).toString()

        adivinhaAnoFotoScore.text = sharedPreferences.getInt("adivinha_ano_foto_max_score", 0).toString() + " pts."

        agoraCaigoScore.text = sharedPreferences.getInt("agora_caigo_max_score", 0).toString()

        probaVelocidadeScore.text = sharedPreferences.getInt("proba_velocidade_min_time", 0).toString()

    }
}