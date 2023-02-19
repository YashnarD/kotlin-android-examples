package com.example.broadcastservice

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastservice.databinding.ActivitySecondBinding
import com.example.broadcastservice.receivers.ExampleReceiver

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener {
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(this@SecondActivity, ExampleReceiver::class.java)
                val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.getBroadcast(this@SecondActivity, 1, intent, PendingIntent.FLAG_IMMUTABLE)
                } else {
                    PendingIntent.getBroadcast(this@SecondActivity, 1, intent, 0)
                }
                val m = System.currentTimeMillis()
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, m, 60 * 1000, pendingIntent)
//                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, 60 * 1000, pendingIntent)
            }
        }
    }
}