package com.example.viewpager2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewpager2.NewsFragment.Companion.newInstance
import com.example.viewpager2.models.NewsData


class PagerFragmentAdapter(fragmentActivity: FragmentActivity, private val list: List<NewsData>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return newInstance(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
