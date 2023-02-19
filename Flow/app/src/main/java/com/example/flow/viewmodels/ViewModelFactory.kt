package com.example.flow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flow.utils.NetworkHelper

class ViewModelFactory(private val networkHelper: NetworkHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(networkHelper) as T
        } else if(modelClass.isAssignableFrom(UserViewModel2::class.java)) {
            return UserViewModel2(networkHelper) as T
        } else if(modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel() as T
        }

        throw Exception("Error")
    }
}