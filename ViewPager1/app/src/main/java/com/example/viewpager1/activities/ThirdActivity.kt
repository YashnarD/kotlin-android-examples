package com.example.viewpager1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpager1.R
import com.example.viewpager1.adapters.PagerDataAdapter
import com.example.viewpager1.databinding.ActivityThirdBinding
import com.example.viewpager1.models.PagerData


class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var pagerDataList: ArrayList<PagerData>
    private lateinit var pagerDataAdapter: PagerDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPages()

        pagerDataAdapter = PagerDataAdapter(supportFragmentManager, pagerDataList)
        binding.viewPager.adapter = pagerDataAdapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun loadPages() {
        pagerDataList = ArrayList()
        pagerDataList.add(
            PagerData(
                R.drawable.image1, "Xush kelibsiz",
                """
                        Kim ko‘rubdur, ey ko‘ngul, ahli jahondin yaxshilig‘, 
                        Kimki, ondin yaxshi yo‘q, ko‘z tutma ondin yaxshilig‘
                        """.trimIndent()
            )
        )
        pagerDataList.add(
            PagerData(
                R.drawable.image2, "Hikoyalar dunyosi",
                """
                Gar zamonni nayf qilsam ayb qilma, ey rafiq, 
                Ko‘rmadim hargiz, netoyin, bu zamondin yaxshilig‘ ! 
                """.trimIndent()
            )
        )
        pagerDataList.add(
            PagerData(
                R.drawable.image3, "Kitob ortidan..",
                """Dilrabolardin yomonliq keldi mahzun ko‘ngluma,
 Kelmadi jonimg'a hech oromi jondin yaxshilig'."""
            )
        )
        pagerDataList.add(
            PagerData(
                R.drawable.image4, "Bizga qo`shiling",
                """Ey ko‘ngul, chun yaxshidin ko‘rdung yamonliq asru ko‘p,
 Emdi ko‘z tutmoq ne ma’ni har yamondin yaxshilig'?"""
            )
        )
    }
}