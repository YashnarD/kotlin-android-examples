package com.example.mvvmarchitecture.models.newsModel

data class Headlines(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)