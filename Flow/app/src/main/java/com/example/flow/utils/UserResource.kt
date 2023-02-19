package com.example.flow.utils

import com.example.flow.models.UserWithPost

sealed class UserResource {

    object Loading: UserResource()

    class Success(val userWithPost: UserWithPost): UserResource()

    class Error(val message: String): UserResource()
}