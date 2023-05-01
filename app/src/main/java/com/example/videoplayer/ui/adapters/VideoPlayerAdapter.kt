package com.example.videoplayer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.databinding.ViewVideoPlayerBinding
import com.example.videoplayer.repository.models.Video
import com.example.videoplayer.helper.interfaces.OnPageChangeListener
import com.example.videoplayer.ui.viewholders.VideoPlayerViewHolder

class VideoPlayerAdapter(
    private val data: List<Video>,
    private val listener: OnPageChangeListener
) : RecyclerView.Adapter<VideoPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPlayerViewHolder {
        val binding =
            ViewVideoPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoPlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoPlayerViewHolder, position: Int) {
        holder.bind(data, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
