package com.example.videoplayer.repository

import com.example.videoplayer.helper.SingleLiveEvent
import com.example.videoplayer.repository.models.NetworkResult
import com.example.videoplayer.repository.models.Video
import com.example.videoplayer.repository.networkSource.ApiService
import org.json.JSONObject
import javax.inject.Inject

class VideosInfoRepo @Inject constructor(private val apiService: ApiService) {
    private val videoLiveData = SingleLiveEvent<NetworkResult<List<Video>>>()

    suspend fun getVideos() {
        videoLiveData.postValue(NetworkResult.Loading())
        val response = apiService.getVideos()
        if (response.isSuccessful && response.body() != null) {
            val videos = response.body()!!.sortedBy { it.publishedAt }
            videoLiveData.postValue(NetworkResult.Success(videos))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            videoLiveData.postValue(NetworkResult.Error(errorObj.getString("Something Went Wrong")))
        } else {
            videoLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

    fun getVideosLiveData(): SingleLiveEvent<NetworkResult<List<Video>>> {
        return videoLiveData
    }
}