package com.example.dagger2.repository

import com.example.dagger2.database.dao.UserDao
import com.example.dagger2.database.entity.UserEntity
import com.example.dagger2.networking.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService, private val userDao: UserDao) {

    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    suspend fun addUsers(list: List<UserEntity>) = userDao.addUsers(list)

    suspend fun getDbUsers() = flow { emit(userDao.getUsers()) }
}