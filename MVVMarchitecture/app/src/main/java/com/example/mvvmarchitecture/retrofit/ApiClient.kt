package com.example.mvvmarchitecture.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //    const val BASE_URL = "https://api.github.com/"
    const val BASE_URL = "https://newsapi.org/v2/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService = getRetrofit().create(ApiService::class.java)
}