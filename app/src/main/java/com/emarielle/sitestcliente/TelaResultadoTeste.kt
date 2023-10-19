package com.emarielle.sitestcliente

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TelaResultadoTeste : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_resultado_teste)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")
    }
}