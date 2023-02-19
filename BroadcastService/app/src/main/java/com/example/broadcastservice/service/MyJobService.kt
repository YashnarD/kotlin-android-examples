package com.example.broadcastservice.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyJobService : JobService() {

    private val TAG = "MyJobService"
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: ")
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob: ")
        return true
    }

}