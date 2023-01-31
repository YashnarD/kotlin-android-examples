package com.example.dialogs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dialogs.databinding.ItemContactBinding
import com.example.dialogs.models.Contact

class ContactAdapter(var contactList: ArrayList<Contact>, var listener: OnItemClickListener) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {


    inner class MyViewHolder(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root)

    interface OnItemClickListener {
        fun onItemClick(contact: Contact, position: Int, itemView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemContactBinding.tv1.text = contactList[position].name
        holder.itemContactBinding.tv2.text = contactList[position].number

        holder.itemView.setOnClickListener {
                listener.onItemClick(contactList[position], position, it)
        }
    }

    override fun getItemCount(): Int = contactList.size
}