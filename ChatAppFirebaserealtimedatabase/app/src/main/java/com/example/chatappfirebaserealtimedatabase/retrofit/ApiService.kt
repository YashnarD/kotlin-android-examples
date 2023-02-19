package com.example.chatappfirebaserealtimedatabase.retrofit

import com.example.chatappfirebaserealtimedatabase.models.NotificationData
import com.example.chatappfirebaserealtimedatabase.models.ResNotificationData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers(
        "Content-Type:application/json",
        "Authorization:key=AAAANtRQ-vg:APA91bELJD66DjUeCIzyXD8vw3IVt3A_6P-fsm-7XZSROBSCuDyuA643vF9Q92D_OMzKQTuvYsRYKVQwVdSC9JBvk4raL-SN_4MoYTjh9yJmeOsqJPZoC_CvjbmOZBn48-3e3-r24d_Q"
    )
    @POST("fcm/send")
    fun sendNotification(@Body notificationData: NotificationData): Call<ResNotificationData>
}