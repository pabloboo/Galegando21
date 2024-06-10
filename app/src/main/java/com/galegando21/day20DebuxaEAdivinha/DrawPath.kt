package com.galegando21.day20DebuxaEAdivinha

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawPath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentPaint: Paint = Paint()
    private var currentPath: Path = Path()
    private val pathsAndPaints: MutableList<Pair<Path, Paint>> = mutableListOf()

    init {
        currentPaint.color = Color.BLACK
        currentPaint.style = Paint.Style.STROKE
        currentPaint.strokeWidth = 10f
        currentPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        for ((path, paint) in pathsAndPaints) {
            canvas.drawPath(path, paint)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentPath.moveTo(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    currentPath.lineTo(event.x, event.y)
                }
                MotionEvent.ACTION_UP -> {
                    currentPath.lineTo(event.x, event.y)
                    pathsAndPaints.add(Pair(currentPath, Paint(currentPaint))) // Copiar el path y paint actuales
                    currentPath = Path() // Empezar un nuevo path
                }
                else -> return false
            }
            invalidate()
            return true
        }
        return false
    }

    fun clearCanvas() {
        pathsAndPaints.clear()
        currentPath.reset()
        invalidate()
    }

    fun setColor(s: String) {
        currentPaint.color = Color.parseColor(s)
    }

    fun setStrokeWidth(width: Float) {
        currentPaint.strokeWidth = width
    }
}