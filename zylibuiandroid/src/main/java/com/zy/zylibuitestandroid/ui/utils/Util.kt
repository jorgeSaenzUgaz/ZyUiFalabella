package com.zy.zylibuitestandroid.ui.utils

import android.app.Activity
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.zy.zylibuiandroid.R

object Util {

    fun translucentStatusBarDialog(pdDialog: Dialog, context: Activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            pdDialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            pdDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            pdDialog.window!!.statusBarColor =
                ContextCompat.getColor(context, R.color.zystatusbarcolor)
            pdDialog.window!!.navigationBarColor =
                ContextCompat.getColor(context, R.color.zystatusbarcolor)
        }

        context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}