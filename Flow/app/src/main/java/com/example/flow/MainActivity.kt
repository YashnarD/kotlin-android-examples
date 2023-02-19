package com.example.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flow.databinding.ActivityMainBinding
import com.example.flow.utils.NetworkHelper
import com.example.flow.utils.UserResource
import com.example.flow.viewmodels.MyViewModel
import com.example.flow.viewmodels.UserViewModel
import com.example.flow.viewmodels.UserViewModel2
import com.example.flow.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    private lateinit var userViewModel: UserViewModel
    private lateinit var userViewModel2: UserViewModel2
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkHelper = NetworkHelper(this)
//        userViewModel =
//            ViewModelProvider(this, ViewModelFactory(networkHelper))[UserViewModel::class.java]

        userViewModel2 =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[UserViewModel2::class.java]

//        myViewModel =
//            ViewModelProvider(this, ViewModelFactory(networkHelper))[MyViewModel::class.java]

        userViewModel2.getUserWithPost().observe(this, Observer {
            when (it) {
                is UserResource.Loading -> {

                }

                is UserResource.Error -> {
                    Log.d(TAG, "onCreate: ${it.message}")
                }

                is UserResource.Success -> {
                    Log.d(TAG, "onCreate: ${it.userWithPost.userList}")
                    Log.d(TAG, "onCreate: ${it.userWithPost.postList}")
                }
            }
        })
    }
}