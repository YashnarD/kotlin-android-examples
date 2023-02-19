package com.example.mvvmarchitecture.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun ImageView.setImage(url: String) {
    Picasso.get().load(url).into(this)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}