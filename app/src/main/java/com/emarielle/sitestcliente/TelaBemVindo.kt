package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.emarielle.sitestcliente.databinding.ActivityTelaBemVindoBinding
import com.google.firebase.auth.FirebaseAuth

class TelaBemVindo : AppCompatActivity() {
    private lateinit var binding: ActivityTelaBemVindoBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaBemVindoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")


       binding.btnFazerTeste.setOnClickListener {
            irTelaTeste()
        }

        /*binding.btnFazerTeste.setOnClickListener {
            // Realiza o logout do usuário
            auth.signOut()

            // Verifica se o usuário está logado
            if (auth.currentUser == null) {
                // O usuário não está logado, redireciona para a tela de login ou outra tela apropriada
                val intent = Intent(this, FormLogin::class.java)
                startActivity(intent)
                finishAffinity() // Encerra todas as atividades para que o usuário não possa voltar para a tela principal sem fazer login
            }
        }*/

    }



    private fun irTelaTeste(){
        val telaTeste = Intent(this, TelaDeTeste::class.java)
        startActivity(telaTeste)
    }
}