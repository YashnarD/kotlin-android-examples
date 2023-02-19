package com.example.flow.retrofit

import retrofit2.Response
import retrofit2.http.GET
import com.example.flow.models.Post
import com.example.flow.models.User

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}