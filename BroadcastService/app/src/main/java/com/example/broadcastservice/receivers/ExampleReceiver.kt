package com.example.broadcastservice.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ExampleReceiver : BroadcastReceiver() {

    private val TAG = "ExampleReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Jring", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onReceive: Jring")
    }
}