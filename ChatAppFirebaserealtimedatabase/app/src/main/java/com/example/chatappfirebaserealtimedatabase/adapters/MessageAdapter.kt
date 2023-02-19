package com.example.chatappfirebaserealtimedatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappfirebaserealtimedatabase.databinding.ItemFromBinding
import com.example.chatappfirebaserealtimedatabase.databinding.ItemToBinding
import com.example.chatappfirebaserealtimedatabase.models.Message

class MessageAdapter(var currentUid: String, var list: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class FromVh(var itemFromBinding: ItemFromBinding) :
        RecyclerView.ViewHolder(itemFromBinding.root) {

        fun onBind(message: Message) {
            itemFromBinding.apply {
                messageTv.text = message.message
//                    date.text = message.date
            }
        }
    }

    inner class ToVh(var itemToBinding: ItemToBinding) :
        RecyclerView.ViewHolder(itemToBinding.root) {
        fun onBind(message: Message) {
            itemToBinding.apply {
                messageTv.text = message.message
//                date.text = message.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return FromVh(
                ItemFromBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ToVh(ItemToBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FromVh) {
            holder.onBind(list[position])
        } else {
            val toVh = holder as ToVh
            toVh.onBind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].from == currentUid) {
            return 1
        }
        return 2
    }

    override fun getItemCount(): Int = list.size
}