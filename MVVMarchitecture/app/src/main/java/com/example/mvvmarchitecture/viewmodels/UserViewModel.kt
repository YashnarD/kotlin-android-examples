package com.example.mvvmarchitecture.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarchitecture.database.entity.UserEntity
import com.example.mvvmarchitecture.repository.UserRepository
import com.example.mvvmarchitecture.utils.NetworkHelper
import com.example.mvvmarchitecture.utils.UserResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun fetchUsers(category: String): StateFlow<UserResource> {
        val stateFlow = MutableStateFlow<UserResource>(UserResource.Loading)

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                userRepository.getNewsData(category)
                    .catch {
                        stateFlow.emit(UserResource.Error(it.message ?: ""))
                    }.collect {
                        if (it.isSuccessful) {
//                            val body = it.body()
//                            val list = ArrayList<UserEntity>()
//                            body?.forEach {
//                                list.add(UserEntity(it.id, it.login, it.avatar_url))
//                            }
//                            userRepository.addUserToDb(list)
                            stateFlow.emit(UserResource.Success(it.body()))
                        } else {
                            if (it.code() in 400..499) {
                                stateFlow.emit(UserResource.Error("Client error"))
                            } else if (it.code() in 500..599) {
                                stateFlow.emit(UserResource.Error("Server error"))
                            } else {
                                stateFlow.emit(UserResource.Error("Other error"))
                            }
                        }
                    }
            } else {
//                userRepository.getDbUsers()
//                    .collect {
//                        if (it.isEmpty()) {
//                            stateFlow.emit(UserResource.Error("No internet connection"))
//                        } else {
//                            stateFlow.emit(UserResource.Success(it))
//                        }
//                    }
            }
        }
        return stateFlow
    }
}