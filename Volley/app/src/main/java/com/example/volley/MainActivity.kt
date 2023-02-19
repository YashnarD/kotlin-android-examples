package com.example.volley

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.volley.adapters.UserAdapter
import com.example.volley.databinding.ActivityMainBinding
import com.example.volley.models.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var userAdapter: UserAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()
        binding.rv.adapter = userAdapter

        requestQueue = Volley.newRequestQueue(this)

//        binding.button.setOnClickListener {
//            val imageRequest = ImageRequest(
//                "https://i.imgur.com/Nwk25LA.jpg",
//                { response -> binding.image.setImageBitmap(response) },
//                0,
//                0,
//                ImageView.ScaleType.CENTER_CROP,
//                Bitmap.Config.ARGB_8888
//            ) { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
//        }

//            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
//                "https://jsonplaceholder.typicode.com/users/1",
//                null,
//                { response ->
//                    val name = response?.get("name")
//                    val user = Gson().fromJson(response.toString(), User::class.java)
//                    Log.d(TAG, "onResponse: $user")
//                    Log.d(TAG, "onResponse: $name")
//                }
//            ) { error -> Log.d(TAG, "onErrorResponse: ${error?.message}") }


        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users",
                null, { response ->
                    val str = response.toString()
                    val type = object : TypeToken<List<User>>() {}.type
                    val userList = Gson().fromJson<List<User>>(str, type)
                    userAdapter.submitList(userList)
                }
            ) { error -> Log.d(TAG, "onErrorResponse: ${error.message}") }

//        requestQueue.add(imageRequest)
//        requestQueue.add(jsonObjectRequest)
        requestQueue.add(jsonArrayRequest)
    }
}