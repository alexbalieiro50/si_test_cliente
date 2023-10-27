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
            topico = "Testando....",
            titulo = "* Testando os tópicos",
            conteudo = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                    "It has survived not only five centuries, but also the leap into electronic typesetting," +
                    " remaining essentially unchanged. It was popularised in the 1960s with the release of" +
                    " Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                    "software like Aldus PageMaker including versions of Lorem Ipsum." +
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. \" +\n" +
                    "                    \"It has roots in a piece of classical Latin literature from 45 BC, \" +\n" +
                    "                    \"making it over 2000 years old. Richard McClintock, a Latin professor at \" +\n" +
                    "                    \"Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, \" +\n" +
                    "                    \"consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical\" +\n" +
                    "                    \" literature, discovered the undoubtable source." +
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. \" +\n" +
                    "                    \"It has roots in a piece of classical Latin literature from 45 BC, \" +\n" +
                    "                    \"making it over 2000 years old. Richard McClintock, a Latin professor at \" +\n" +
                    "                    \"Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, \" +\n" +
                    "                    \"consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical\" +\n" +
                    "                    \" literature, discovered the undoubtable source." +
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. \" +\n" +
                    "                    \"It has roots in a piece of classical Latin literature from 45 BC, \" +\n" +
                    "                    \"making it over 2000 years old. Richard McClintock, a Latin professor at \" +\n" +
                    "                    \"Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, \" +\n" +
                    "                    \"consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical\" +\n" +
                    "                    \" literature, discovered the undoubtable source."

        )

        listaTemas.add(tema1)

        val tema2 = ListaConteudo(
            topico = "Testando 2....",
            titulo = "* Testando os tópicos 2...",
            conteudo = "Contrary to popular belief, Lorem Ipsum is not simply random text. " +
                    "It has roots in a piece of classical Latin literature from 45 BC, " +
                    "making it over 2000 years old. Richard McClintock, a Latin professor at " +
                    "Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, " +
                    "consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical" +
                    " literature, discovered the undoubtable source."

        )

        listaTemas.add(tema2)

    }

}