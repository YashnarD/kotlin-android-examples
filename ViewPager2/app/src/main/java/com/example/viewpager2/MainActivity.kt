package com.example.viewpager2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.viewpager2.adapters.PagerFragmentAdapter
import com.example.viewpager2.databinding.ActivityMainBinding
import com.example.viewpager2.models.NewsData
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<NewsData>
    private lateinit var pagerFragmentAdapter: PagerFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        pagerFragmentAdapter = PagerFragmentAdapter(this, list)
        binding.viewPager.adapter = pagerFragmentAdapter

        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position -> tab.text = "Tab $position" }.attach()
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(this@MainActivity, position.toString() + "", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun loadData() {
        list = ArrayList()
        list.add(
            NewsData(
                "https://storage.kun.uz/source/7/XWy-f92SWpomQ2q9qqYxeHg8q0AuRXR8.jpg",
                "Тўрт сабаб. Биз нега илмда кундан кунга орқада қоляпмиз?",
                "Зангиотадаги коронавирусга ихтисослашган 2-клиник шифохонанинг Радиология бўлими мудири, Буюк Британиянинг Абердин университети магистри Маъруфжон Салоҳиддинов Kun.uz'га тақдим этган мақоласида олий таълим ва илмий соҳадаги муаммолар ҳақида сўз юритиб, қатор ечимларни таклиф қилади.",
                "13:47 / 20.06.2021"
            )
        )
        list.add(
            NewsData(
                "https://storage.kun.uz/source/thumbnails/_medium/7/rgZvsRaHgD0m3xTxrWG4Egxrn96nPyVg_medium.jpg",
                "Тўрт сабаб. Биз нега илмда кундан кунга орқада қоляпмиз?",
                "Зангиотадаги коронавирусга ихтисослашган 2-клиник шифохонанинг Радиология бўлими мудири, Буюк Британиянинг Абердин университети магистри Маъруфжон Салоҳиддинов Kun.uz'га тақдим этган мақоласида олий таълим ва илмий соҳадаги муаммолар ҳақида сўз юритиб, қатор ечимларни таклиф қилади.",
                "13:47 / 20.06.2021"
            )
        )
        list.add(
            NewsData(
                "https://storage.kun.uz/source/thumbnails/_medium/7/0qO1tfaU696xpTON1JzeFMWye7o0KcFh_medium.jpg",
                "Тўрт сабаб. Биз нега илмда кундан кунга орқада қоляпмиз?",
                "Зангиотадаги коронавирусга ихтисослашган 2-клиник шифохонанинг Радиология бўлими мудири, Буюк Британиянинг Абердин университети магистри Маъруфжон Салоҳиддинов Kun.uz'га тақдим этган мақоласида олий таълим ва илмий соҳадаги муаммолар ҳақида сўз юритиб, қатор ечимларни таклиф қилади.",
                "13:47 / 20.06.2021"
            )
        )
        list.add(
            NewsData(
                "https://storage.kun.uz/source/thumbnails/_medium/7/VCifH9DHqmEY7DZYYGXV5XPiTD7pm0W4_medium.jpg",
                "Тўрт сабаб. Биз нега илмда кундан кунга орқада қоляпмиз?",
                "Зангиотадаги коронавирусга ихтисослашган 2-клиник шифохонанинг Радиология бўлими мудири, Буюк Британиянинг Абердин университети магистри Маъруфжон Салоҳиддинов Kun.uz'га тақдим этган мақоласида олий таълим ва илмий соҳадаги муаммолар ҳақида сўз юритиб, қатор ечимларни таклиф қилади.",
                "13:47 / 20.06.2021"
            )
        )
    }
}