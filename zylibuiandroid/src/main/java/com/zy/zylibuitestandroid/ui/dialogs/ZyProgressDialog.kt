package com.zy.zylibuitestandroid.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import com.zy.zylibuiandroid.R
import com.zy.zylibuitestandroid.ui.utils.Util

class ZyProgressDialog(val pcContext: Context) {
    private val TAG = ZyProgressDialog::class.java.simpleName
    protected var mDialog: Dialog? = null
    var zyTextDialog: TextView? = null

    protected fun isDialogOpened(): Boolean {
        if (mDialog == null || !mDialog!!.isShowing) {
            return false
        }
        return true
    }

    fun setText(message: String) {
        Log.i(TAG, "ChangeText: $message")
        (pcContext as Activity).runOnUiThread {
            try {
                zyTextDialog?.text = message
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun show(initMsg: String = "") {
        if (!isDialogOpened()) {
            (pcContext as Activity).runOnUiThread {
                try {
                    mDialog = Dialog(pcContext, R.style.FullScreenDialogStyle).apply {
                        setContentView(R.layout.view_zy_progress_dialog)
                        window?.apply {
                            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                            addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                        }
                    }

                    mDialog?.let { dialog ->
                        Util.changeColorStatusActionBar(dialog, pcContext)
                        dialog.findViewById<TextView>(R.id.txtMessage).text = initMsg
                        zyTextDialog = dialog.findViewById(R.id.txtMessage)
                        zyTextDialog?.setText(initMsg)
                        dialog.show()

                        Log.e(TAG, "Dialog Show")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, e.message.toString())
                }
            }
        }
    }

    fun dimiss() {
        if (mDialog != null) {
            try {
                (pcContext as Activity).runOnUiThread {
                    mDialog?.dismiss()
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}
