package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.adapters.MarvelAdapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.models.MarvelData
import com.example.retrofit.retrofit.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    private lateinit var marvelAdapter: MarvelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marvelAdapter = MarvelAdapter()
        binding.rv.adapter = marvelAdapter

//        ApiClient.apiService(this).getMarvelsData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                marvelAdapter.submitList(it)
//            }

        ApiClient.apiService(this)
            .getMarvelsData().enqueue(object : Callback<List<MarvelData>> {
                override fun onResponse(
                    call: Call<List<MarvelData>>,
                    response: Response<List<MarvelData>>
                ) {
                    if (response.isSuccessful) {
                        marvelAdapter.submitList(response.body())
                    }
                }

                override fun onFailure(call: Call<List<MarvelData>>, t: Throwable) {

                }
            })

    }
}