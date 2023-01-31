package com.example.viewpager1.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpager1.adapters.ImageAdapter
import com.example.viewpager1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<String>
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        imageAdapter = ImageAdapter(list)
        binding.viewPager.adapter = imageAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        handler = Handler()
        handler.postDelayed(runnable, 2000)
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            if (binding.viewPager.currentItem === list.size - 1) {
                binding.viewPager.currentItem = 0
            } else {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
            handler.postDelayed(this, 2000)
        }
    }

    private fun loadData() {
        list = ArrayList()
        list.add("https://storage.kun.uz/source/thumbnails/_medium/7/rgZvsRaHgD0m3xTxrWG4Egxrn96nPyVg_medium.jpg")
        list.add("https://storage.kun.uz/source/thumbnails/_medium/7/VCifH9DHqmEY7DZYYGXV5XPiTD7pm0W4_medium.jpg")
        list.add("https://storage.kun.uz/source/thumbnails/_medium/7/aPaY8Ocq4TP27h6rpqFkZ6qYuvMGnSdK_medium.jpg")
        list.add("https://storage.kun.uz/source/thumbnails/_medium/7/t2a8_trI_C3MDn-sqOjtI1B2hTvtmkK7_medium.jpg")
    }
}