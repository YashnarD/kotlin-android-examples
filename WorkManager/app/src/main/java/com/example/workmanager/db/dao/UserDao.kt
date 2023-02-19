package com.example.workmanager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.workmanager.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun addUser(userEntity: UserEntity)

    @Query("select * from userentity")
    fun getAllUsers(): List<UserEntity>
}