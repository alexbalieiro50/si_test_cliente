package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.emarielle.sitestcliente.databinding.ActivityTelaResultadoQuizBinding

class TelaResultadoQuiz : AppCompatActivity() {

    private lateinit var binding:ActivityTelaResultadoQuizBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTelaResultadoQuizBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")


        binding.btnVoltarTelaIncial.setOnClickListener {
            voltarTela()
        }


        // Recuperar a pontuação e o número total de perguntas dos extras do Intent
        val pontuacao = intent.getIntExtra("pontuacao", 0)
        val totalPerguntas = intent.getIntExtra("totalPerguntas", 0)

        // Exibir a pontuação
        val pontuacaoTextView = binding.txtResultado
        pontuacaoTextView.text = " $pontuacao / 10"
    }

    private fun voltarTela() {
        val voltarTela = Intent(this, TelaConteudo::class.java)
        startActivity(voltarTela)
    }



}