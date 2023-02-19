package com.example.retrofit.retrofit

import retrofit2.http.GET
import com.example.retrofit.models.MarvelData
import retrofit2.Call

interface ApiService {

    @GET("demos/marvel")
    fun getMarvelsData(): Call<List<MarvelData>>


}