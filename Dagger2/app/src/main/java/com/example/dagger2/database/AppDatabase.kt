package com.example.dagger2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dagger2.database.dao.UserDao
import com.example.dagger2.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}