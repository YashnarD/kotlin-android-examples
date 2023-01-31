package com.example.relationroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "Talaba",
    foreignKeys = [ForeignKey(
        entity = Region::class,
        parentColumns = ["id"],
        childColumns = ["region_id"],
        onDelete = CASCADE
    )]
)

data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "region_id")
    val regionId: Int
)
