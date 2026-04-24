package com.marlebas.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){
    private var _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = PostRepository()

    fun carregarPosts(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response = repository.getPosts()
                _posts.value = response

            } catch (e: Exception){
                _error.value = "Erro ao carregar dados"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}