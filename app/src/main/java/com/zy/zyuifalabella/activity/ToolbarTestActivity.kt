package com.zy.zyuifalabella.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zy.zylibuitestandroid.ui.utils.Extensions.changeColorStatusActionBar
import com.zy.zyuifalabella.R

class ToolbarTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_test)
        this.changeColorStatusActionBar()
    }
}