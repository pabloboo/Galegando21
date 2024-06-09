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

const val NUMBER_OF_DAYS = 20
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
}

fun removeAccents(input: String): String {
    val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
    val accentRemoved = Regex("(?![ñÑ])[\\u0300-\\u036F]").replace(normalized, "")
    return accentRemoved.replace("ñ", "ñ").replace("Ñ", "Ñ")
}