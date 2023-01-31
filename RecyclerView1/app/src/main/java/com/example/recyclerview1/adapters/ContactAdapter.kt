package com.example.recyclerview1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview1.databinding.ItemContactBinding
import com.example.recyclerview1.models.Contact

class ContactAdapter(
    var contactList: ArrayList<Contact>,
    var listener: OnItemContactClickListener
) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root)

    interface OnItemContactClickListener {
        fun onItemContactClick(contact: Contact)
        fun onItemDeleteClick(contact: Contact, position: Int)
        fun onItemEditClick(contact: Contact, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.itemContactBinding.nameTv.text = contact.name
        holder.itemContactBinding.numberTv.text = contact.number

        holder.itemView.setOnClickListener {
            listener.onItemContactClick(contact)
        }

        holder.itemContactBinding.deleteBtn.setOnClickListener {
            listener.onItemDeleteClick(contact, position)
        }

        holder.itemContactBinding.editBtn.setOnClickListener {
            listener.onItemEditClick(contact, position)
        }
    }

    override fun getItemCount(): Int = contactList.size
}