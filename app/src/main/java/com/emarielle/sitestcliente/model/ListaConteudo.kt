package com.emarielle.sitestcliente.model


import java.io.Serializable

data class ListaConteudo(
    val titulo: String? = null,
    val topico: String? = null,
    val conteudo: String? = null
) : Serializable