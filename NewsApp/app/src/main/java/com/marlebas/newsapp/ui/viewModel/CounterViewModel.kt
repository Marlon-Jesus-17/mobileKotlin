package com.marlebas.newsapp.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.data.RetrofitIntance
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel(){
    var posts = mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun carregarPosts(){
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = RetrofitIntance.api.getPosts()
                posts.value = response
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}