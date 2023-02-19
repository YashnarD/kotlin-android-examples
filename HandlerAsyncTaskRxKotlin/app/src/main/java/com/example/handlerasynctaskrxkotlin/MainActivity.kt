package com.example.handlerasynctaskrxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.handlerasynctaskrxkotlin.adapters.StudentAdapter
import com.example.handlerasynctaskrxkotlin.database.AppDatabase
import com.example.handlerasynctaskrxkotlin.databinding.ActivityMainBinding
import com.example.handlerasynctaskrxkotlin.entities.Student
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var isPressed = false
    private lateinit var handler: Handler
    private lateinit var appDatabase: AppDatabase
    private lateinit var studentAdapter: StudentAdapter
    private val TAG = "MainActivity"
//    private var mHandler: Handler? = null
//    var gameOn = false
//    var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        binding.button.setOnClickListener {
            val student = Student(name = "Yashnar", age = 20)
            appDatabase.studentDao().addStudent(student)
        }

        studentAdapter = StudentAdapter()
        binding.rv.adapter = studentAdapter

        appDatabase.studentDao()
            .getAllStudents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                studentAdapter.submitList(it)
            }, {
                Log.d(TAG, "onCreate: $it")
            }, {
                Log.d(TAG, "onCreate: complete")
            })

//        startTime = System.currentTimeMillis()
//        mHandler = object : Handler(Looper.getMainLooper()) {
//            override fun handleMessage(msg: Message) {
//                super.handleMessage(msg)
//                if(gameOn) {
//                    val seconds = (System.currentTimeMillis() - startTime) / 1000
//                    Log.i("info", "seconds = $seconds")
//                }
//                mHandler?.sendEmptyMessageDelayed(0, 1000)
//            }
//        }
//        gameOn = true
//        mHandler?.sendEmptyMessage(0)

        handler = Handler(Looper.getMainLooper())
    }

    override fun onBackPressed() {
        if(isPressed) {
            super.onBackPressed()
            return
        }
        isPressed = true
        handler.postDelayed({
            isPressed = false
        }, 2000)
        Toast.makeText(this, "Dasturdan chiqish uchun yana bir marta bosing", Toast.LENGTH_SHORT)
            .show()
    }
}