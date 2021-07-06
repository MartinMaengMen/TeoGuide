package com.upc.teoguide.ui.principal.fragments.explore

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.upc.teoguide.R
import com.upc.teoguide.databinding.FragmentExporeBinding

class ExporeFragment : Fragment() {
    private var _binding: FragmentExporeBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        val mateoSalado = LatLng(-12.067163839786117, -77.06362247344228)
        googleMap.addMarker(MarkerOptions().position(mateoSalado).title("Huaca Mateo-Salado"))
        val huacaSanMiguel = LatLng(-12.070137734933882, -77.08431095138)
        googleMap.addMarker(MarkerOptions().position(huacaSanMiguel).title("Huaca San Miguel"))
        val huacaMangomarca = LatLng(-12.014978831873446, -76.98147327020303)
        googleMap.addMarker(MarkerOptions().position(huacaMangomarca).title("Huaca MangoMarca"))
        val huallamarca = LatLng(-12.097147060919646, -77.04048228878406)
        googleMap.addMarker(MarkerOptions().position(huallamarca).title("Huaca Huallamarca")).showInfoWindow()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(huallamarca,12f))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExporeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(callback)
    }
}