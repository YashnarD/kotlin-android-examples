package com.example.coroutines.utils

import com.example.coroutines.models.MainUser

sealed class UserResource {

    object Loading: UserResource()

    class Success(val mainUser: MainUser): UserResource()

    class Error(val exception: Exception): UserResource()
}