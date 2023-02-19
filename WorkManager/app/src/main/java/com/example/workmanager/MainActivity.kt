package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.workmanager.databinding.ActivityMainBinding
import com.example.workmanager.db.AppDatabase
import com.example.workmanager.db.entity.UserEntity
import com.example.workmanager.models.User
import com.example.workmanager.retrofit.ApiClient
import com.example.workmanager.retrofit.ApiService
import com.example.workmanager.utils.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    private lateinit var networkHelper: NetworkHelper
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkHelper = NetworkHelper(this)
        appDatabase = AppDatabase.getInstance(this)
        apiService = ApiClient.apiService

        if(networkHelper.isNetworkConnected()) {
            apiService.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if(response.isSuccessful) {
                        val body = response.body()
                        body?.forEach {
                            val userEntity = UserEntity(it.email, it.id, it.name, it.phone)
                            appDatabase.userDao().addUser(userEntity)
                        }
                        // show recycler view
                        // appDatabase.userDao().getAllUsers()
                    }
                    else {
                        // appDatabase.userDao().getAllUsers()
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {

                }

            })
        } else {
            // appDatabase.userDao().getAllUsers()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val workRequest2 = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints).build()

        // val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this)
            .enqueue(workRequest2)
    }
}