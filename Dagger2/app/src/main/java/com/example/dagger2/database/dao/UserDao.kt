package com.example.dagger2.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.dagger2.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addUsers(list: List<UserEntity>)

    @Query("select * from userEntity")
    suspend fun getUsers(): List<UserEntity>
}