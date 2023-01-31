package com.example.viewpager1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.viewpager1.databinding.PagerViewBinding
import com.squareup.picasso.Picasso

class ImageAdapter(var list: ArrayList<String>): PagerAdapter() {

    override fun getCount(): Int = list.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = PagerViewBinding.inflate(LayoutInflater.from(container.context), container, false)
        Picasso.get().load(list[position]).into(binding.image)
        container.addView(binding.root)
        return binding.root
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab $position"
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}