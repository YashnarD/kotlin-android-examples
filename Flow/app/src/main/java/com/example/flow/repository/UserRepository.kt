package com.example.flow.repository

import com.example.flow.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: ApiService) {

    fun getUsers() = flow { emit(apiService.getUsers()) }

    fun getPosts() = flow { emit(apiService.getPosts()) }
}