package com.galegando21.utils
import android.content.Intent
import android.os.CountDownTimer
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.galegando21.BannerFragment
import com.galegando21.R
import java.text.Normalizer
import java.util.Calendar

//const val ENVIRONMENT = "production"
const val ENVIRONMENT = "development"
const val NUMBER_OF_DAYS = 21
const val ALFABETO = "ABCDEFGHILMNÑOPQRSTUVXZ"

fun setBanner(activity: FragmentActivity, bannerTextId: Int) {
    val bannerFragment = activity.supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
    activity.supportFragmentManager.beginTransaction().runOnCommit {
        bannerFragment.setBannerText(activity.getString(bannerTextId))
    }.commit()
}

fun showBannerMenu(activity: FragmentActivity) {
    val bannerFragment = activity.supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
    activity.supportFragmentManager.beginTransaction().runOnCommit {
        bannerFragment.showBannerMenu()
    }.commit()
}

fun setOnBackPressed(activity: AppCompatActivity, destinationActivityClass: Class<*>, timer: CountDownTimer? = null) {
    activity.onBackPressedDispatcher.addCallback(activity, object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Intent(activity, destinationActivityClass).also {
                timer?.cancel()
                activity.startActivity(it)
                activity.finish()
            }
        }
    })
}

fun updateCurrentStreak(activity: AppCompatActivity) {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
    val lastDayCurrentStreakUpdated = sharedPreferences.getInt(SharedPreferencesKeys.LAST_DAY_CURRENT_STREAK_UPDATED, -1)
    val currentStreak = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    val lastYear = sharedPreferences.getInt(SharedPreferencesKeys.LAST_YEAR_CURRENT_STREAK_UPDATED, -1)
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    if ((currentDay == lastDayCurrentStreakUpdated + 1 && currentYear == lastYear) ||
        (currentDay == 1 && lastDayCurrentStreakUpdated >= 365 && currentYear == lastYear + 1)) {
        // Incrementar el valor de la racha actual
        sharedPreferences.edit().putInt(SharedPreferencesKeys.CURRENT_STREAK, currentStreak + 1).apply()
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LAST_DAY_CURRENT_STREAK_UPDATED, currentDay).apply()
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LAST_YEAR_CURRENT_STREAK_UPDATED, currentYear).apply()
    } else if (currentDay > lastDayCurrentStreakUpdated + 1 || currentYear > lastYear) {
        // Restablecer el valor de la racha actual a 0
        sharedPreferences.edit().putInt(SharedPreferencesKeys.CURRENT_STREAK, 0).apply()
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LAST_DAY_CURRENT_STREAK_UPDATED, currentDay).apply()
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LAST_YEAR_CURRENT_STREAK_UPDATED, currentYear).apply()
    }

    // Actualizar el valor de la racha más larga
    val longestStreak = sharedPreferences.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
    val currentStreakUpdated = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
    if (currentStreakUpdated > longestStreak) {
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LONGEST_STREAK, currentStreakUpdated).apply()
    }
}

fun updateUserExperience(activity: AppCompatActivity, experience: Int): Int {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)

    val currentStreak = sharedPreferences.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)
    val experienceWithCurrentStreak = experience + currentStreak

    var experienceWithBonus = experienceWithCurrentStreak
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (currentHour in 19..20) { // Hora Dourada -> dobre de experiencia
        experienceWithBonus *= 2
    }

    val currentExperience = sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0)
    val newExperience = currentExperience + experienceWithBonus

    sharedPreferences.edit().putInt(SharedPreferencesKeys.EXPERIENCE_POINTS, newExperience).apply()

    updateTodayExperience(activity, experienceWithBonus)

    return experienceWithBonus
}

fun updateTodayExperience(activity: AppCompatActivity, experience: Int) {
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)

    var todayExperience = 0
    var lastDayExperienceUpdated = sharedPreferences.getInt(SharedPreferencesKeys.LAST_DAY_EXPERIENCE_POINTS_UPDATED, -1)
    if (currentDay == lastDayExperienceUpdated) {
        todayExperience = sharedPreferences.getInt(SharedPreferencesKeys.TODAY_EXPERIENCE_POINTS, 0)
    } else {
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LAST_DAY_EXPERIENCE_POINTS_UPDATED, currentDay).apply()
    }

    val newExperience = todayExperience + experience
    sharedPreferences.edit().putInt(SharedPreferencesKeys.TODAY_EXPERIENCE_POINTS, newExperience).apply()
    // Comprobar si es el día que más experiencia ha obtenido
    val maxDayExperience = sharedPreferences.getInt(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS, 0)
    if (newExperience > maxDayExperience) {
        sharedPreferences.edit().putInt(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS, newExperience).apply()
        // Guardar la fecha del día con más experiencia
        val today = Calendar.getInstance().time
        val dateInString = android.text.format.DateFormat.format("dd/MM/yyyy", today).toString()
        sharedPreferences.edit().putString(SharedPreferencesKeys.MAX_DAY_EXPERIENCE_POINTS_DATE, dateInString).apply()
    }
}

fun removeAccents(input: String): String {
    val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
    val accentRemoved = Regex("(?![ñÑ])[\\u0300-\\u036F]").replace(normalized, "")
    return accentRemoved.replace("ñ", "ñ").replace("Ñ", "Ñ")
}