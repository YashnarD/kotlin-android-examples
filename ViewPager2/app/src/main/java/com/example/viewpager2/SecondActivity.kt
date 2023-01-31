package com.example.viewpager2

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.viewpager2.adapters.PagerFragmentAdapter
import com.example.viewpager2.databinding.ActivitySecondBinding
import com.example.viewpager2.models.NewsData
import com.google.android.material.bottomnavigation.BottomNavigationView


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var list: ArrayList<NewsData>
    private lateinit var pagerFragmentAdapter: PagerFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        pagerFragmentAdapter = PagerFragmentAdapter(this, list)
        binding.viewPager.adapter = pagerFragmentAdapter
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.bottomNavigationView.selectedItemId = R.id.home
                } else if (position == 1) {
                    binding.bottomNavigationView.selectedItemId = R.id.dashboard
                } else if (position == 2) {
                    binding.bottomNavigationView.selectedItemId = R.id.notifications
                } else if (position == 3) {
                    binding.bottomNavigationView.selectedItemId = R.id.profile
                }
            }
        })

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val itemId = item.itemId
                when(itemId) {
                    R.id.home -> binding.viewPager.currentItem = 0
                    R.id.dashboard -> binding.viewPager.currentItem = 1
                    R.id.notifications -> binding.viewPager.currentItem = 2
                    R.id.profile -> binding.viewPager.currentItem = 3
                }
                return true
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