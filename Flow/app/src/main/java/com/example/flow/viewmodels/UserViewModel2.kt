package com.example.flow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.models.UserWithPost
import com.example.flow.repository.UserRepository
import com.example.flow.retrofit.ApiClient
import com.example.flow.utils.NetworkHelper
import com.example.flow.utils.UserResource
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

class UserViewModel2(private val networkHelper: NetworkHelper) : ViewModel() {

    private val liveData = MutableLiveData<UserResource>()

    private val userRepository = UserRepository(ApiClient.apiService)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        if(networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                liveData.postValue(UserResource.Loading)
                val userWithPost = UserWithPost()
                userRepository.getUsers().flatMapConcat {
                    userWithPost.userList = it.body()
                    userRepository.getPosts()
                }.collect {
                    userWithPost.postList = it.body()
                    liveData.postValue(UserResource.Success(userWithPost))
                }
            }
        } else {
            liveData.postValue(UserResource.Error("No internet connection"))
        }
    }

    fun getUserWithPost(): LiveData<UserResource> {
        return liveData
    }
}