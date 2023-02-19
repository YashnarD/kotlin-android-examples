package com.example.coroutines.retrofit

import com.example.coroutines.models.User
import com.example.coroutines.models.git.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("users")
    suspend fun getUsers1(): Response<List<User>>

    @GET
    suspend fun getUsers2(@Url url: String = "https://api.github.com/users"): Response<List<GithubUser>>
}