package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class TelaDeSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_splash)

        supportActionBar?.hide()

        window.statusBarColor = Color.parseColor("#6746C5")

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,FormLogin::class.java)
            startActivity(intent)
        },3000)
    }
}