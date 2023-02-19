package com.example.pagingcustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingcustom.adapters.PaginationAdapter
import com.example.pagingcustom.databinding.ActivityMainBinding
import com.example.pagingcustom.models.Data
import com.example.pagingcustom.models.UserData
import com.example.pagingcustom.retrofit.ApiClient
import com.example.pagingcustom.utils.PaginationScrollListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var list: ArrayList<Data>
    private lateinit var paginationAdapter: PaginationAdapter

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    private var TOTAL_PAGES = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        paginationAdapter = PaginationAdapter(list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.adapter = paginationAdapter

        getUsers(currentPage)

        binding.rv.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                getNextUsers(currentPage)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    private fun getUsers(page: Int) {
        ApiClient.apiService.getUsers(page).enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful) {
                    list.addAll(response.body()?.data ?: emptyList())
                    paginationAdapter.notifyDataSetChanged()

                    if (currentPage <= TOTAL_PAGES) {
                        paginationAdapter.addLoading()
                    } else {
                        isLastPage = true
                    }
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {

            }

        })
    }

    private fun getNextUsers(page: Int) {
        ApiClient.apiService.getUsers(page).enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful) {
                    paginationAdapter.removeLoading()
                    isLoading = false
                    paginationAdapter.addAll(response.body()?.data ?: emptyList())

                    if(currentPage != TOTAL_PAGES) {
                        paginationAdapter.addLoading()
                    } else {
                        isLastPage = true
                    }
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {

            }

        })
    }
}