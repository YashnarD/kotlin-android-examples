package com.example.mvvmarchitecture.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitecture.database.entity.UserEntity
import com.example.mvvmarchitecture.databinding.ItemUserBinding
import com.example.mvvmarchitecture.models.newsModel.Article
import com.example.mvvmarchitecture.utils.setImage

class UserAdapter : ListAdapter<Article, UserAdapter.Vh>(MyDiffUtill()) {

    inner class Vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(article: Article) {
            itemUserBinding.apply {
                tv.text = article.title
                article.urlToImage?.let { img.setImage(it) }
            }
        }
    }

    class MyDiffUtill : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.urlToImage == newItem.urlToImage
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}