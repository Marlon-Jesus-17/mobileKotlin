package com.marlebas.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.ui.navigation.NavGraph
import com.marlebas.newsapp.ui.theme.NewsAppTheme
import com.marlebas.newsapp.ui.viewModel.PostDetailViewModel
import com.marlebas.newsapp.ui.viewModel.PostViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                NavGraph()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(navController: NavController, viewModel: PostViewModel = viewModel()){

    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "News App",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.carregarPosts()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Atualizar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column (modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            when{
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = error ?: "Erro desconhecido")

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(onClick = {
                                viewModel.carregarPosts()
                            }, enabled = !isLoading) {
                                Text("Tentar novamente")
                            }
                        }
                    }
                }
                else -> {
                    if (posts.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Button(
                                onClick = {viewModel.carregarPosts()},
                                enabled = !isLoading
                            ) {
                                Text("Buscar Posts")
                            }
                        }
                    } else {
                        LazyColumn {
                            items (posts){
                                    post -> PostItem(post){
                                Log.d("NAV", "ID: ${post.id}")
                                navController.navigate("detail/${post.id}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit){

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable{ onClick()}) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title,
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun PostDetailScreen(
    postId: Int?,
    viewModel: PostDetailViewModel = viewModel()
){
    val post by viewModel.post.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(postId) {
        postId?.let{
            viewModel.carregarPost(it)
        }
    }

    when{
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        post != null -> {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = post!!.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = post!!.body,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        else -> {
            Text("Erro ao carregar post")
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