package com.example.sqlite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.ItemContactBinding
import com.example.sqlite.models.Contact

class ContactAdapter(var list: List<Contact>, var listener: OnItemClickListener) :
    RecyclerView.Adapter<ContactAdapter.Vh>() {

    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact, position: Int) {
            itemContactBinding.apply {
                tv1.text = contact.name
                tv2.text = contact.number

                edit.setOnClickListener {
                    listener.onItemEdit(contact, position)
                }

                delete.setOnClickListener {
                    listener.onItemDelete(contact, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val contact = list[position]
        holder.onBind(contact, position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemDelete(contact: Contact, position: Int)
        fun onItemEdit(contact: Contact, position: Int)
    }
}