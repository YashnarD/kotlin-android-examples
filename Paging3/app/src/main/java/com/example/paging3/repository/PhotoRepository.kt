package com.example.paging3.repository

import com.example.paging3.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class PhotoRepository(private val apiService: ApiService) {

    fun getPhotos(page: Int) = flow { emit(apiService.getRandomPhotos(page = page)) }
}