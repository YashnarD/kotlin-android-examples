package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemMarvelBinding
import com.example.retrofit.models.MarvelData
import com.squareup.picasso.Picasso

class MarvelAdapter : ListAdapter<MarvelData, MarvelAdapter.Vh>(MyDiffUtilll()) {

    inner class Vh(var itemMarvelBinding: ItemMarvelBinding) :
        RecyclerView.ViewHolder(itemMarvelBinding.root) {

        fun onBind(marvelData: MarvelData) {
            itemMarvelBinding.apply {
                Picasso.get().load(marvelData.imageurl).into(image)
                tv.text = marvelData.name
            }
        }
    }

    class MyDiffUtilll : DiffUtil.ItemCallback<MarvelData>() {
        override fun areItemsTheSame(oldItem: MarvelData, newItem: MarvelData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MarvelData, newItem: MarvelData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}