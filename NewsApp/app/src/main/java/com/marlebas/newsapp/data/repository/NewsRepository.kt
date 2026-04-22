package com.marlebas.newsapp.data.repository

import com.marlebas.newsapp.data.Post
import com.marlebas.newsapp.data.RetrofitIntance

class NewsRepository{

    suspend fun getPosts(): List<Post>{
        return RetrofitIntance.api.getPosts()
    }
}