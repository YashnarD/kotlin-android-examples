package com.example.mvvmarchitecture.retrofit

import com.example.mvvmarchitecture.models.GithubUser
import com.example.mvvmarchitecture.models.newsModel.Headlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getListData(
        @Query("q") category: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
        @Query("apiKey") apiKey: String = "ec53550bda604e4dbb8b95a8027b5e3a"
    ): Response<Headlines>

//    @GET("users")
//    suspend fun getGithubUsers(): Response<List<GithubUser>>
}