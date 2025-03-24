package com.zy.zylibuitestandroid.ui.utils

import android.content.Context

object Extensions {

    fun Context.dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }

    // Extensión para modificar el alpha de los colores
    fun Int.withAlpha(alphaFactor: Float): Int {
        val alpha = (255 * alphaFactor).toInt().coerceIn(0, 255)
        return (this and 0x00FFFFFF) or (alpha shl 24)
    }
}