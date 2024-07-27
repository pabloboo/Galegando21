package com.galegando21.day01Pasagalego

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.galegando21.R
import com.galegando21.utils.ALFABETO
import kotlin.math.cos
import kotlin.math.sin

enum class LetterStatus {
    NOT_ANSWERED,
    CORRECT,
    INCORRECT
}

class RoscoView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val letters = ALFABETO.toCharArray()
    private var currentLetter: Char = 'A'
    private val letterStatus = mutableMapOf<Char, LetterStatus>()
    private var colorAnimator: ValueAnimator? = null

    init {
        letters.forEach { letterStatus[it] = LetterStatus.NOT_ANSWERED }
    }

    fun setCurrentLetter(letter: Char) {
        currentLetter = letter

        colorAnimator?.cancel() // Cancelar la animación anterior

        // Animación de cambio de color en la letra actual
        colorAnimator = ValueAnimator.ofObject(
            ArgbEvaluator(),
            resources.getColor(R.color.secondaryBlue, null),
            resources.getColor(R.color.primaryBlue, null)
        ).apply {
            duration = 500
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener { invalidate() }
            start()
        }

        invalidate()
    }

    fun setLetterStatus(letter: Char, status: LetterStatus) {
        letterStatus[letter] = status
        invalidate() // Redraw the view
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = (width.coerceAtMost(height) / 2f) - paint.textSize - 100
        val letterRadius = radius / 12f
        val circleRadius = letterRadius * 1.5f
        val centerX = width / 2f
        val centerY = height / 2f

        for (i in letters.indices) {
            val angle = Math.toRadians((i * 360.0 / letters.size) - 90).toFloat()
            val letterX = centerX + radius * cos(angle.toDouble())
            val letterY = centerY + radius * sin(angle.toDouble())

            // Draw the circle
            if (letters[i] == currentLetter) {
                paint.color = colorAnimator?.animatedValue as? Int ?: resources.getColor(R.color.secondaryBlue, null)
            } else {
                paint.color = when (letterStatus[letters[i]]) {
                    LetterStatus.NOT_ANSWERED -> resources.getColor(R.color.primaryBlue, null)
                    LetterStatus.CORRECT -> resources.getColor(R.color.correctGreen, null)
                    LetterStatus.INCORRECT -> resources.getColor(R.color.errorRed, null)
                    else -> resources.getColor(R.color.primaryBlue, null)
                }
            }

            canvas.drawCircle((letterX + letterRadius).toFloat(),
                (letterY - letterRadius).toFloat(), circleRadius, paint)

            // Draw the letter
            paint.color = Color.WHITE
            paint.textSize = letterRadius * 2
            paint.textAlign = Paint.Align.CENTER
            canvas.drawText(letters[i].toString(), letterX.toFloat() + letterRadius, letterY.toFloat(), paint)
        }
    }
}