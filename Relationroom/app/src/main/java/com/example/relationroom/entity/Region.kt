package com.example.relationroom.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Region(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)