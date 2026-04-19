package com.marlebas.testes

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TestesDeAprendizado{
    @Test
    fun main(){

//    NullPointerException

        val nome: String = "Marlon" //val para imutável(não pode ter o valor alterado) e String não aceita nulo

        var biografia: String? = null //var para mutável(pode ter o valor alterado) e String? para aceitar o valor null

//    Estruturas de controle e função

        val status = 2

        val mensagem = when(status){ //when substitui o switch
            1 -> "Ativo"
            2 -> "Pendente"
            else -> "Inativo"
        }

        assertEquals("Pendente", mensagem)

//    Funções (Declaração e Sintaxe Curta)

//    declaração padrão:

        fun soma(a: Int, b: Int): Int{
            return a + b
        }

        var resultado = soma(9, 5)

        assertEquals(14, resultado)

//    Single-Expression Functions (Sintaxe de uma linha): Como você já sabe o que a função faz, pode pular as chaves e o return:

        fun somar(a: Int, b: Int) = a + b

        resultado = somar(2, 7)

        assertEquals(9, resultado)

        val acesso = 1

        fun conferirAcesso(acesso: Int) = when (acesso) {1 -> "Admin" 2-> "Editor" else -> "Não possui acesso"}

        assertEquals("Admin", conferirAcesso(acesso))

//    O Operador Elvis (?:)

//    Lembra da sua variável biografia: String?? Se você quiser exibir um texto padrão caso ela seja nula, você usa o operador Elvis
//    (porque ele parece o topete do Elvis Presley se você deitar a cabeça para a esquerda):

//    Se biografia for null, usa o texto da direita
        val textoParaExibir = biografia?: "Usuário sem bio"


//    Calsses e Objetos

//    Data Classes (A salvação do programador)
//    No Kotlin, se o objetivo da classe é apenas segurar dados, usamos a palavra-chave data.

        data class Usuario(var nome: String, var idade: Int)

//    Com essa única linha, o Kotlin gera automaticamente para você:
//    Getters e Setters (para var) ou apenas Getters (para val).
//    toString() formatado: Usuario(nome=Thiago, idade=25).
//    equals() e hashCode().
//    Função copy(), que permite criar um novo objeto alterando apenas uma propriedade.

        println(Usuario("Marlon", 24)) //ToString funcionando

        val usuario = Usuario(nome = "Agatha", idade = 10) //Instancia não usa a palavra new

        assertEquals(10, usuario.idade) //Usando os getters por trás dos panos

        usuario.idade = 6 //usando os setters por trás dos panos


    }
}
