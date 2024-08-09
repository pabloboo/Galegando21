package com.galegando21.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.exception.AuthRestException
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.Serializable

val supabase = createSupabaseClient(
    supabaseUrl = "https://hddnmaaerppnfiiadiiw.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhkZG5tYWFlcnBwbmZpaWFkaWl3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjMxMDgyODcsImV4cCI6MjAzODY4NDI4N30.NjVcMV4Z1nMDYzfLVdGE8ElUeMOstz1QHw_3OBGebNs"
) {
    install(Postgrest)
    install(Auth)
}

suspend fun signUp(context: Context, email: String, password: String) {
    try {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        Toast.makeText(context,"Rexistrado correctamente", Toast.LENGTH_SHORT).show()
        saveToken(context)
    } catch (e: AuthRestException) {
        if (e.message == "Email rate limit exceeded") {
            Toast.makeText(context,"Error rexistrandote, límite de correos enviados superado", Toast.LENGTH_SHORT).show()
        } else if (e.message == "User already registered") {
            Toast.makeText(context,"Error rexistrandote, usuario xa rexistrado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Error rexistrandote, intentao máis tarde", Toast.LENGTH_SHORT).show()
        }
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun login(context: Context, email: String, password: String) {
    try {
        val user = supabase.auth.currentUserOrNull()
        if (user != null) {
            Log.d("SupabaseFunctions", "login: Already logged in")
            supabase.auth.signOut()
        }
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        Toast.makeText(context,"Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
        saveToken(context)
    } catch (e: BadRequestRestException) {
        Toast.makeText(context,"Error iniciando sesión, credenciais inválidas", Toast.LENGTH_SHORT).show()
        Log.d("SupabaseFunctions", "Error signing up: ${e.message}")
        e.printStackTrace()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing in: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun signOut(context: Context) {
    try {
        supabase.auth.signOut()
        Toast.makeText(context,"Sesión pechada correctamente", Toast.LENGTH_SHORT).show()
        val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(SharedPreferencesKeys.ACCESS_TOKEN)
        editor.apply()
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error signing out: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun isLoggedIn(context: Context): Boolean {
    try {
        val token = getToken(context)
        if (token.isNullOrEmpty()) {
            Log.d("SupabaseFunctions", "Not logged in")
            return false
        } else {
            supabase.auth.refreshCurrentSession()
            saveToken(context)
            Log.d("SupabaseFunctions", "Already logged in")
            return true
        }
    } catch (e: Exception) {
        Log.d("SupabaseFunctions", "Error checking if logged in: ${e.message}")
        e.printStackTrace()
    }
    return false
}

private fun saveToken(context: Context) {
    val token = supabase.auth.currentAccessTokenOrNull()
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(SharedPreferencesKeys.ACCESS_TOKEN, token)
    editor.apply()
}

private fun getToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.SUPABASE, Context.MODE_PRIVATE)
    return sharedPreferences.getString(SharedPreferencesKeys.ACCESS_TOKEN, null)
}

// Funciones para gardar e obter datos de SharedPreferences en Supabase
suspend fun storeSharedPreferencesInSupabase(context: Context) {
    val userId = supabase.auth.currentUserOrNull()?.id ?: return

    val sharedPreferencesStatistics = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, Context.MODE_PRIVATE)
    val longestStreak = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
    val experiencePoints = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0)
    val maxDayExperiencePoints = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS, 0)
    val maxDayExperiencePointsDate = sharedPreferencesStatistics.getString(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS_DATE, "")
    val numberOfBadges = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.NUMBER_OF_BADGES, 0)
    val pasagalegoCorrectAnswersDictionaryEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY_EASY, 0)
    val pasagalegoErrorAnswersDictionaryEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY_EASY, 0)
    val pasagalegoTimeDictionaryEasy = sharedPreferencesStatistics.getString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY_EASY, "00:00")
    val pasagalegoCorrectAnswersDictionary = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY, 0)
    val pasagalegoErrorAnswersDictionary = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY, 0)
    val pasagalegoTimeDictionary = sharedPreferencesStatistics.getString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY, "00:00")
    val pasagalegoCorrectAnswersOrixinal = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_ORIXINAL, 0)
    val pasagalegoErrorAnswersOrixinal = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_ORIXINAL, 0)
    val pasagalegoTimeOrixinal = sharedPreferencesStatistics.getString(SharedPreferencesKeys.PASAGALEGO_TIME_ORIXINAL, "00:00")
    val atrapameSePodesQuestionsNeeded = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, 0)
    val atrapaUnMillonMaxCash = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, 0)
    val aforcadoMaxStreakEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY, 0)
    val aforcadoMaxStreakDificult = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT, 0)
    val verdadeOuMentiraMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, 0)
    val adivinhaEscudoMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, 0)
    val adivinhaAnoFotoMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, 0)
    val agoraCaigoMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, 0)
    val probaVelocidadeMinTime = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, 0)
    val ruletaDaSorteMaxCash = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, 0)
    val ondeEstanMinTime = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, 0)
    val sopaLetrasMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, 0)
    val anagramasMaxScoreEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY, 0)
    val anagramasMaxScoreDificult = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT, 0)
    val adivinhaPersonaxeMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, 0)
    val xogoPalabrasMaxScore = sharedPreferencesStatistics.getFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, 0F)
    val explosionPalabrasMaxScoreEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY, 0)
    val explosionPalabrasMaxScoreDificult = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT, 0)
    val wordleMaxStreak = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, 0)
    val conexionsMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, 0)
    val palabrasEncadeadasMaxScore = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, 0)
    val briscaMaxScoreEasy = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.BRISCA_MAX_SCORE_EASY, 0)
    val briscaMaxScoreDificult = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.BRISCA_MAX_SCORE_DIFICULT, 0)

    val sharedPreferencesVirtualCoins = context.getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, Context.MODE_PRIVATE)
    val coins = sharedPreferencesVirtualCoins.getInt(SharedPreferencesKeys.COINS, 0)
    val item1 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_1, false)
    val item2 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_2, false)
    val item3 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_3, false)
    val item4 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_4, false)
    val item5 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_5, false)
    val item6 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_6, false)
    val item7 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_7, false)
    val item8 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_8, false)
    val item9 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_9, false)
    val item10 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_10, false)
    val item11 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_11, false)
    val item12 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_12, false)
    val item13 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_13, false)
    val item14 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_14, false)
    val item15 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_15, false)
    val item16 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_16, false)
    val item17 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_17, false)
    val item18 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_18, false)
    val item19 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_19, false)
    val item20 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_20, false)
    val item21 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_21, false)
    val item22 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_22, false)
    val item23 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_23, false)
    val item24 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_24, false)
    val item25 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_25, false)
    val item26 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_26, false)
    val item27 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_27, false)
    val item28 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_28, false)
    val item29 = sharedPreferencesVirtualCoins.getBoolean(SharedPreferencesKeys.ITEM_29, false)

    try {
        supabase.from("Datos")
            .upsert(
                SharedPreferencesData(
                    userId = userId,
                    longestStreak = longestStreak,
                    experiencePoints = experiencePoints,
                    maxDayExperiencePoints = maxDayExperiencePoints,
                    maxDayExperiencePointsDate = maxDayExperiencePointsDate!!,
                    numberOfBadges = numberOfBadges,
                    pasagalegoCorrectAnswersDictionaryEasy = pasagalegoCorrectAnswersDictionaryEasy,
                    pasagalegoErrorAnswersDictionaryEasy = pasagalegoErrorAnswersDictionaryEasy,
                    pasagalegoTimeDictionaryEasy = pasagalegoTimeDictionaryEasy!!,
                    pasagalegoCorrectAnswersDictionary = pasagalegoCorrectAnswersDictionary,
                    pasagalegoErrorAnswersDictionary = pasagalegoErrorAnswersDictionary,
                    pasagalegoTimeDictionary = pasagalegoTimeDictionary!!,
                    pasagalegoCorrectAnswersOrixinal = pasagalegoCorrectAnswersOrixinal,
                    pasagalegoErrorAnswersOrixinal = pasagalegoErrorAnswersOrixinal,
                    pasagalegoTimeOrixinal = pasagalegoTimeOrixinal!!,
                    atrapameSePodesQuestionsNeeded = atrapameSePodesQuestionsNeeded,
                    atrapaUnMillonMaxCash = atrapaUnMillonMaxCash,
                    aforcadoMaxStreakEasy = aforcadoMaxStreakEasy,
                    aforcadoMaxStreakDificult = aforcadoMaxStreakDificult,
                    verdadeOuMentiraMaxScore = verdadeOuMentiraMaxScore,
                    adivinhaEscudoMaxScore = adivinhaEscudoMaxScore,
                    adivinhaAnoFotoMaxScore = adivinhaAnoFotoMaxScore,
                    agoraCaigoMaxScore = agoraCaigoMaxScore,
                    probaVelocidadeMinTime = probaVelocidadeMinTime,
                    ruletaDaSorteMaxCash = ruletaDaSorteMaxCash,
                    ondeEstanMinTime = ondeEstanMinTime,
                    sopaLetrasMaxScore = sopaLetrasMaxScore,
                    anagramasMaxScoreEasy = anagramasMaxScoreEasy,
                    anagramasMaxScoreDificult = anagramasMaxScoreDificult,
                    adivinhaPersonaxeMaxScore = adivinhaPersonaxeMaxScore,
                    xogoPalabrasMaxScore = xogoPalabrasMaxScore,
                    explosionPalabrasMaxScoreEasy = explosionPalabrasMaxScoreEasy,
                    explosionPalabrasMaxScoreDificult = explosionPalabrasMaxScoreDificult,
                    wordleMaxStreak = wordleMaxStreak,
                    conexionsMaxScore = conexionsMaxScore,
                    palabrasEncadeadasMaxScore = palabrasEncadeadasMaxScore,
                    briscaMaxScoreEasy = briscaMaxScoreEasy,
                    briscaMaxScoreDificult = briscaMaxScoreDificult,
                    coins = coins,
                    item1 = item1,
                    item2 = item2,
                    item3 = item3,
                    item4 = item4,
                    item5 = item5,
                    item6 = item6,
                    item7 = item7,
                    item8 = item8,
                    item9 = item9,
                    item10 = item10,
                    item11 = item11,
                    item12 = item12,
                    item13 = item13,
                    item14 = item14,
                    item15 = item15,
                    item16 = item16,
                    item17 = item17,
                    item18 = item18,
                    item19 = item19,
                    item20 = item20,
                    item21 = item21,
                    item22 = item22,
                    item23 = item23,
                    item24 = item24,
                    item25 = item25,
                    item26 = item26,
                    item27 = item27,
                    item28 = item28,
                    item29 = item29
                )
            )
        Toast.makeText(context,"Datos gardados correctamente", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context,"Erro gardando datos, intentao máis tarde", Toast.LENGTH_SHORT).show()
        Log.d("SupabaseFunctions", "Error storing in Supabase: ${e.message}")
        e.printStackTrace()
    }
}

