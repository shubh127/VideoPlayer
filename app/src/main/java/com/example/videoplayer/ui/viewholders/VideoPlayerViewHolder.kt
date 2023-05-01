package com.example.videoplayer.ui.viewholders

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.R
import com.example.videoplayer.databinding.ViewVideoPlayerBinding
import com.example.videoplayer.helper.interfaces.OnPageChangeListener
import com.example.videoplayer.repository.models.Video
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import io.noties.markwon.Markwon

class VideoPlayerViewHolder(private var binding: ViewVideoPlayerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var isPlaying = false
    private var exoPlayer: ExoPlayer? = null
    fun bind(list: List<Video>, position: Int, listener: OnPageChangeListener) {
        val data = list[position]
        val markwon = Markwon.create(binding.root.context)

        exoPlayer = ExoPlayer.Builder(binding.pvVideo.context).build()
        binding.pvVideo.player = exoPlayer?.apply {
            setMediaItem(MediaItem.fromUri(data.hlsURL))
            playWhenReady = true
            prepare()
            pause()
        }
        markwon.setMarkdown(binding.tvDesc, data.description)

        setUpClickListeners(listener, position, list.size)
    }

    private fun setUpClickListeners(listener: OnPageChangeListener, position: Int, size: Int) {
        binding.ibPlayPause.setOnClickListener {
            //play/pause button click
            handlePlayPause()
        }

        binding.ibNext.setOnClickListener {
            //on next click
            if (position < size) {
                isPlaying = false
                binding.ibPlayPause.setImageResource(R.drawable.play)
                exoPlayer?.pause()
                listener.onNextPageClicked()
            }
        }

        binding.ibPrevious.setOnClickListener {
            //previous button click
            if (position > 0) {
                isPlaying = false
                binding.ibPlayPause.setImageResource(R.drawable.play)
                exoPlayer?.pause()
                listener.onPrevPageClicked()
            }
        }

        binding.pvVideo.setOnClickListener {
            //video player click to show hide the play/pause, next, prev buttons
            if (binding.grpButtons.isVisible) {
                binding.grpButtons.visibility = View.GONE
            } else {
                binding.grpButtons.visibility = View.VISIBLE
            }
        }
    }

    private fun handlePlayPause() {
        if (isPlaying) {
            isPlaying = false
            exoPlayer?.pause()
            binding.ibPlayPause.setImageResource(R.drawable.play)
        } else {
            isPlaying = true
            exoPlayer?.play()
            binding.ibPlayPause.setImageResource(R.drawable.pause)
        }
    }
}
