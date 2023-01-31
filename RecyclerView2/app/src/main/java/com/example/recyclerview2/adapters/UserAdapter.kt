package com.example.recyclerview2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.R
import com.example.recyclerview2.databinding.ItemUserBinding
import com.example.recyclerview2.models.User
import com.example.recyclerview2.utils.MyItemTouchHelper
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(var userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>(),
    MyItemTouchHelper {

    inner class MyViewHolder(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.itemUserBinding.tv1.text = user.username
        holder.itemUserBinding.tv2.text = user.password
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int = userList.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(userList, i, i + 1)
            }
        } else {
            for (i in toPosition downTo fromPosition) {
                Collections.swap(userList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }
}