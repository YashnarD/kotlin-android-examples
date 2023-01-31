package com.example.relationroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.relationroom.entity.Student

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Query("select * from talaba")
    fun getAllStudents(): List<Student>
}