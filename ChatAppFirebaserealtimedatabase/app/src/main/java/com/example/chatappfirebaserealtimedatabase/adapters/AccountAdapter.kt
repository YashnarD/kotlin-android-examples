package com.example.chatappfirebaserealtimedatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappfirebaserealtimedatabase.databinding.ItemAccountBinding
import com.example.chatappfirebaserealtimedatabase.models.Account
import com.squareup.picasso.Picasso

class AccountAdapter(var list: List<Account>, var listener: OnItemClickListener): RecyclerView.Adapter<AccountAdapter.Vh>() {

    inner class Vh(var itemAccountBinding: ItemAccountBinding): RecyclerView.ViewHolder(itemAccountBinding.root) {
        fun onBind(account: Account) {
            itemAccountBinding.apply {
                Picasso.get().load(account.photoUrl).into(image)
                name.text = account.displayName
            }
            itemView.setOnClickListener {
                listener.onItemClick(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemClick(account: Account)
    }
}