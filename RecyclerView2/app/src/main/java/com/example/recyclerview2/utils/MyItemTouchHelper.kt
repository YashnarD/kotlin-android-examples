package com.example.recyclerview2.utils

interface MyItemTouchHelper {

    // drag and drop
    fun onItemMove(fromPosition: Int, toPosition: Int)

    // swipe
    fun onItemDismiss(position: Int)
}