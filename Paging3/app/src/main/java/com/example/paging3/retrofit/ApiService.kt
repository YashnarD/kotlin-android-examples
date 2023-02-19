package com.example.paging3.retrofit

import com.example.paging3.models.PhotoData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos/random")
    suspend fun getRandomPhotos(
        @Query("client_id") clientId: String = "zBhoa66Dd-IGCclQlxW23Xwb78dpZYAOLeld4ppfa1k",
        @Query("count") count: Int = 100,
        @Query("page") page: Int
    ) : List<PhotoData>
}