package com.example.videoplayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.videoplayer.databinding.FragmentVideoPlayerBinding
import com.example.videoplayer.repository.models.NetworkResult
import com.example.videoplayer.repository.models.Video
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
        setUpListeners()
    }

    private fun configViews() {
        //fetching videos data from API
        viewModel.getVideos()
    }

    private fun setUpListeners() {
        //live data observing video's data from server
        viewModel.getVideosLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    binding.tvError.visibility = View.GONE
                    binding.viewpager.visibility = View.VISIBLE
                    binding.pbLoader.visibility = View.GONE
                }
                is NetworkResult.Error -> {
                    binding.tvError.text = it.message.toString()
                    binding.tvError.visibility = View.VISIBLE
                    binding.viewpager.visibility = View.GONE
                    binding.pbLoader.visibility = View.GONE
                }
                is NetworkResult.Loading -> {
                    binding.pbLoader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.viewpager.visibility = View.GONE
                }
            }
        }
    }
}