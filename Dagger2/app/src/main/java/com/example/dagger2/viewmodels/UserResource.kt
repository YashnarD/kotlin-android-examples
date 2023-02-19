package com.example.dagger2.viewmodels

import com.example.dagger2.database.entity.UserEntity
import com.example.dagger2.models.GithubUser

sealed class UserResource {

    object Loading : UserResource()

    data class Success(val list: List<UserEntity>?) : UserResource()

    data class Error(val message: String) : UserResource()
}