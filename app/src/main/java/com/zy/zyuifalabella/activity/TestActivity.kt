package com.zy.zyuifalabella.activity

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

        var btnZyProgressDialog = findViewById<AppCompatButton>(R.id.btnZyProgressDialog)
        btnZyProgressDialog.setOnClickListener {
            var test = ZyProgressDialog(this)
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
    }
}