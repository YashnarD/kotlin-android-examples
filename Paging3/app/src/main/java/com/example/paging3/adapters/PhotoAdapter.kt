package com.example.paging3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3.R
import com.example.paging3.databinding.ItemPhotoBinding
import com.example.paging3.models.PhotoData
import com.squareup.picasso.Picasso

class PhotoAdapter(val context: Context) :
    PagingDataAdapter<PhotoData, PhotoAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<PhotoData>() {
        override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem == newItem
        }
    }

    inner class Vh(var itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {
        fun onBind(photoData: PhotoData) {
            itemPhotoBinding.apply {
                Glide.with(context).load(photoData.urls.small)
                    .placeholder(R.drawable.ic_launcher_background).into(img)
//                Picasso.get().load(photoData.urls.small)
//                    .placeholder(R.drawable.ic_launcher_background).into(img)
            }
        }
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}