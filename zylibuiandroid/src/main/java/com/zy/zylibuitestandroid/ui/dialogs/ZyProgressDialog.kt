package com.zy.zylibuitestandroid.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import com.zy.zylibuiandroid.R
import com.zy.zylibuitestandroid.ui.utils.Util
import java.lang.ref.WeakReference

class ZyProgressDialog private constructor() {
    private val TAG = ZyProgressDialog::class.java.simpleName

    private var mDialog: Dialog? = null
    private var zyTextDialog: TextView? = null
    private var contextRef: WeakReference<Context>? = null

    companion object {
        private val instance = ZyProgressDialog()

        //Obtener la instancia sin crear múltiples diálogos
        fun getInstance(context: Context): ZyProgressDialog {
            instance.contextRef = WeakReference(context)
            return instance
        }
    }

    private fun isDialogOpened(): Boolean {
        return mDialog?.isShowing ?: false
    }

    //Mostrar el diálogo
    fun show(initMsg: String = "") {
        val context = contextRef?.get()
        if (context !is Activity || context.isFinishing) return

        if (!isDialogOpened()) {
            context.runOnUiThread {
                try {
                    mDialog = Dialog(context, R.style.FullScreenDialogStyle).apply {
                        setContentView(R.layout.view_zy_progress_dialog)
                        window?.apply {
                            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                            addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                        }
                    }

                    mDialog?.let { dialog ->
                        Util.changeColorStatusActionBar(dialog, context)
                        zyTextDialog = dialog.findViewById<TextView>(R.id.txtMessage).apply {
                            text = initMsg
                        }
                        dialog.show()

                        Log.d(TAG, "Dialog Show")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, e.message.toString())
                }
            }
        }
    }

    //Cambiar el texto del diálogo
    fun setText(message: String) {
        val context = contextRef?.get()
        if (context !is Activity || context.isFinishing) return

        context.runOnUiThread {
            try {
                zyTextDialog?.text = message
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    //Cerrar el díalogo
    fun dismiss() {
        val context = contextRef?.get()
        if (context !is Activity || context.isFinishing) return

        context.runOnUiThread {
            try {
                mDialog?.dismiss()
                mDialog = null
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}
