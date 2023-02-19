package com.example.mvcarchitecture.model

import java.util.*
import kotlin.collections.ArrayList

class Model : Observable() {

    var list: ArrayList<Int> = ArrayList(3)

    init {
        list.add(0)
        list.add(0)
        list.add(0)
    }

    fun getValueAtIndex(index: Int): Int {
        return list[index]
    }

    fun setValueAtIndex(index: Int) {
        list[index] += 1
        setChanged()
        notifyObservers()
    }
}