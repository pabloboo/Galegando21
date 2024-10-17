package com.galegando21

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.time.LocalDateTime
import java.time.Month

class SpecialNotificationsWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val currentDateTime = LocalDateTime.now()

        // Verificar si hoy es 17 de mayo
        if (currentDateTime.dayOfMonth == 17 && currentDateTime.month == Month.MAY) {
            createSpecialEventNotification()
        }

        // Verificar si la hora actual es 19:00
        if (currentDateTime.hour == 19) {
            createGoldenHourNotification()
        }

        return Result.success()
    }

    private fun createSpecialEventNotification() {
        // Código para crear la notificación del evento del día de las letras gallegas
        createNotification("canal_letras_gallegas", "Día das letras galegas", "Obtén o cuadruple de puntos durante todo o día!")
    }

    private fun createGoldenHourNotification() {
        // Código para crear la notificación de la hora dorada
        createNotification("canal_hora_dorada", "Hora dourada", "Xoga agora e obtén o dobre de puntos.")
    }

    private fun createNotification(channelId: String, channelName: String, contentText: String) {
        val notificationId = 1

        // Crear un Intent para abrir la MainActivity cuando se haga clic en la notificación
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        // Crear la notificación
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(channelName)
            .setContentText(contentText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // La notificación se cancela cuando el usuario la toca
            .build()

        // Crear el canal de notificación (necesario para Android 8.0 y versiones posteriores)
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) { return }
        NotificationManagerCompat.from(applicationContext).notify(notificationId, notification)
    }
}