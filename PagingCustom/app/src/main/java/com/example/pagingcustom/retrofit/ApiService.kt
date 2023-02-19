package com.example.pagingcustom.retrofit

import com.example.pagingcustom.models.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    fun getUsers(@Query("page") page: Int): Call<UserData>
}