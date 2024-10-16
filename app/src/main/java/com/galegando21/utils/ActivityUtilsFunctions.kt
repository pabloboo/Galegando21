package com.galegando21.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.galegando21.BannerFragment
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys.CURRENT_DAY_PLAYED
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

const val ENVIRONMENT = "production"
//const val ENVIRONMENT = "development"
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

fun getCurrentDay(): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return format.format(LocalDate.now())
}

fun getCurrentDayEmotionIdKey(): String {
    return CURRENT_DAY_PLAYED + "-" + getCurrentDay()
}

fun getDayBefore(date: String): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return format.format(LocalDate.parse(date).minusDays(1))
}

fun getEmotionIdKey(date: String): String {
    return "$CURRENT_DAY_PLAYED-$date"
}

fun getCurrentStreak(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
    return getCurrentStreakInternal(sharedPreferences)
}

fun getCurrentStreak(activity: AppCompatActivity): Int {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
    return getCurrentStreakInternal(sharedPreferences)
}

private fun getCurrentStreakInternal(sharedPreferences: SharedPreferences): Int {
    var currentStreak = 0
    var currentDate = getCurrentDay()

    if (sharedPreferences.getBoolean(getCurrentDayEmotionIdKey(), false)) {
        currentStreak++
    }
    currentDate = getDayBefore(currentDate)
    while (sharedPreferences.getBoolean(getEmotionIdKey(currentDate), false)) {
        currentStreak++
        currentDate = getDayBefore(currentDate)
    }

    return currentStreak
}

fun updateCurrentStreak(activity: AppCompatActivity) {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(getCurrentDayEmotionIdKey(), true).apply()

    // Actualizar el valor de la racha más larga
    val longestStreak = sharedPreferences.getInt(SharedPreferencesKeys.LONGEST_STREAK, 0)
    val currentStreak = getCurrentStreak(activity)
    if (currentStreak > longestStreak) {
        sharedPreferences.edit().putInt(SharedPreferencesKeys.LONGEST_STREAK, currentStreak).apply()
    }
}

fun updateUserExperience(activity: AppCompatActivity, experience: Int): Int {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)

    val currentStreak = getCurrentStreak(activity)
    val experienceWithCurrentStreak = experience + currentStreak

    var experienceWithBonus = experienceWithCurrentStreak
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (currentHour in 19..20) { // Hora Dourada -> dobre de experiencia
        experienceWithBonus *= 2
    }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    if (currentMonth == 4 && currentDay == 17) { // Día das Letras Galegas -> cuadruple de experiencia
        experienceWithBonus *= 4
    }

    val currentExperience = sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0)
    val newExperience = currentExperience + experienceWithBonus

    sharedPreferences.edit().putInt(SharedPreferencesKeys.EXPERIENCE_POINTS, newExperience).apply()

    // Actualizar el número de monedas virtuales
    val sharedPreferencesVirtualCoins = activity.getSharedPreferences(SharedPreferencesKeys.VIRTUAL_COINS, AppCompatActivity.MODE_PRIVATE)
    var coinsExperienceAccumulated = sharedPreferencesVirtualCoins.getInt(SharedPreferencesKeys.COINS_EXPERIENCE_ACCUMULATED, 0)
    coinsExperienceAccumulated += experienceWithBonus
    sharedPreferencesVirtualCoins.edit().putInt(SharedPreferencesKeys.COINS_EXPERIENCE_ACCUMULATED, coinsExperienceAccumulated).apply()
    if (coinsExperienceAccumulated >= 1000) { // Cada 1000 puntos de experiencia se obtiene una moneda virtual
        val coins = sharedPreferencesVirtualCoins.getInt(SharedPreferencesKeys.COINS, 0)
        sharedPreferencesVirtualCoins.edit().putInt(SharedPreferencesKeys.COINS, coins+1).apply()
        sharedPreferencesVirtualCoins.edit().putInt(SharedPreferencesKeys.COINS_EXPERIENCE_ACCUMULATED, coinsExperienceAccumulated-1000).apply()
    }

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

// Funciones para compartir resultados
fun screenShot(view: View): Bitmap? {
    view.setBackgroundColor(android.graphics.Color.WHITE)
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun shareScreenshot(bitmap: Bitmap?, activity: AppCompatActivity) {
    if (bitmap != null) {
        try {
            // Crear un archivo para escribir los datos de la imagen
            val file = File(activity.getExternalFilesDir(null), "resultados_galegando21.jpg")
            file.createNewFile()

            // Convertir bitmap a bytes
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bitmapdata = bos.toByteArray()

            // Escribir los bytes en el archivo
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()

            // Obtener la URI del archivo
            val fileUri = FileProvider.getUriForFile(activity, "com.galegando21.provider", file)

            // Compartir la imagen
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/jpeg"
            shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            activity.startActivity(Intent.createChooser(shareIntent, "Compartir en..."))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

// Función de mostrar encuestas la primera vez que se completa un juego
fun showSurvey(activity: AppCompatActivity, key: String, enquisaTextView: TextView) {
    val sharedPreferences = activity.getSharedPreferences(SharedPreferencesKeys.SURVEYS, AppCompatActivity.MODE_PRIVATE)
    val isFirstTime = sharedPreferences.getBoolean(key, false)

    if (!isFirstTime) {
        val text = "Danos feedback! completa a seguinte enquisa: "
        val link = "https://docs.google.com/forms/d/e/1FAIpQLSc_yJAt18SDFg-towSOjIjpFAUjKgWvBgzbsxlF8wXcDNOLRQ/viewform"
        val spannableString = SpannableString("$text$link")

        val startLinkIndex = text.length
        val endLinkIndex = startLinkIndex + link.length
        spannableString.setSpan(URLSpan(link), startLinkIndex, endLinkIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        enquisaTextView.text = spannableString
        enquisaTextView.movementMethod = LinkMovementMethod.getInstance()

        enquisaTextView.visibility = View.VISIBLE
        sharedPreferences.edit().putBoolean(key, true).apply()
    } else {
        enquisaTextView.visibility = View.GONE
    }
}