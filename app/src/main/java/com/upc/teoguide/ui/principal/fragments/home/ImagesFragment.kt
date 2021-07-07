package com.upc.teoguide.ui.principal.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.data.entities.Multimedia
import com.upc.teoguide.databinding.FragmentImagesBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaMultimediaAdapter

class ImagesFragment : Fragment() {
    private lateinit var adapter: ListaMultimediaAdapter
    private var _binding: FragmentImagesBinding?= null
    private val binding get() = _binding!!
    private val model: ImagesViewModel by viewModels()
    private var centroId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        setUpRecycleView()
        setUpObservers()
        return binding.root
    }

    private fun setUpRecycleView() {
        adapter = ListaMultimediaAdapter(binding.root.context)
        binding.rvMultimedia.layoutManager = LinearLayoutManager(this.context)
        binding.rvMultimedia.adapter = adapter
    }

    private fun setUpObservers() {
        arguments?.takeIf { it.containsKey("arg1") }?.apply {
            centroId = getInt("arg1").toInt()

        }

        model.getMultimedias(centroId).observe(viewLifecycleOwner, { multimedias ->
            if (multimedias != null) {
                adapter.setItems(multimedias as ArrayList<Multimedia>)
            } else {
                adapter.setItems(ArrayList())
            }
        })
    }
}