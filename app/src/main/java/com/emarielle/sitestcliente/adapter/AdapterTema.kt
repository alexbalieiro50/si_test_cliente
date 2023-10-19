package com.emarielle.sitestcliente.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        holder.tema.text = temas[position].titulo
        holder.topico.text = temas[position].topico
    }

    inner class TemaViewHolder(binding: ActivityTelaListaConteudoBinding) : RecyclerView.ViewHolder(binding.root) {

        val tema = binding.textTema
        val topico = binding.txtTopico

    }

}