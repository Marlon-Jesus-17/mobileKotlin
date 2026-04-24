package com.marlebas.newsapp.data.repository

import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.data.RetrofitIntance
import com.marlebas.newsapp.data.RetrofitIntance.api

class PostRepository{

    suspend fun getPosts(): List<Post>{
        return RetrofitIntance.api.getPosts()
    }

    suspend fun getPostsById(id: Int): Post{
        return api.getPostById(id)
    }
}