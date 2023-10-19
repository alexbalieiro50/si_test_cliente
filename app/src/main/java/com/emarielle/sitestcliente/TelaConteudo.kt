package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.emarielle.sitestcliente.adapter.AdapterTema
import com.emarielle.sitestcliente.databinding.ActivityTelaConteudoBinding
import com.emarielle.sitestcliente.model.ListaConteudo
import com.google.firebase.auth.FirebaseAuth

class TelaConteudo : AppCompatActivity() {

    private lateinit var binding: ActivityTelaConteudoBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaConteudoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")

        binding.btnSair.setOnClickListener {
            // Realiza o logout do usuário
            auth.signOut()

            // Verifica se o usuário está logado
            if (auth.currentUser == null) {
                // O usuário não está logado, redireciona para a tela de login ou outra tela apropriada
                val intent = Intent(this, FormLogin::class.java)
                startActivity(intent)
                finishAffinity() // Encerra todas as atividades para que o usuário não possa voltar para a tela principal sem fazer login
            }
        }


        val recyclerView_temas = binding.RecycleViewTopico
        recyclerView_temas.layoutManager = LinearLayoutManager(this)
        recyclerView_temas.setHasFixedSize(true)

        val listaTemas: MutableList<ListaConteudo> = mutableListOf()
        val adapterTemas = AdapterTema(this, listaTemas)
        recyclerView_temas.adapter = adapterTemas

        val tema1 = ListaConteudo(
            titulo = "Testando....",
            topico = "* Testando os tópicos"
        )

        listaTemas.add(tema1)

        val tema2 = ListaConteudo(
            titulo = "Testando 2....",
            topico = "* Testando os tópicos..."
        )

        listaTemas.add(tema2)

    }

}