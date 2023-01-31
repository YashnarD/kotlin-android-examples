package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.example.listview.databinding.ActivityMainBinding
import com.example.listview.databinding.ItemUserBinding
import com.example.listview.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userList: ArrayList<User>
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayout = findViewById(R.id.layout)
        loadUsers()

        for (i in userList.indices) {
            val itemUserBinding = ItemUserBinding.inflate(layoutInflater)
            itemUserBinding.usernameTv.text = userList[i].username
            itemUserBinding.passwordTv.text = userList[i].password
            val j = i
            itemUserBinding.root.setOnClickListener {
                Toast.makeText(
                    this,
                    userList[j].username + "->" + userList[j].password,
                    Toast.LENGTH_SHORT
                ).show()
            }
            linearLayout.addView(itemUserBinding.root)
        }
    }

    private fun loadUsers() {
        userList = ArrayList()
        for (i in 0 until 100) {
            userList.add(User("Sanjar -> " + i, "123" + i))
        }
    }
}