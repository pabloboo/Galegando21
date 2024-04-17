package com.galegando21

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class UnlockButtonsWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    // Cada vez que se ejecuta la función se desbloquea un botón
    override fun doWork(): Result {
        val sharedPreferences = applicationContext.getSharedPreferences("BotonesDesbloqueados", Context.MODE_PRIVATE)
        val unlockedButtonCount = sharedPreferences.getInt("unlockedButtonCount", 0)
        sharedPreferences.edit().putInt("unlockedButtonCount", unlockedButtonCount + 1).apply()

        // Crear y enviar la notificación
        createNotification()

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
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
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
}