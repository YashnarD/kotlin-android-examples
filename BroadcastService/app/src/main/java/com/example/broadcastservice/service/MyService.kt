package com.example.broadcastservice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }
}