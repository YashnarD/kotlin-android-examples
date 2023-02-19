package com.example.flow.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flow.utils.SingleLiveEvent

class MyViewModel: ViewModel() {
    val liveEvent = SingleLiveEvent<String>()
}