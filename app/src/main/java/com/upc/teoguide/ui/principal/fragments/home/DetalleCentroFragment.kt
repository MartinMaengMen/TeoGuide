package com.upc.teoguide.ui.principal.fragments.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.upc.teoguide.databinding.FragmentDetalleCentroBinding

class DetalleCentroFragment : Fragment() {
    private var _binding: FragmentDetalleCentroBinding ?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetalleCentroBinding.inflate(inflater, container, false)
        mostrarDetalle()
        return binding.root
    }

    private fun mostrarDetalle() {
        binding.tTiempo.text = "55 minutos"
        binding.tvDireccion.text = "Parque de la Exposición, Av 9 de Diciembre 125, Cercado de Lima"
        binding.tvDescripcion.text = "El Museo de Arte de Lima es uno de los principales museos del Perú, ubicado en el Paseo Colón, frente al Museo de Arte Italiano, en el distrito de Lima."
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}