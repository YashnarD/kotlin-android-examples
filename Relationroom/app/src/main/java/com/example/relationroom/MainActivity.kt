package com.example.relationroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.relationroom.database.AppDatabase
import com.example.relationroom.databinding.ActivityMainBinding
import com.example.relationroom.entity.Region
import com.example.relationroom.entity.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)
        appDatabase.regionDao().addRegion(Region(name = "Toshkent"))
        appDatabase.regionDao().addRegion(Region(name = "Samarqand"))
        appDatabase.regionDao().addRegion(Region(name = "Navoiy"))
        appDatabase.regionDao().addRegion(Region(name = "Buxoro"))

        appDatabase.studentDao().addStudent(Student(name = "Sanjar", regionId = 1))
        appDatabase.studentDao().addStudent(Student(name = "Dilshod", regionId = 2))
        appDatabase.studentDao().addStudent(Student(name = "Mansur", regionId = 3))
        appDatabase.studentDao().addStudent(Student(name = "Suxrob", regionId = 4))
    }
}