package com.emarielle.sitestcliente.perguntas

data class Questions(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: String
)

