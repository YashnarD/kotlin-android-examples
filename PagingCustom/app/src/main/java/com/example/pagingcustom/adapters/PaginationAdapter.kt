package com.example.pagingcustom.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingcustom.databinding.ItemProgressBinding
import com.example.pagingcustom.databinding.ItemUserBinding
import com.example.pagingcustom.models.Data
import com.squareup.picasso.Picasso

class PaginationAdapter(var list: ArrayList<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded = false
    private var LOADING = 0
    private var DATA = 1

    fun addAll(otherList: List<Data>) {
        list.addAll(otherList)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoadingAdded = true
    }

    fun removeLoading() {
        isLoadingAdded = false
    }

    inner class UserVh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {
        fun onBind(data: Data) {
            itemUserBinding.apply {
                Picasso.get().load(data.avatar).into(image)
                tv.text = data.first_name
            }
        }
    }

    inner class ProgressVh(var itemProgressBinding: ItemProgressBinding) :
        RecyclerView.ViewHolder(itemProgressBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LOADING) {
            return ProgressVh(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return UserVh(
                ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == DATA) {
            val userVh = holder as UserVh
            userVh.onBind(list[position])
        } else {
            val progressVh = holder as ProgressVh
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == list.size - 1 && isLoadingAdded) {
            return LOADING
        }
        return DATA
    }
}