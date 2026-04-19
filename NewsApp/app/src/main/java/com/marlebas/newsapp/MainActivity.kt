package com.marlebas.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marlebas.newsapp.ui.theme.NewsAppTheme
import com.marlebas.newsapp.ui.viewModel.CounterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                MinhaPrimeiraTela()
            }
        }
    }
}

@Composable
fun MinhaPrimeiraTela(viewModel: CounterViewModel = viewModel()){

    val posts = viewModel.posts.value

    Column (modifier = Modifier.fillMaxSize()){
        Button(onClick = {
            viewModel.carregarPosts()
        }) {
            Text("Buscar Posts")
        }
        posts.forEach {
            Text(it.title)
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
    NewsAppTheme {
        Greeting("Android")
    }
}