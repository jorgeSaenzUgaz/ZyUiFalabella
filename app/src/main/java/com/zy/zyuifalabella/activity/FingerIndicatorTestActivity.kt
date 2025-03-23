package com.zy.zyuifalabella.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zy.zylibuitestandroid.ui.finger.FingerIndicatorView
import com.zy.zyuifalabella.R

class FingerIndicatorTestActivity : AppCompatActivity() {
    var pViewFingerIndicator: FingerIndicatorView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_indicator_test)

        pViewFingerIndicator = findViewById(R.id.viewFingerIndicator)

        pViewFingerIndicator?.apply {
            setFirstFingerText(text = "ÍNDICE IZQUIERDO")
            setFirstFingerImage(codFinger = "1")

            setSecondFingerText(text = "ÍNDICE DERECHO")
            setSecondFingerImage(codFinger = "1")
        }
    }
}