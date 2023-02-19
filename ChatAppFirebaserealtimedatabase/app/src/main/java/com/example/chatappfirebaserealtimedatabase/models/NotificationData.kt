package com.example.chatappfirebaserealtimedatabase.models

data class NotificationData(
    val `data`: Data,
    val notification: Notification,
    val to: String
)