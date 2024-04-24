package com.galegando21

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.galegando21.utils.NUMBER_OF_DAYS
import com.galegando21.utils.SharedPreferencesKeys

class UnlockButtonsWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    // Cada vez que se ejecuta la función se desbloquea un botón
    override fun doWork(): Result {
        val sharedPreferences = applicationContext.getSharedPreferences(SharedPreferencesKeys.UNLOCKED_BUTTONS, Context.MODE_PRIVATE)
        val unlockedButtonCount = sharedPreferences.getInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, 0)
        sharedPreferences.edit().putInt(SharedPreferencesKeys.UNLOCKED_BUTTON_COUNT, unlockedButtonCount + 1).apply()

        // Si aún no se han desbloqueado todos los botones, crear y enviar la notificación
        if (unlockedButtonCount < NUMBER_OF_DAYS) {
            createNotification()
        } else {
            // Si ya se han desbloqueado todos los botones, crear y enviar la notificación de racha
            createNotificationRacha()
        }

        return Result.success()
    }

    private fun createNotification() {
        val channelId = "unlock_buttons_channel"
        val channelName = "Desbloqueo de contido"
        val notificationId = 1

        // Crear un Intent para abrir la MainActivity cuando se haga clic en la notificación
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        // Crear la notificación
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Novo contido desbloqueado!")
            .setContentText("Fai click para ver o novo contido")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // La notificación se cancela cuando el usuario la toca
            .build()

        // Crear el canal de notificación (necesario para Android 8.0 y versiones posteriores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        NotificationManagerCompat.from(applicationContext).notify(notificationId, notification)
    }

    private fun createNotificationRacha() {
        val channelId = "current_streak_channel"
        val channelName = "Racha actual"
        val notificationId = 2

        // Crear un Intent para abrir la MainActivity cuando se haga clic en la notificación
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        val sharedPreferencesStatistics = applicationContext.getSharedPreferences(SharedPreferencesKeys.STATISTICS, AppCompatActivity.MODE_PRIVATE)
        val currentStreak = sharedPreferencesStatistics.getInt(SharedPreferencesKeys.CURRENT_STREAK, 0)

        // Crear la notificación
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Non perdas a túa racha!")
            .setContentText("Tes unha racha de $currentStreak días, xoga para non perdela.")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // La notificación se cancela cuando el usuario la toca
            .build()

        // Crear el canal de notificación (necesario para Android 8.0 y versiones posteriores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        NotificationManagerCompat.from(applicationContext).notify(notificationId, notification)
    }
}