package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import com.example.listview.adapters.UserBaseAdapter
import com.example.listview.databinding.ActivitySecondBinding
import com.example.listview.models.User

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var userList: ArrayList<User>
    private lateinit var userBaseAdapter: UserBaseAdapter
    private var clickPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadStrings()

        userBaseAdapter = UserBaseAdapter(userList)
        binding.listView.adapter = userBaseAdapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            clickPosition = position
            binding.edit1.setText(userList[position].username)
            binding.edit2.setText(userList[position].password)
        }

        binding.saveBtn.setOnClickListener {
            if(clickPosition != -1) {
                val newUsername = binding.edit1.text.toString()
                val newPassword = binding.edit2.text.toString()

                userList[clickPosition].username = newUsername
                userList[clickPosition].password = newPassword
                clickPosition = -1
            } else {
                val username = binding.edit1.text.toString()
                val password = binding.edit2.text.toString()

                val user = User(username, password)
                userList.add(user)
            }
            userBaseAdapter.notifyDataSetChanged()
            binding.edit1.setText("")
            binding.edit2.setText("")
        }
    }

    private fun loadStrings() {
        userList = ArrayList()
    }
}