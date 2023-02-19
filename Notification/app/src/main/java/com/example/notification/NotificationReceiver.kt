package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val resultsFromIntent = RemoteInput.getResultsFromIntent(intent)
        val charSequence = resultsFromIntent.getCharSequence("key")
        Toast.makeText(context, charSequence, Toast.LENGTH_SHORT).show()
        val notificationManager =
            context?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, "channelId")
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setContentTitle("Content title")
        builder.setContentText("Replied")

        val notification = builder.build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("channelId", "Name", importance)
            mChannel.description = "descriptionText"
            notificationManager.createNotificationChannel(mChannel)
        }
        notificationManager.notify(1, notification)
    }
}