package com.example.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanager.db.AppDatabase
import com.example.workmanager.db.entity.UserEntity
import com.example.workmanager.models.User
import com.example.workmanager.retrofit.ApiClient
import com.example.workmanager.retrofit.ApiService
import com.example.workmanager.utils.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyWorker(var context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    lateinit var appDatabase: AppDatabase
    lateinit var apiService: ApiService
    lateinit var networkHelper: NetworkHelper

    override fun doWork(): Result {
        apiService = ApiClient.apiService
        appDatabase = AppDatabase.getInstance(context)
        networkHelper = NetworkHelper(context)

        if (networkHelper.isNetworkConnected()) {
            apiService.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.forEach {
                            val userEntity = UserEntity(it.email, it.id, it.name, it.phone)
                            appDatabase.userDao().addUser(userEntity)
                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {

                }

            })
        }
        return Result.success()
    }
}