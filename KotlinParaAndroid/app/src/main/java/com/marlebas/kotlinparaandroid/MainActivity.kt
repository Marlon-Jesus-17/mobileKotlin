package com.marlebas.kotlinparaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marlebas.kotlinparaandroid.ui.theme.KotlinParaAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val filmes = remember {
                mutableStateListOf<Filme>().apply {
                    addAll(apresentar())
                }
            }
            LazyColumn{ //Cria a lista de itens
                items(filmes) { //Recebe o tipo dos itens
                    filme ->

                    // Card cria os cards onde as informações ficam dentro
                    Card(
                        onClick = { //torna o Card um botão que dispara uma ação quando clicado
                            val index = filmes.indexOf(filme)
                            filmes[index] = filme.copy(favorito = !filme.favorito)
                        },
                        elevation = CardDefaults.cardElevation(6.dp), //Cartão com sombra/destaque (altura visual do cartão)
                        modifier = Modifier //Controla a aparência/tamanho
                        .fillMaxWidth() //Ocupa largura
                        .padding(horizontal = 10.dp, vertical = 6.dp) //Tamanho da margem
                    ) {
                        //Row organiza os itens horizontalmente, um ao lado do outro
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween //Joga um item para a esquerda e o outro para a direita, criando um espaço entre eles
                        ) {
                            //Column estrutura os componentes em forma de colunas
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = filme.nome, fontSize = 20.sp)
                                Text(text = "Filme avaliado")
                            }
                            val corNota = when{
                                filme.nota >= 9 -> Color.Green
                                filme.nota >= 8 -> Color.Yellow
                                else -> Color.Red
                            }
                            Text(text = if (filme.favorito)
                                            "❤\uFE0F ${filme.nota}"
                                        else
                                            "\uD83E\uDD0D ${filme.nota}",
                                fontSize = 18.sp,
                                color = corNota)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinParaAndroidTheme {
        Greeting("Android")
    }
}