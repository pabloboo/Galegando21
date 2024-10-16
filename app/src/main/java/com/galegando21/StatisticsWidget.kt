package com.galegando21

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.getCurrentStreak

class StatisticsWidget : AppWidgetProvider() {
    private var sharedPreferencesChangeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

        // Register SharedPreferences change listener
        val sharedPreferences = context.getSharedPreferences(
            SharedPreferencesKeys.STATISTICS,
            AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferencesChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }

    override fun onDisabled(context: Context) {
        // Unregister SharedPreferences change listener
        val sharedPreferences = context.getSharedPreferences(
            SharedPreferencesKeys.STATISTICS,
            AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
        sharedPreferencesChangeListener = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if ("reload" == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        if ("open_app" == intent.action) {
            val openAppIntent = Intent(context, MainActivity::class.java)
            openAppIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(openAppIntent)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val sharedPreferences = context.getSharedPreferences(
        SharedPreferencesKeys.STATISTICS,
        AppCompatActivity.MODE_PRIVATE
    )
    val currentStreak = getCurrentStreak(context)
    val totalExperience = sharedPreferences.getInt(SharedPreferencesKeys.EXPERIENCE_POINTS, 0).toString()

    // Log the values
    Log.d("StatisticsWidget", "Current streak: $currentStreak")
    Log.d("StatisticsWidget", "Total experience: $totalExperience")

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.statistics_widget)
    views.setTextViewText(R.id.current_streak_statistics, currentStreak.toString())
    views.setTextViewText(R.id.total_experience_statistics, totalExperience)
    views.setOnClickPendingIntent(R.id.widget_reload_button, getPendingSelfIntent(context, appWidgetId, "reload"))
    views.setOnClickPendingIntent(R.id.widget_title, getPendingSelfIntent(context, appWidgetId, "open_app"))

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

private fun getPendingSelfIntent(context: Context, appWidgetId: Int, action: String): PendingIntent {
    val intent = Intent(context, StatisticsWidget::class.java)
    intent.action = action
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    return PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
}