suspend fun replaceSharedPreferencesWithSupabase(context: Context) {
    val userId = supabase.auth.currentUserOrNull()?.id ?: return

    val sharedPreferencesSupabase = supabase
        .from("Datos")
        .select {
            filter {
                eq("userId", userId)
            }
        }
        .decodeSingle<SharedPreferencesData>()

    if (sharedPreferencesSupabase == null) {
        Toast.makeText(context,"Erro obtendo datos, intentao máis tarde", Toast.LENGTH_SHORT).show()
        return
    }

    val sharedPreferencesUnlockedButtons = context.getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, Context.MODE_PRIVATE)
    val editorUnlockedButtons = sharedPreferencesUnlockedButtons.edit()
    editorUnlockedButtons.putInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 21)
    editorUnlockedButtons.apply()

    val sharedPreferencesStatistics = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, Context.MODE_PRIVATE)
    val editorStatistics = sharedPreferencesStatistics.edit()

    editorStatistics.putInt(SharedPreferencesKeys.LONGEST_STREAK, sharedPreferencesSupabase.longestStreak)
    editorStatistics.putInt(SharedPreferencesKeys.EXPERIENCE_POINTS, sharedPreferencesSupabase.experiencePoints)
    editorStatistics.putInt(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS, sharedPreferencesSupabase.maxDayExperiencePoints)
    editorStatistics.putString(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS_DATE, sharedPreferencesSupabase.maxDayExperiencePointsDate)
    editorStatistics.putInt(SharedPreferencesKeys.NUMBER_OF_BADGES, sharedPreferencesSupabase.numberOfBadges)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY_EASY, sharedPreferencesSupabase.pasagalegoCorrectAnswersDictionaryEasy)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY_EASY, sharedPreferencesSupabase.pasagalegoErrorAnswersDictionaryEasy)
    editorStatistics.putString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY_EASY, sharedPreferencesSupabase.pasagalegoTimeDictionaryEasy)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_DICTIONARY, sharedPreferencesSupabase.pasagalegoCorrectAnswersDictionary)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_DICTIONARY, sharedPreferencesSupabase.pasagalegoErrorAnswersDictionary)
    editorStatistics.putString(SharedPreferencesKeys.PASAGALEGO_TIME_DICTIONARY, sharedPreferencesSupabase.pasagalegoTimeDictionary)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_CORRECT_ANSWERS_ORIXINAL, sharedPreferencesSupabase.pasagalegoCorrectAnswersOrixinal)
    editorStatistics.putInt(SharedPreferencesKeys.PASAGALEGO_ERROR_ANSWERS_ORIXINAL, sharedPreferencesSupabase.pasagalegoErrorAnswersOrixinal)
    editorStatistics.putString(SharedPreferencesKeys.PASAGALEGO_TIME_ORIXINAL, sharedPreferencesSupabase.pasagalegoTimeOrixinal)
    editorStatistics.putInt(SharedPreferencesKeys.ATRAPAME_SE_PODES_QUESTIONS_NEEDED, sharedPreferencesSupabase.atrapameSePodesQuestionsNeeded)
    editorStatistics.putInt(SharedPreferencesKeys.ATRAPA_UN_MILLON_MAX_CASH, sharedPreferencesSupabase.atrapaUnMillonMaxCash)
    editorStatistics.putInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_EASY, sharedPreferencesSupabase.aforcadoMaxStreakEasy)
    editorStatistics.putInt(SharedPreferencesKeys.AFORCADO_MAX_STREAK_DIFICULT, sharedPreferencesSupabase.aforcadoMaxStreakDificult)
    editorStatistics.putInt(SharedPreferencesKeys.VERDADE_OU_MENTIRA_MAX_SCORE, sharedPreferencesSupabase.verdadeOuMentiraMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.ADIVINHA_ESCUDO_MAX_SCORE, sharedPreferencesSupabase.adivinhaEscudoMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.ADIVINHA_ANO_FOTO_MAX_SCORE, sharedPreferencesSupabase.adivinhaAnoFotoMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.AGORA_CAIGO_MAX_SCORE, sharedPreferencesSupabase.agoraCaigoMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.PROBA_VELOCIDADE_MIN_TIME, sharedPreferencesSupabase.probaVelocidadeMinTime)
    editorStatistics.putInt(SharedPreferencesKeys.RULETA_DA_SORTE_MAX_CASH, sharedPreferencesSupabase.ruletaDaSorteMaxCash)
    editorStatistics.putInt(SharedPreferencesKeys.ONDE_ESTAN_MIN_TIME, sharedPreferencesSupabase.ondeEstanMinTime)
    editorStatistics.putInt(SharedPreferencesKeys.SOPA_LETRAS_MAX_SCORE, sharedPreferencesSupabase.sopaLetrasMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_EASY, sharedPreferencesSupabase.anagramasMaxScoreEasy)
    editorStatistics.putInt(SharedPreferencesKeys.ANAGRAMAS_MAX_SCORE_DIFICULT, sharedPreferencesSupabase.anagramasMaxScoreDificult)
    editorStatistics.putInt(SharedPreferencesKeys.ADIVINHA_PERSONAXE_MAX_SCORE, sharedPreferencesSupabase.adivinhaPersonaxeMaxScore)
    editorStatistics.putFloat(SharedPreferencesKeys.XOGO_PALABRAS_MAX_SCORE, sharedPreferencesSupabase.xogoPalabrasMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_EASY, sharedPreferencesSupabase.explosionPalabrasMaxScoreEasy)
    editorStatistics.putInt(SharedPreferencesKeys.EXPLOSION_PALABRAS_MAX_SCORE_DIFICULT, sharedPreferencesSupabase.explosionPalabrasMaxScoreDificult)
    editorStatistics.putInt(SharedPreferencesKeys.WORDLE_MAX_STREAK, sharedPreferencesSupabase.wordleMaxStreak)
    editorStatistics.putInt(SharedPreferencesKeys.CONEXIONS_MAX_SCORE, sharedPreferencesSupabase.conexionsMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.PALABRAS_ENCADEADAS_MAX_SCORE, sharedPreferencesSupabase.palabrasEncadeadasMaxScore)
    editorStatistics.putInt(SharedPreferencesKeys.BRISCA_MAX_SCORE_EASY, sharedPreferencesSupabase.briscaMaxScoreEasy)
    editorStatistics.putInt(SharedPreferencesKeys.BRISCA_MAX_SCORE_DIFICULT, sharedPreferencesSupabase.briscaMaxScoreDificult)

    editorStatistics.apply()

    val sharedPreferencesVirtualCoins = context.getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, Context.MODE_PRIVATE)
    val editorVirtualCoins = sharedPreferencesVirtualCoins.edit()

    editorVirtualCoins.putInt(SharedPreferencesKeys.COINS, sharedPreferencesSupabase.coins)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_1, sharedPreferencesSupabase.item1)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_2, sharedPreferencesSupabase.item2)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_3, sharedPreferencesSupabase.item3)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_4, sharedPreferencesSupabase.item4)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_5, sharedPreferencesSupabase.item5)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_6, sharedPreferencesSupabase.item6)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_7, sharedPreferencesSupabase.item7)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_8, sharedPreferencesSupabase.item8)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_9, sharedPreferencesSupabase.item9)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_10, sharedPreferencesSupabase.item10)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_11, sharedPreferencesSupabase.item11)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_12, sharedPreferencesSupabase.item12)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_13, sharedPreferencesSupabase.item13)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_14, sharedPreferencesSupabase.item14)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_15, sharedPreferencesSupabase.item15)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_16, sharedPreferencesSupabase.item16)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_17, sharedPreferencesSupabase.item17)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_18, sharedPreferencesSupabase.item18)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_19, sharedPreferencesSupabase.item19)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_20, sharedPreferencesSupabase.item20)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_21, sharedPreferencesSupabase.item21)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_22, sharedPreferencesSupabase.item22)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_23, sharedPreferencesSupabase.item23)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_24, sharedPreferencesSupabase.item24)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_25, sharedPreferencesSupabase.item25)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_26, sharedPreferencesSupabase.item26)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_27, sharedPreferencesSupabase.item27)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_28, sharedPreferencesSupabase.item28)
    editorVirtualCoins.putBoolean(SharedPreferencesKeys.ITEM_29, sharedPreferencesSupabase.item29)

    editorVirtualCoins.apply()

    Toast.makeText(context,"Datos obtidos correctamente", Toast.LENGTH_SHORT).show()
}

