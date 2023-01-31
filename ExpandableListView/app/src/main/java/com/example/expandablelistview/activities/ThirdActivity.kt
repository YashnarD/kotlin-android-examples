package com.example.expandablelistview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var list: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.autoComplete.setAdapter(arrayAdapter)
    }

    private fun loadData() {
        list = ArrayList()
        list.add("Samandar")
        list.add("Sanjar")
        list.add("Sardor")
        list.add("Sarvar")
    }
}