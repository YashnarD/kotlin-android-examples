package com.example.expandablelistview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ItemUserBinding
import com.example.expandablelistview.models.User
import com.squareup.picasso.Picasso

class MySpinnerAdapter(context: Context, val userList: ArrayList<User>) :
    ArrayAdapter<User>(context, R.layout.item_user, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemUserBinding: ItemUserBinding
        if (convertView == null) {
            itemUserBinding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            itemUserBinding = ItemUserBinding.bind(convertView)
        }
        itemUserBinding.nameTv.text = userList[position].name
        Picasso.get().load(userList[position].image).into(itemUserBinding.image)
        return itemUserBinding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemUserBinding: ItemUserBinding
        if (convertView == null) {
            itemUserBinding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            itemUserBinding = ItemUserBinding.bind(convertView)
        }
        itemUserBinding.nameTv.text = userList[position].name
        Picasso.get().load(userList[position].image).into(itemUserBinding.image)
        return itemUserBinding.root
    }

    override fun getCount(): Int = userList.size
}