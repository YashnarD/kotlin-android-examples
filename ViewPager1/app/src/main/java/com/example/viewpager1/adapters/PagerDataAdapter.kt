package com.example.viewpager1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.viewpager1.fragments.PagerDataFragment.Companion.newInstance
import com.example.viewpager1.models.PagerData


class PagerDataAdapter(fm: FragmentManager, private val pagerDataList: List<PagerData>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return newInstance(pagerDataList[position])
    }

    override fun getCount(): Int {
        return pagerDataList.size
    }
}
