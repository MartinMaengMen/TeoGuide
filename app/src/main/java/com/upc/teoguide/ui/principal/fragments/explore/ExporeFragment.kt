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
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val huallamarca = LatLng(-12.097147060919646, -77.04048228878406)
        googleMap.addMarker(MarkerOptions().position(huallamarca).title("Marker in Sydney")).showInfoWindow()
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(huallamarca))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(huallamarca,15f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentExporeBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    //return inflater.inflate(R.layout.fragment_expore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(callback)
    }
}