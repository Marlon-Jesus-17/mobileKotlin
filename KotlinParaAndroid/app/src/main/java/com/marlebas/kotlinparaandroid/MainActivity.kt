package com.marlebas.kotlinparaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marlebas.kotlinparaandroid.ui.theme.KotlinParaAndroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val filmes = remember {
                mutableStateListOf<Filme>().apply {
                    addAll(apresentar())
                }
            }

            var mostrarFavoritos by remember {
                mutableStateOf(false)
            }

            var textoBusca by remember{
                mutableStateOf("")
            }

            val listaExibida = filmes.filter { filme ->

                val passouFavorito = !mostrarFavoritos || filme.favorito

                val passouBusca = filme.nome.contains( //verifica se um texto contém outro texto dentro dele
                    textoBusca,
                    ignoreCase = true //Ignora diferença entre maiúscula e minúscula
                )
                passouFavorito && passouBusca
            }

            var filmeSelecionado by remember{
                mutableStateOf<Filme?>(null)
            }

            Scaffold( //Serve para estruturar telas prontas(esqueleto de tela Android)
                topBar = { //Área superior da tela, normalmente: título, ações, menu...
                    TopAppBar( //Barra pronta, Material Design
                        title = { //Texto principal da barra
                            Text("Catálogo de Filmes")
                        },
                        actions = {//Botões do lado direito
                            Text(
                                text =  if(mostrarFavoritos)
                                            "Todos"
                                        else
                                            "Favoritos",
                                modifier = Modifier.padding(16.dp)
                                    .clickable{ //transforma componente em clicável
                                        mostrarFavoritos = !mostrarFavoritos
                                    }
                            )
                        }
                    )
                }
            ) {
                paddingValues ->

                if(filmeSelecionado == null){

                    Column(modifier = Modifier.padding(paddingValues)) {
                        TextField(
                            value = textoBusca, //Mostra o texto atual
                            onValueChange = { textoBusca = it }, //Sempre que digita algo Atualiza o estado
                            label = {
                                Text("Buscar filme")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        LazyColumn(modifier = Modifier.weight(1f)){ //Cria uma lista
                            items(listaExibida) { //Recebe a lista de itens
                                    filme ->

                                MovieCard(filme = filme,
                                    onClick = {
                                        filmeSelecionado = filme
                                    }
                                )
                            }
                        }
                    }

                }else{

                    MovieDetailScreen(filme = filmeSelecionado!!,
                        onVoltar = {filmeSelecionado = null},
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

@Composable
fun MovieCard(filme: Filme, onClick: () -> Unit){

    // Card cria os cards onde as informações ficam dentro
    Card(
        onClick = onClick, //torna o Card um botão que dispara uma ação quando clicado
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

@Composable
fun MovieDetailScreen(filme: Filme, onVoltar: () -> Unit, paddingValues: PaddingValues){

    Column(
        modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(16.dp)
    ) {
        Text(text = filme.nome, fontSize = 26.sp)
        Text(text = "Nota: ${filme.nota}", fontSize = 20.sp)
        Text(text =
        if(filme.favorito)
            "Favorito ❤\uFE0F"
        else
            "Não favorito \uD83E\uDD0D")
        Text(text = "← Voltar", modifier = Modifier
            .padding(top = 20.dp)
            .clickable{
                onVoltar()
            },
            fontSize = 18.sp
        )
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