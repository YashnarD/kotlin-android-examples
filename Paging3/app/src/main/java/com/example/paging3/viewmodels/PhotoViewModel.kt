package com.example.paging3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.paging3.repository.PhotoDataSource
import com.example.paging3.repository.PhotoRepository
import com.example.paging3.retrofit.ApiClient

const val PAGE_SIZE = 1
class PhotoViewModel: ViewModel() {

    val flow = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PhotoDataSource(PhotoRepository(ApiClient.apiService))
    }.flow.cachedIn(viewModelScope)
}