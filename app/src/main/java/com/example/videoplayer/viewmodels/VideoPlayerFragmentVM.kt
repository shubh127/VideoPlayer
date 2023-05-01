package com.example.videoplayer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.repository.models.Video
import com.example.videoplayer.repository.VideosInfoRepo
import com.example.videoplayer.helper.SingleLiveEvent
import com.example.videoplayer.repository.models.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerFragmentVM @Inject constructor(
    private val repo: VideosInfoRepo
) : ViewModel() {

    fun getVideos() {
        viewModelScope.launch {
            repo.getVideos()
        }
    }

    fun getVideosLiveData(): SingleLiveEvent<NetworkResult<List<Video>>> {
        return repo.getVideosLiveData()
    }
}