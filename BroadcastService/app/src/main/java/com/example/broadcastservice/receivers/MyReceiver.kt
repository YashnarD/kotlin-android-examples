package com.example.broadcastservice.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.broadcastservice.utils.NetworkHelper

class MyReceiver : BroadcastReceiver() {

    private lateinit var networkHelper: NetworkHelper
    override fun onReceive(context: Context, intent: Intent) {
        networkHelper = NetworkHelper(context)
        if (networkHelper.isNetworkConnected()) {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
            Log.d("YYYY", "onReceive: Connected")
        } else {
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
            Log.d("YYYY", "onReceive: Disconnected")
        }
    }
}