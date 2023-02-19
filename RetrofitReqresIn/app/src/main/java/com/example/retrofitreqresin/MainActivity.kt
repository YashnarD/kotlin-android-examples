package com.example.retrofitreqresin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitreqresin.databinding.ActivityMainBinding
import com.example.retrofitreqresin.models.register.ReqRegister
import com.example.retrofitreqresin.models.register.ResErrorRegister
import com.example.retrofitreqresin.models.register.ResRegister
import com.example.retrofitreqresin.retrofit.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reqRegister = ReqRegister("eve.holt@reqres.in", "pistol")
        ApiClient.apiService
            .register(reqRegister)
            .enqueue(object : Callback<ResRegister> {
                override fun onResponse(call: Call<ResRegister>, response: Response<ResRegister>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                    } else if (response.code() == 400) {
                        val jsonString = response.errorBody()?.string()
                        val errorRegister =
                            Gson().fromJson(jsonString, ResErrorRegister::class.java)
                        Log.d(TAG, "onResponse: ${errorRegister.error}")
                    }
                }

                override fun onFailure(call: Call<ResRegister>, t: Throwable) {

                }

            })
    }
}