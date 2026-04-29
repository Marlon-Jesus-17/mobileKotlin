package com.marlebas.kotlinparaandroid

data class Filme(val nome: String, val nota: Double)

fun apresentar(): List<Filme>{
    val filmes = listOf(
        Filme("Batman", 9.4),
        Filme("Jujutsu", 8.8),
        Filme("Dragon ball z", 9.7),
        Filme("Madagascar", 7.3)
    )

    filmes.forEach { println(it.nome)}
    val melhores = filmes.filter { it.nota > 9 }
    melhores.forEach { it.nome }
    val nomes = filmes.map { it.nome }
    println(nomes)

    return filmes
}