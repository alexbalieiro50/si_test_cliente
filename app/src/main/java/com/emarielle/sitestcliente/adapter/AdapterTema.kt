package com.emarielle.sitestcliente.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emarielle.sitestcliente.MainActivity
import com.emarielle.sitestcliente.TelaPrincipal
import com.emarielle.sitestcliente.databinding.ActivityTelaListaConteudoBinding
import com.emarielle.sitestcliente.model.ListaConteudo

class AdapterTema(private val context: Context, private val temas: MutableList<ListaConteudo>): RecyclerView.Adapter<AdapterTema.TemaViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemaViewHolder {
        val itemLista = ActivityTelaListaConteudoBinding.inflate(LayoutInflater.from(context),parent,false)
        val holder = TemaViewHolder(itemLista)
        return holder
    }

    override fun getItemCount(): Int  = temas.size

    override fun onBindViewHolder(holder: TemaViewHolder, position: Int) {
        val tema = temas[position]
        holder.bind(tema)
        holder.itemView.setOnClickListener {
            val itemClicado = temas[position]
            val intent = Intent(context, TelaPrincipal::class.java)
            intent.putExtra("topico", itemClicado.topico)
            intent.putExtra("titulo", itemClicado.titulo)
            intent.putExtra("conteudo", itemClicado.conteudo)
            context.startActivity(intent)
        }
    }

    inner class TemaViewHolder(private val binding: ActivityTelaListaConteudoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(conteudo: ListaConteudo) {
            binding.textTema.text = conteudo.titulo
            binding.txtTopico.text = conteudo.topico
        }

    }

}
