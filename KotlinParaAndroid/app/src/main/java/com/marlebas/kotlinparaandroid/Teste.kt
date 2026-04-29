package com.marlebas.kotlinparaandroid

data class Filme(val nome: String, val nota: Double, val favorito: Boolean = false)

fun apresentar(): List<Filme>{
    val filmes = listOf(
        Filme("Batman", 9.4),
        Filme("Jujutsu", 8.8),
        Filme("Dragon ball z", 9.7),
        Filme("Madagascar", 7.3)
    )
    return filmes
}