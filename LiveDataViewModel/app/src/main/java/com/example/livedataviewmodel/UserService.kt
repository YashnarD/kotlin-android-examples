package com.example.livedataviewmodel

import com.example.livedataviewmodel.models.User
import com.example.livedataviewmodel.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {

    fun getUsers(): List<User> {
        val list = ArrayList<User>()
        ApiClient.apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    list.addAll(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })
        return list
    }
}