package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.emarielle.sitestcliente.databinding.ActivityTelaResultadoTesteBinding

class TelaResultadoTeste : AppCompatActivity() {

    private lateinit var binding:ActivityTelaResultadoTesteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTelaResultadoTesteBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")

        binding.btnSaibaMais.setOnClickListener {

            irTela()

        }


        val pontuacao = intent.getIntExtra("pontuacao", 0)
        val totalPerguntas = intent.getIntExtra("totalPerguntas", 0)

        // Calcular a porcentagem
        val porcentagem = ((totalPerguntas.toInt() - pontuacao.toDouble())/totalPerguntas.toInt()) * 100

        // Exibir a pontuação e a porcentagem
        val pontuacaoTextView = binding.txtResultadoTeste
        pontuacaoTextView.text = "${porcentagem.toInt()}%"


    }

    private fun irTela() {
        val voltarTela = Intent(this, TelaConteudo::class.java)
        startActivity(voltarTela)
    }
}