package com.upc.teoguide.ui.principal.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.upc.teoguide.databinding.FragmentTourBinding
import com.upc.teoguide.R

class TourFragment : Fragment(){
    private var _binding: FragmentTourBinding?=null
    private val binding get() = _binding!!

    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTourBinding.inflate(inflater, container, false)
        val view: View = inflater.inflate(R.layout.fragment_tour, container, false)
        youTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        /*youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "UYetrDDjRRk"
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })*/
        return binding.root
    }
}