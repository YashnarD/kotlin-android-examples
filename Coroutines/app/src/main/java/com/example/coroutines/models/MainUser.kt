package com.example.coroutines.models

import com.example.coroutines.models.git.GithubUser

data class MainUser(
    val user1: List<User>?,
    val user2: List<GithubUser>?
)