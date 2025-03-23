package com.zy.zyuifalabella.activity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zy.zylibuitestandroid.ui.finger.FingerIndicatorView
import com.zy.zyuifalabella.R

class FingerIndicatorTestActivity : AppCompatActivity() {
    var pViewFingerIndicator: FingerIndicatorView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_indicator_test)

        pViewFingerIndicator = findViewById(R.id.viewFingerIndicator)

        val bitmap = getBitmapFromMipmap(this, R.mipmap.zy_test_finger)

        pViewFingerIndicator?.apply {
            setFirstFingerText(text = "ÍNDICE IZQUIERDO")
            setFirstFingerImage(codFinger = "1")
            setFirstFingerCaptureImageBitmap(bmHuellaCapturada = bitmap, 10)

            setSecondFingerText(text = "ÍNDICE DERECHO")
            setSecondFingerImage(codFinger = "1")
            setSecondFingerCaptureImageBitmap(bmHuellaCapturada = bitmap, 140)
        }
    }


    fun getBitmapFromMipmap(context: Context, resId: Int): Bitmap? {
        val drawable: Drawable? = ContextCompat.getDrawable(context, resId)
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            drawable?.let {
                val bitmap = Bitmap.createBitmap(
                    it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                it.setBounds(0, 0, canvas.width, canvas.height)
                it.draw(canvas)
                bitmap
            }
        }
    }
}