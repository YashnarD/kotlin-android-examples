package com.example.room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemContactBinding
import com.example.room.entities.Contact

class ContactAdapter(var list: List<Contact>, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ContactAdapter.Vh>() {

    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact, position: Int) {
            itemContactBinding.apply {
                tv1.text = contact.name
                tv2.text = contact.number

                editBtn.setOnClickListener {
                    onItemClickListener.onItemEdit(contact, position)
                }
                deleteBtn.setOnClickListener {
                    onItemClickListener.onItemDelete(contact, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemEdit(contact: Contact, position: Int)
        fun onItemDelete(contact: Contact, position: Int)
    }
}