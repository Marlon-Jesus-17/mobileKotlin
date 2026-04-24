package com.marlebas.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel(){

    private val repository = PostRepository()

    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun carregarPost(id: Int){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getPostsById(id)
                _post.value = result
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}