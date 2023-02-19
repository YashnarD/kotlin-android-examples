package com.example.workmanager.retrofit

import com.example.workmanager.models.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}