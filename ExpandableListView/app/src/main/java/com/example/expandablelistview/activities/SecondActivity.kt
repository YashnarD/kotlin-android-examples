package com.example.expandablelistview.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expandablelistview.adapters.MySpinnerAdapter
import com.example.expandablelistview.databinding.ActivitySecondBinding
import com.example.expandablelistview.models.User


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var mySpinnerAdapter: MySpinnerAdapter
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadList()

        mySpinnerAdapter = MySpinnerAdapter(this, userList)
        binding.spinner.adapter = mySpinnerAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@SecondActivity,
                    userList[position].name,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.button.setOnClickListener { v ->
            val selectedItemPosition = binding.spinner.selectedItemPosition
            val (_, name) = userList.get(selectedItemPosition)
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadList() {
        userList = ArrayList()
        userList.add(User("https://square.github.io/picasso/static/sample.png", "Bahodir"))
        userList.add(
            User(
                "https://storage.kun.uz/source/thumbnails/_medium/7/DrH72BI9fMJyYMS8LGlX1Oq21Umbp83R_medium.jpg",
                "Sanjar"
            )
        )
        userList.add(
            User(
                "https://storage.kun.uz/source/thumbnails/_medium/7/ZdFjh9SSqtzwmQyd8Tg3Xq2LL4P3lFR8_medium.jpg",
                "Samandar"
            )
        )
        userList.add(
            User(
                "https://storage.kun.uz/source/thumbnails/_medium/7/Iv3kx8JJKcNIS4eMEog5QOU-X4ZV5QTI_medium.jpg",
                "Fayzullo"
            )
        )
    }
}