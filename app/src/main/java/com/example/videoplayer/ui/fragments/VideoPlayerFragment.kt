package com.example.videoplayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentVideoPlayerBinding
import com.example.videoplayer.helper.NetworkUtility
import com.example.videoplayer.helper.interfaces.OnPageChangeListener
import com.example.videoplayer.repository.models.NetworkResult
import com.example.videoplayer.repository.models.Video
import com.example.videoplayer.ui.adapters.VideoPlayerAdapter
import com.example.videoplayer.viewmodels.VideoPlayerFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment : Fragment(), OnPageChangeListener {
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

        if (NetworkUtility.isNetworkAvailable(requireContext())) {
            //fetching videos data from API
            viewModel.getVideos()
        } else {
            Toast.makeText(
                context,
                getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.viewpager.isUserInputEnabled = false
    }

    private fun setUpListeners() {
        //live data observing video's data from server
        viewModel.getVideosLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success<*> -> {
                    //api success case
                    binding.tvError.visibility = View.GONE
                    binding.viewpager.visibility = View.VISIBLE
                    binding.viewpager.adapter = VideoPlayerAdapter(it.data as List<Video>, this)
                    binding.pbLoader.visibility = View.GONE
                }
                is NetworkResult.Error<*> -> {
                    //api error case
                    binding.tvError.text = it.message.toString()
                    binding.tvError.visibility = View.VISIBLE
                    binding.viewpager.visibility = View.GONE
                    binding.pbLoader.visibility = View.GONE
                }
                is NetworkResult.Loading<*> -> {
                    //loading till response from server
                    binding.pbLoader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.viewpager.visibility = View.GONE
                }
            }
        }
    }

    /**
     * handle next button click of video player
     */
    override fun onNextPageClicked() {
        binding.viewpager.currentItem += 1
    }

    /**
     * handle previous button click of video player
     */
    override fun onPrevPageClicked() {
        binding.viewpager.currentItem -= 1
    }
}