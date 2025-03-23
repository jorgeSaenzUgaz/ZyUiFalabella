package com.zy.zyuifalabella.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.zy.zylibuitestandroid.ui.dialogs.ZyProgressDialog
import com.zy.zyuifalabella.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val btnZyProgressDialog = findViewById<AppCompatButton>(R.id.btnZyProgressDialog)
        btnZyProgressDialog.setOnClickListener {
            val test = ZyProgressDialog(this)
            test.show("Realizando consulta de configuración.\nPor favor, espere.")

            // Retrasar la actualización del texto 5 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                test.setText("Obteniendo mejores huellas.\nPor favor, espere.")
            }, 5000)

            Handler(Looper.getMainLooper()).postDelayed({
                test.setText("Seleccionando huellas.\nPor favor, espere.")
            }, 10000)

            Handler(Looper.getMainLooper()).postDelayed({
                test.dismiss()
            }, 15000)
        }

        val btnFingerIndicator = findViewById<AppCompatButton>(R.id.btnFingerIndicator)
        btnFingerIndicator.setOnClickListener {
            startActivity(Intent(this, FingerIndicatorTestActivity::class.java))
        }

        val btnToolbarLogo = findViewById<AppCompatButton>(R.id.btnToolbarLogo)
        btnToolbarLogo.setOnClickListener {
            startActivity(Intent(this, ToolbarTestActivity::class.java))
        }
    }
}