package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.emarielle.sitestcliente.adapter.AdapterTema
import com.emarielle.sitestcliente.databinding.ActivityTelaPrincipalBinding
import com.emarielle.sitestcliente.model.ListaConteudo

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")

        binding.btnVoltar.setOnClickListener {

            voltarTela()
        }

        binding.btnFazerQuiz.setOnClickListener {

            irQuiz()

        }



        val topico: TextView = binding.textTopico
        val titulo: TextView = binding.txtTitulo
        val conteudo: TextView = binding.txtConteudo

        val topicoConteudo = intent.getStringExtra("topico")
        val tituloConteudo = intent.getStringExtra("titulo")
        val conteudoConteudo = intent.getStringExtra("conteudo")

        if (topicoConteudo != null && tituloConteudo != null && conteudoConteudo != null) {
            topico.text = topicoConteudo
            titulo.text = tituloConteudo
            conteudo.text = conteudoConteudo
        }

    }

    private fun voltarTela() {
        val voltarTela = Intent(this, TelaConteudo::class.java)
        startActivity(voltarTela)
    }

    private fun irQuiz() {
        val irTelaQuiz= Intent(this, TelaQuiz::class.java)
        startActivity(irTelaQuiz)
    }
}