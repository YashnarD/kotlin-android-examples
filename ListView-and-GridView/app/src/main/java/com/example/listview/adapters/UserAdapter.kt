package com.example.listview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.listview.R
import com.example.listview.databinding.ItemUserBinding
import com.example.listview.models.User

class UserAdapter(context: Context, var userList: ArrayList<User>) :
    ArrayAdapter<User>(context, R.layout.item_user, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemUserBinding: ItemUserBinding

        if (convertView == null) {
            itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        } else {
            itemUserBinding = ItemUserBinding.bind(convertView)
        }
        itemUserBinding.usernameTv.text = userList[position].username
        itemUserBinding.passwordTv.text = userList[position].password
        return itemUserBinding.root
    }

    override fun getCount(): Int = userList.size
}