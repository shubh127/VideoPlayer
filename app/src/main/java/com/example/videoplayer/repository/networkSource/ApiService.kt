package com.example.videoplayer.repository.networkSource

import com.example.videoplayer.repository.models.Video
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/videos")
    suspend fun getVideos(): Response<List<Video>>
}