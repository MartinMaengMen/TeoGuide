package com.upc.teoguide.ui.principal.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.upc.teoguide.databinding.FragmentDetalleCentroBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ViewPagerAdapter

class DetalleCentroFragment : Fragment() {
    private var _binding: FragmentDetalleCentroBinding?= null
    private val binding get() = _binding!!
    private val args: DetalleCentroFragmentArgs by navArgs()
    private lateinit var tabLayout: TabLayout
    //private lateinit var viewPager: ViewPager
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val ctxt = this
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    NavHostFragment.findNavController(ctxt).popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)*/
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleCentroBinding.inflate(inflater, container, false)
        mostrarDetalle()
        tabLayout = binding.detailTabs
        viewPager = binding.detailViewpager
        viewPager.adapter = ViewPagerAdapter(this, args.centroArg)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Información"
                1 -> tab.text = "Comentarios"
                else -> tab.text = "Imágenes"
            }
        }.attach()
        return binding.root
    }

    private fun mostrarDetalle() {
        val centro = args.centroArg
        Glide.with(this).load(centro.imgUrl).into(binding.imgDetalle)
        binding.tTiempo.text = "55 minutos"
        binding.tvDireccion.text = centro.direccion
        //binding.tvDescripcion.text = centro.descripcion
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}