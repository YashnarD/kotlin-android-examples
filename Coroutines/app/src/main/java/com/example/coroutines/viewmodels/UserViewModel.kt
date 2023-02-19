package com.example.coroutines.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.models.MainUser
import com.example.coroutines.models.User
import com.example.coroutines.retrofit.ApiClient
import com.example.coroutines.utils.UserResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    fun fetchUsers(): LiveData<UserResource> {
        val liveData = MutableLiveData<UserResource>()
        liveData.postValue(UserResource.Loading)

        viewModelScope.launch {
            try {
                coroutineScope {
                    val async1 = async { ApiClient.apiService.getUsers1() }
                    val async2 = async { ApiClient.apiService.getUsers2() }
                    val await1 = async1.await()
                    val await2 = async2.await()
                    val mainUser = MainUser(await1.body(), await2.body())
                    liveData.postValue(UserResource.Success(mainUser))
                }
            } catch (e: Exception) {
                liveData.postValue(UserResource.Error(e))
            }
        }
        return liveData
    }
}