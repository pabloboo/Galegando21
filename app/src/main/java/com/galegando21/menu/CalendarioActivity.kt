package com.galegando21.menu

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.galegando21.R
import com.galegando21.utils.SharedPreferencesKeys
import com.galegando21.utils.getEmotionIdKey
import com.galegando21.utils.setBanner
import java.time.LocalDate

class CalendarioActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var calendarGrid: GridLayout
    private val logoResource = R.drawable.game_controller_icon
    private lateinit var monthTv: TextView
    private lateinit var prevMonthBtn: ImageButton
    private lateinit var nextMonthBtn: ImageButton
    private lateinit var yearTv: TextView
    private lateinit var prevYearBtn: ImageButton
    private lateinit var nextYearBtn: ImageButton
    private lateinit var diaLunesTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        sharedPreferences = getSharedPreferences(SharedPreferencesKeys.STATISTICS, MODE_PRIVATE)
        calendarGrid = findViewById(R.id.calendarGrid)
        monthTv = findViewById(R.id.monthTv)
        prevMonthBtn = findViewById(R.id.prevMonthBtn)
        nextMonthBtn = findViewById(R.id.nextMonthBtn)
        yearTv = findViewById(R.id.yearTv)
        prevYearBtn = findViewById(R.id.prevYearBtn)
        nextYearBtn = findViewById(R.id.nextYearBtn)
        diaLunesTv = findViewById(R.id.diaLunesTv)

        setBanner(this, R.string.calendario)

        var year = getCurrrentYear()
        var month = getCurrentMonth()
        yearTv.text = year.toString()
        monthTv.text = getStringOfMonth(month)
        fillCalendar(year, month)

        prevYearBtn.setOnClickListener {
            year = getPreviousYear(year)
            yearTv.text = year.toString()
            fillCalendar(year, month)
        }

        nextYearBtn.setOnClickListener {
            year = getNextYear(year)
            yearTv.text = year.toString()
            fillCalendar(year, month)
        }

        prevMonthBtn.setOnClickListener {
            month = getPreviousMonth(month)
            monthTv.text = getStringOfMonth(month)
            fillCalendar(year, month)
        }

        nextMonthBtn.setOnClickListener {
            month = getNextMonth(month)
            monthTv.text = getStringOfMonth(month)
            fillCalendar(year, month)
        }
    }

    private fun fillCalendar(year: Int, month: Int) {
        calendarGrid.removeAllViews() // Limpia el GridLayout antes de llenarlo

        var elementSize = 90
        diaLunesTv.post {
            elementSize = diaLunesTv.width
        }

        // Calcula el número de días en el mes
        val daysInMonth = java.time.YearMonth.of(year, month).lengthOfMonth()

        // Calcula en qué día de la semana comienza el primer día del mes (1 = Lunes, 7 = Domingo)
        val firstDayOfMonth = LocalDate.of(year, month, 1).dayOfWeek.value % 8

        // Añade celdas vacías al inicio si el mes no comienza en lunes
        for (i in 1 until firstDayOfMonth) {
            calendarGrid.addView(TextView(this)) // Añade celdas vacías para alinear correctamente
        }

        // Añade los días del mes al calendario
        for (day in 1..daysInMonth) {
            val currentDate = String.format("%04d-%02d-%02d", year, month, day)
            val hasPlayed = sharedPreferences.getBoolean(getEmotionIdKey(currentDate), false)
            Log.d("CALENDAR", "currentDate: ${getEmotionIdKey(currentDate)}, hasPlayed: $hasPlayed")

            val cell = if (hasPlayed) {
                ImageView(this).apply {
                    setImageResource(logoResource)
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = elementSize
                        height = elementSize
                    }
                }
            } else {
                TextView(this).apply {
                    text = day.toString()
                    gravity = android.view.Gravity.CENTER
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = elementSize
                        height = elementSize
                    }
                }
            }

            calendarGrid.addView(cell)
        }
    }

    private fun getCurrrentYear(): Int {
        return LocalDate.now().year
    }

    private fun getPreviousYear(year: Int): Int {
        return if (year == 1) 1 else year - 1
    }

    private fun getNextYear(year: Int): Int {
        return if (year == 9999) 9999 else year + 1
    }

    private fun getCurrentMonth(): Int {
        return LocalDate.now().monthValue
    }

    private fun getPreviousMonth(month: Int): Int {
        return if (month == 1) 12 else month - 1
    }

    private fun getNextMonth(month: Int): Int {
        return if (month == 12) 1 else month + 1
    }

    private fun getStringOfMonth(month: Int): String {
        return when(month) {
            1 -> "Xaneiro"
            2 -> "Febreiro"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Maio"
            6 -> "Xuño"
            7 -> "Xullo"
            8 -> "Agosto"
            9 -> "Setembro"
            10 -> "Outubro"
            11 -> "Novembro"
            12 -> "Decembro"
            else -> "Erro"
        }
    }

}