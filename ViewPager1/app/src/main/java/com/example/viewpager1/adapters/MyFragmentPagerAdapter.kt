package com.example.viewpager1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.viewpager1.fragments.BlankFragment.Companion.newInstance

class MyFragmentPagerAdapter(
    fm: FragmentManager,
    private val list: List<String>,
    private val titleList: List<String>
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return newInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }
}
