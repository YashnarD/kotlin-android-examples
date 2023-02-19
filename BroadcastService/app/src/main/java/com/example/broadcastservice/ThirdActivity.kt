package com.example.broadcastservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastservice.databinding.ActivityThirdBinding
import com.example.broadcastservice.service.MyJobService
import com.example.broadcastservice.service.MyService

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, MyService::class.java)
        binding.startButton.setOnClickListener {
            val jobscheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo = JobInfo.Builder(123, ComponentName(this, MyJobService::class.java))
            val job = jobInfo.setRequiresCharging(false)
                .setMinimumLatency(1)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setOverrideDeadline(3 * 60 * 1000)
                .build()
            jobscheduler.schedule(job)
//            startService(intent)
        }

        binding.stopButton.setOnClickListener {
            stopService(intent)
        }
    }
}