@Serializable
data class SharedPreferencesData(
    val userId: String,
    val longestStreak: Int,
    val experiencePoints: Int,
    val maxDayExperiencePoints: Int,
    val maxDayExperiencePointsDate: String,
    val numberOfBadges: Int,
    val pasagalegoCorrectAnswersDictionaryEasy: Int,
    val pasagalegoErrorAnswersDictionaryEasy: Int,
    val pasagalegoTimeDictionaryEasy: String,
    val pasagalegoCorrectAnswersDictionary: Int,
    val pasagalegoErrorAnswersDictionary: Int,
    val pasagalegoTimeDictionary: String,
    val pasagalegoCorrectAnswersOrixinal: Int,
    val pasagalegoErrorAnswersOrixinal: Int,
    val pasagalegoTimeOrixinal: String,
    val atrapameSePodesQuestionsNeeded: Int,
    val atrapaUnMillonMaxCash: Int,
    val aforcadoMaxStreakEasy: Int,
    val aforcadoMaxStreakDificult: Int,
    val verdadeOuMentiraMaxScore: Int,
    val adivinhaEscudoMaxScore: Int,
    val adivinhaAnoFotoMaxScore: Int,
    val agoraCaigoMaxScore: Int,
    val probaVelocidadeMinTime: Int,
    val ruletaDaSorteMaxCash: Int,
    val ondeEstanMinTime: Int,
    val sopaLetrasMaxScore: Int,
    val anagramasMaxScoreEasy: Int,
    val anagramasMaxScoreDificult: Int,
    val adivinhaPersonaxeMaxScore: Int,
    val xogoPalabrasMaxScore: Float,
    val explosionPalabrasMaxScoreEasy: Int,
    val explosionPalabrasMaxScoreDificult: Int,
    val wordleMaxStreak: Int,
    val conexionsMaxScore: Int,
    val palabrasEncadeadasMaxScore: Int,
    val briscaMaxScoreEasy: Int,
    val briscaMaxScoreDificult: Int,
    val coins: Int,
    val item1: Boolean,
    val item2: Boolean,
    val item3: Boolean,
    val item4: Boolean,
    val item5: Boolean,
    val item6: Boolean,
    val item7: Boolean,
    val item8: Boolean,
    val item9: Boolean,
    val item10: Boolean,
    val item11: Boolean,
    val item12: Boolean,
    val item13: Boolean,
    val item14: Boolean,
    val item15: Boolean,
    val item16: Boolean,
    val item17: Boolean,
    val item18: Boolean,
    val item19: Boolean,
    val item20: Boolean,
    val item21: Boolean,
    val item22: Boolean,
    val item23: Boolean,
    val item24: Boolean,
    val item25: Boolean,
    val item26: Boolean,
    val item27: Boolean,
    val item28: Boolean,
    val item29: Boolean
)