package com.example.videoplayer.repository

import android.util.Log
import com.example.videoplayer.helper.SingleLiveEvent
import com.example.videoplayer.repository.models.NetworkResult
import com.example.videoplayer.repository.models.Video
import com.example.videoplayer.repository.networkSource.ApiService
import org.json.JSONObject
import javax.inject.Inject

class VideosInfoRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getVideos() {
        val response = apiService.getVideos()
        if (response.isSuccessful && response.body() != null) {
            Log.d(">>>>>", response.body().toString())
        } else if (response.errorBody() != null) {
            Log.d(">>>>>", response.errorBody().toString())
        } else {
            Log.d(">>>>>","fail")
        }
    }
}