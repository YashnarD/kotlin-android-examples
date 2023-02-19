package com.example.workmanager.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String
)