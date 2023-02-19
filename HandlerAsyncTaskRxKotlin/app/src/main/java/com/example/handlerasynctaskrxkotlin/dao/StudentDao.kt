package com.example.handlerasynctaskrxkotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.handlerasynctaskrxkotlin.entities.Student
import io.reactivex.Flowable

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Query("select * from student")
    fun getAllStudents(): Flowable<List<Student>>
}