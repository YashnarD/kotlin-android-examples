package com.example.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    @ColumnInfo(name = "phone_number")
    var number: String,
    var age: Int
)
