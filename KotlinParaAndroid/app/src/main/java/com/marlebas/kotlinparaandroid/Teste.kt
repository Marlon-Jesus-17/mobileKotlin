package com.marlebas.kotlinparaandroid

data class Filme(val nome: String, val nota: Double, val favorito: Boolean = false)

fun apresentar(): List<Filme>{
    val filmes = listOf(
        Filme("Batman", 9.4),
        Filme("Jujutsu", 8.8),
        Filme("Dragon ball z", 9.7),
        Filme("Madagascar", 7.3),
        Filme("O diabo veste prada", 10.0),
        Filme("O rei Leão", 9.9),
        Filme("Hércules", 8.2),
        Filme("A era do gelo", 7.5),
        Filme("Cruela", 8.9),
        Filme("Encanto", 8.0),
        Filme("Alice no país das maravilhas", 6.9),
        Filme("Os incriveis", 9.7),
        Filme("Os incriveis 2", 9.7),
        Filme("SpainGlish", 6.5),
        Filme("Pequenos espiões", 6.4),
        Filme("A noiva cadaver", 8.4),
        Filme("Coraline", 10.0),
        Filme("O rei leão 2", 9.7),
        Filme("O rei Leão 3", 9.8)
    )
    return filmes
}