package com.example.viewpager2.models

import java.io.Serializable

data class NewsData(var imgUrl: String, var title: String, var description: String, var date: String) :
    Serializable