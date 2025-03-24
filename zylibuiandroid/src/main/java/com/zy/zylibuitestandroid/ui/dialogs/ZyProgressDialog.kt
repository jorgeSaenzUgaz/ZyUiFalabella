package com.zy.zylibuitestandroid.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import com.zy.zylibuiandroid.R
import com.zy.zylibuitestandroid.ui.utils.Extensions.changeColorStatusActionBar
import com.zy.zylibuitestandroid.ui.utils.Util
import java.lang.ref.WeakReference

class ZyProgressDialog(context: Context) {
    private val TAG = ZyProgressDialog::class.java.simpleName
    private var mDialog: Dialog? = null
    private var zyTextDialog: TextView? = null
    private var contextRef: WeakReference<Context> = WeakReference(context)

    private fun isDialogOpened(): Boolean {
        return mDialog?.isShowing ?: false
    }

    /** Permite mostrar el diálogo con un mensaje inicial
     * @param initMsg: Mensaje inicial que se mostrará en el diálogo.
     */
    fun show(initMsg: String = "") {
        val context = contextRef.get()
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
                        context.changeColorStatusActionBar(dialog)
                        zyTextDialog = dialog.findViewById<TextView>(R.id.txtMessage).apply {
                            text = initMsg
                        }
                        dialog.show()

                        Log.d(TAG, "Dialog Show")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error al mostrar el diálogo: ${e.message}")
                }
            }
        }
    }

    /**
     * Permite cambiar el texto del diálogo mientras este se encuentre abierto
     * @param message: Mensaje que se mostrará en el diálogo.
     */
    fun setText(message: String) {
        val context = contextRef.get()
        if (context !is Activity || context.isFinishing) return

        context.runOnUiThread {
            try {
                zyTextDialog?.text = message
            } catch (e: Exception) {
                Log.e(TAG, "Error al cambiar el texto del diálogo: ${e.message}")
            }
        }
    }

    /**
     * Permite cerrar el diálogo
     */
    fun dismiss() {
        val context = contextRef.get()
        if (context !is Activity || context.isFinishing) return

        context.runOnUiThread {
            try {
                mDialog?.dismiss()
                mDialog = null
            } catch (e: Exception) {
                Log.e(TAG, "Error al cerrar el diálogo: ${e.message}")
            }
        }
    }
}
