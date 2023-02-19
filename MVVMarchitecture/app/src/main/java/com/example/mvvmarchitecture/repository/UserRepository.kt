package com.example.mvvmarchitecture.repository

import com.example.mvvmarchitecture.database.dao.UserDao
import com.example.mvvmarchitecture.database.entity.UserEntity
import com.example.mvvmarchitecture.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: ApiService) {

    suspend fun getNewsData(category: String) = flow { emit(apiService.getListData(category)) }

//    suspend fun getDbUsers() = flow { emit(userDao.getUsers()) }
//
//    suspend fun addUserToDb(list: List<UserEntity>) = userDao.insert(list)
}