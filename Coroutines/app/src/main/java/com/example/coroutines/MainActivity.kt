package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.utils.UserResource
import com.example.coroutines.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.fetchUsers().observe(this, Observer {
            when (it) {
                is UserResource.Loading -> {
                    Log.d(TAG, "onCreate: Loading...")
                }
                is UserResource.Success -> {
                    Log.d(TAG, "onCreate: ${it.mainUser}")
                }
                is UserResource.Error -> {
                    Log.d(TAG, "onCreate: ${it.exception.message}")
                }
            }
        })
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Job Cancel")
        job.cancel()
        super.onDestroy()
    }
}