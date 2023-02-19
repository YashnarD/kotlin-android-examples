package com.example.livedataviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livedataviewmodel.models.User
import com.example.livedataviewmodel.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {

    fun getUsers(): LiveData<List<User>> {
        val list = MutableLiveData<List<User>>()
        ApiClient.apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) {
                    list.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })
        return list
    }
}