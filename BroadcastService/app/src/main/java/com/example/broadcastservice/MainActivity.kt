package com.example.broadcastservice

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastservice.databinding.ActivityMainBinding
import com.example.broadcastservice.receivers.MyReceiver

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myReceiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myReceiver = MyReceiver()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(myReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myReceiver)
    }
}