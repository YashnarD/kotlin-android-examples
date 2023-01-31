package com.example.viewpager2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2.databinding.ItemNewsBinding
import com.example.viewpager2.models.NewsData
import com.squareup.picasso.Picasso


class PagerRvAdapter(private val list: List<NewsData>) :
    RecyclerView.Adapter<PagerRvAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(list[position].imgUrl).into(holder.binding.image)
        holder.binding.titleTv.text = list[position].title
        holder.binding.descTv.text = list[position].description
        holder.binding.dateTv.text = list[position].date
        holder.binding.titleTv.isSelected = true
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)
}
