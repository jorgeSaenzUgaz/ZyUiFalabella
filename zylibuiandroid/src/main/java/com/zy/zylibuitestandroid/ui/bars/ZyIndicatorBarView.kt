package com.zy.zylibuitestandroid.ui.bars

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R
import com.zy.zylibuitestandroid.ui.utils.Extensions.withAlpha

class ZyIndicatorBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, private val defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val defaultColor =
        ContextCompat.getColor(context, android.R.color.darker_gray).withAlpha(0.5f)
    private val veryBadColor =
        ContextCompat.getColor(context, R.color.zyIndicatorBarVeryBad).withAlpha(0.5f)
    private val badColor =
        ContextCompat.getColor(context, R.color.zyIndicatorBarBad).withAlpha(0.5f)
    private val mediumColor =
        ContextCompat.getColor(context, R.color.zyIndicatorBarMedium).withAlpha(0.5f)
    private val goodColor =
        ContextCompat.getColor(context, R.color.zyIndicatorBarGood).withAlpha(0.5f)
    private val veryGoodColor =
        ContextCompat.getColor(context, R.color.zyIndicatorBarVeryGood).withAlpha(0.5f)

    private var indicatorLevel: Int = 1
    private val barSpacing: Float
    private val cornerRadius: Float

    init {
        val density = context.resources.displayMetrics.density
        barSpacing = 3 * density  // Convertir 3dp a px
        cornerRadius = 5 * density // Convertir 5dp a px
        setupAttrs(context, attrs)
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet?) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.IndicatorBarView, defStyleAttr, 0)
        try {
            indicatorLevel =
                ta.getInt(R.styleable.IndicatorBarView_indicatorLevel, 1).coerceIn(1, 5)
        } finally {
            ta.recycle()
        }
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
                        1 -> veryBadColor
                        2 -> badColor
                        3 -> mediumColor
                        4 -> goodColor
                        5 -> veryGoodColor
                        else -> defaultColor
                    }
                }

                else -> defaultColor
            }

            val top = totalHeight - (i + 1) * barHeight - i * barSpacing
            val bottom = top + barHeight

            val rect = RectF(0f, top, barWidth, bottom)
            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, barPaint)
        }
    }

    /**
     * Inputs
     */

    /** Ingresar valor que se mostrará marcado en las barras las cuales varían el color según el valor ingresado
     * @param level: Ingresar un valor entre 1 y 5. Donde 1 es muy mala, 2 es mala, 3 es regular, 4 es buena y 5 es muy buena.
     */
    fun setIndicator(level: Int) {
        indicatorLevel = level.coerceIn(1, 5)
        invalidate()
    }
}