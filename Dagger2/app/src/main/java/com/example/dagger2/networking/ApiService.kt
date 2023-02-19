package com.example.dagger2.networking

import com.example.dagger2.models.GithubUser
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>
}