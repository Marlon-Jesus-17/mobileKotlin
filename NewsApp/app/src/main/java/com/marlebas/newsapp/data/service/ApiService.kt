package com.marlebas.newsapp.data.service

import com.marlebas.newsapp.data.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post
}