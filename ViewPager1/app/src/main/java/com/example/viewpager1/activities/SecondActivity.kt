package com.example.viewpager1.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.viewpager1.R
import com.example.viewpager1.adapters.MyFragmentPagerAdapter
import com.example.viewpager1.databinding.ActivitySecondBinding
import com.example.viewpager1.databinding.ItemTabBinding
import com.example.viewpager1.utils.CubeInScalingTransformation
import com.google.android.material.tabs.TabLayout

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var list: ArrayList<String>
    private lateinit var titleList: ArrayList<String>
    private lateinit var myFragmentPagerAdapter: MyFragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        myFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, list, titleList)
        binding.viewPager.adapter = myFragmentPagerAdapter
        binding.viewPager.setPageTransformer(true, CubeInScalingTransformation())
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setTabs()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.tv.setTextColor(Color.WHITE)
                itemTabBinding.circle.visibility = View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.tv.setTextColor(Color.parseColor("#AFADAD"))
                itemTabBinding.circle.visibility = View.INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setTabs() {
        val tabCount = binding.tabLayout.tabCount
        for(i in 0 until tabCount) {
            val tab: TabLayout.Tab? = binding.tabLayout.getTabAt(i)
            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
            itemTabBinding.tv.text = "Tab $i"
            if(i == 0) {
                itemTabBinding.circle.visibility = View.VISIBLE
                itemTabBinding.tv.setTextColor(Color.WHITE)
            } else {
                itemTabBinding.circle.visibility = View.INVISIBLE
                itemTabBinding.tv.setTextColor(Color.parseColor("#AFADAD"))
            }
            tab?.customView = itemTabBinding.root
        }
    }

    private fun loadData() {
        list = ArrayList()
        titleList = ArrayList()

        for(i in 0..25)
        {
            list.add("https://storage.kun.uz/source/thumbnails/_medium/7/rgZvsRaHgD0m3xTxrWG4Egxrn96nPyVg_medium.jpg")
            list.add("https://storage.kun.uz/source/thumbnails/_medium/7/VCifH9DHqmEY7DZYYGXV5XPiTD7pm0W4_medium.jpg")
            list.add("https://storage.kun.uz/source/thumbnails/_medium/7/aPaY8Ocq4TP27h6rpqFkZ6qYuvMGnSdK_medium.jpg")
            list.add("https://storage.kun.uz/source/thumbnails/_medium/7/t2a8_trI_C3MDn-sqOjtI1B2hTvtmkK7_medium.jpg")
        }

        for(i in 0..100) {
            titleList.add("Yashnar $i")
        }
    }
}