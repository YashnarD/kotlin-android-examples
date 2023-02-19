package com.example.paging3.repository

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.models.PhotoData
import com.example.paging3.retrofit.ApiClient
import com.example.paging3.retrofit.ApiService
import com.example.paging3.viewmodels.PAGE_SIZE
import kotlinx.coroutines.flow.catch

class PhotoDataSource(private val photoRepository: PhotoRepository) :
    PagingSource<Int, PhotoData>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoData> {
        val currentPage = params.key ?: 1
        return try {
            var loadResult: LoadResult.Page<Int, PhotoData>? = null
            photoRepository.getPhotos(currentPage)
                .catch {
                    loadResult = LoadResult.Page(emptyList(), null, null)
                }.collect {
                    if(it.isNotEmpty()) {
                        loadResult = LoadResult.Page(
                            it,
                            if (currentPage == 1) null else currentPage.minus(1),
                            if (it.isEmpty()) null else currentPage.plus(1)
                        )
                    } else {
                        loadResult = LoadResult.Page(
                            data = emptyList(),
                            null,
                            null
                        )
                    }
                }

            loadResult!!
        } catch (e: Exception) {
            LoadResult.Error(e.fillInStackTrace())
        }
    }
}