package com.example.videoplayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.videoplayer.databinding.FragmentVideoPlayerBinding
import com.example.videoplayer.viewmodels.VideoPlayerFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {
    private lateinit var binding: FragmentVideoPlayerBinding
    private val viewModel: VideoPlayerFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViews()
    }

    private fun configViews() {
        //fetching videos data from API
        viewModel.getVideos()
    }
}