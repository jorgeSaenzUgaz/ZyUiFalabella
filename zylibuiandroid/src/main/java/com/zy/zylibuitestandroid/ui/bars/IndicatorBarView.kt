package com.zy.zylibuitestandroid.ui.bars

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R

class IndicatorBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, private val defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val defaultColor = ContextCompat.getColor(context, android.R.color.darker_gray)
    private val redColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    private val yellowColor = ContextCompat.getColor(context, android.R.color.holo_orange_light)
    private val greenColor = ContextCompat.getColor(context, android.R.color.holo_green_dark)

    private var indicatorLevel: Int = 1
    private val barSpacing: Float

    init {
        // Convertir 3dp a px
        barSpacing = 3 * context.resources.displayMetrics.density
        setupAttrs(context, attrs)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.IndicatorBarView, defStyleAttr, 0)
        try {
            indicatorLevel = ta.getInt(R.styleable.IndicatorBarView_indicatorLevel, 1).coerceIn(1, 5)
        } finally {
            ta.recycle()
        }
    }

    fun setIndicator(level: Int) {
        indicatorLevel = level.coerceIn(1, 5)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = 120 // Ancho mínimo
        val minHeight = 120 // Alto mínimo
        setMeasuredDimension(
            resolveSize(minWidth, widthMeasureSpec),
            resolveSize(minHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val barCount = 5
        val totalHeight = height.toFloat()

        // Altura total sin contar el espaciado
        val totalSpacing = barSpacing * (barCount - 1)
        val barHeight = (totalHeight - totalSpacing) / barCount

        val barWidth = width.toFloat() // Ocupar el ancho completo

        for (i in 0 until barCount) {
            barPaint.color = defaultColor // Color por defecto

            barPaint.color = when {
                i < indicatorLevel -> {
                    when (indicatorLevel) {
                        1, 2 -> redColor
                        3 -> yellowColor
                        4, 5 -> greenColor
                        else -> defaultColor
                    }
                }
                else -> defaultColor
            }

            val top = totalHeight - (i + 1) * barHeight - i * barSpacing
            val bottom = top + barHeight
            canvas.drawRect(0f, top, barWidth, bottom, barPaint)
        }
    }
}
