package com.marlebas.newsapp.data

import retrofit2.http.GET

interface NewsApi{
    @GET("posts")
    suspend fun getPosts(): List<Post>
}