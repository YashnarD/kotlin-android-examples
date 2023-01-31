package com.example.listview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listview.databinding.ItemUserBinding
import com.example.listview.models.User

class UserBaseAdapter(var userList: ArrayList<User>): BaseAdapter() {

    override fun getCount(): Int = userList.size

    override fun getItem(p0: Int): Any = userList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemUserBinding: ItemUserBinding

        if(convertView == null) {
            itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent?.context))
        } else {
            itemUserBinding = ItemUserBinding.bind(convertView)
        }

        itemUserBinding.usernameTv.text = userList[position].username
        itemUserBinding.passwordTv.text = userList[position].password
        return itemUserBinding.root
    }
}