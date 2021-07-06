package com.upc.teoguide.ui.principal.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upc.teoguide.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }
}