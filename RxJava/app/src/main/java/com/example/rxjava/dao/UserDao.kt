package com.example.rxjava.dao

import androidx.room.*
import com.example.rxjava.entity.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Single<Long>

    @Update
    fun editUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from users where id=:id")
    fun getUserById(id: Int): Maybe<User>

    @Query("select * from users")
    fun getAllUsers(): Flowable<List<User>>
}