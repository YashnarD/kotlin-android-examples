package com.example.mvvmarchitecture.utils

import com.example.mvvmarchitecture.database.entity.UserEntity
import com.example.mvvmarchitecture.models.newsModel.Headlines

sealed class UserResource {

    object Loading : UserResource()

    data class Success(val headlines: Headlines?) : UserResource()

    data class Error(val message: String) : UserResource()
}