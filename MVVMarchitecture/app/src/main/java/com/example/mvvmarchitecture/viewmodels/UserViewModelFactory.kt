package com.example.mvvmarchitecture.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmarchitecture.repository.UserRepository
import com.example.mvvmarchitecture.utils.NetworkHelper

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository, networkHelper) as T
        }
        throw Exception("Error")
    }
}