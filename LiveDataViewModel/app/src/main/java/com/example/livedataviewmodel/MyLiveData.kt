package com.example.livedataviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyLiveData {

    private var liveData = MutableLiveData<String>()

    fun getLiveData(): LiveData<String> {
        return liveData
    }

    fun setLiveData(value: String) {
        liveData.value = value
    }
}