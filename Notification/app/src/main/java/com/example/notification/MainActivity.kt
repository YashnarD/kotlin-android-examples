package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val channelId = "channelId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // public notification
        // current activity close

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        binding.button.setOnClickListener {
            val intent = Intent(this, NotificationReceiver::class.java)
            intent.putExtra("item_id", 1)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE)

            val remoteInput: RemoteInput = RemoteInput.Builder("key")
                .setLabel("Type message")
                .build()

            val action = NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_send,
                "Reply",
                pendingIntent
            ).addRemoteInput(remoteInput)

            val builder = NotificationCompat.Builder(this, channelId)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            builder.setContentTitle("Content title")
            builder.setContentText("Content text")
            builder.addAction(action.build())

            val notification = builder.build()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channelId, "Name", importance)
                mChannel.description = "descriptionText"
                notificationManager.createNotificationChannel(mChannel)
            }
            notificationManager.notify(1, notification)
        }

        binding.button2.setOnClickListener {
            notificationManager.cancel(1)
        }
    }
}