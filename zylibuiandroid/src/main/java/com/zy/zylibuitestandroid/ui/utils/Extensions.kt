package com.zy.zylibuitestandroid.ui.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R

object Extensions {

    //Convertir de DP a Pixel
    fun Context.dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }

    // Extensión para modificar el alpha de los colores
    fun Int.withAlpha(alphaFactor: Float): Int {
        val alpha = (255 * alphaFactor).toInt().coerceIn(0, 255)
        return (this and 0x00FFFFFF) or (alpha shl 24)
    }

    //Cambiar el color de la barra de estado y la barra inferior de acción
    fun Activity.changeColorStatusActionBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            this.window!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            this.window!!.statusBarColor =
                ContextCompat.getColor(this, R.color.zyStatusbarColor)
            this.window!!.navigationBarColor =
                ContextCompat.getColor(this, R.color.zyStatusbarColor)
        }

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun Activity.changeColorStatusActionBar(pdDialog: Dialog) {
        if (Build.VERSION.SDK_INT >= 21) {
            pdDialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            pdDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            pdDialog.window!!.statusBarColor =
                ContextCompat.getColor(this, R.color.zyStatusbarColor)
            pdDialog.window!!.navigationBarColor =
                ContextCompat.getColor(this, R.color.zyStatusbarColor)
        }

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}