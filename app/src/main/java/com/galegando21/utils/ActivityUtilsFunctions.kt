package com.galegando21.utils
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.galegando21.BannerFragment
import com.galegando21.R
import java.util.Calendar

const val NUMBER_OF_DAYS = 14

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

fun setOnBackPressed(activity: AppCompatActivity, destinationActivityClass: Class<*>) {
    activity.onBackPressedDispatcher.addCallback(activity, object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Intent(activity, destinationActivityClass).also {
                activity.startActivity(it)
            }
        }
    })
}

fun updateCurrentStreak(activity: AppCompatActivity) {
    val sharedPreferences = activity.getSharedPreferences("statistics", AppCompatActivity.MODE_PRIVATE)
    val lastDayCurrentStreakUpdated = sharedPreferences.getInt("last_day_current_streak_updated", -1)
    val currentStreak = sharedPreferences.getInt("current_streak", 0)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    val lastYear = sharedPreferences.getInt("last_year_current_streak_updated", -1)
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    if ((currentDay == lastDayCurrentStreakUpdated + 1 && currentYear == lastYear) ||
        (currentDay == 1 && lastDayCurrentStreakUpdated >= 365 && currentYear == lastYear + 1)) {
        // Incrementar el valor de la racha actual
        sharedPreferences.edit().putInt("current_streak", currentStreak + 1).apply()
        sharedPreferences.edit().putInt("last_day_current_streak_updated", currentDay).apply()
        sharedPreferences.edit().putInt("last_year_current_streak_updated", currentYear).apply()
    } else if (currentDay > lastDayCurrentStreakUpdated + 1 || currentYear > lastYear) {
        // Restablecer el valor de la racha actual a 0
        sharedPreferences.edit().putInt("current_streak", 0).apply()
        sharedPreferences.edit().putInt("last_day_current_streak_updated", currentDay).apply()
        sharedPreferences.edit().putInt("last_year_current_streak_updated", currentYear).apply()
    }
}