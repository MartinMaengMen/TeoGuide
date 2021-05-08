package com.upc.teoguide.ui.principal.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.FragmentHomeBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaCentrosHistoricosAdapter
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), ListaCentrosHistoricosAdapter.CentrosHistoricosListener {
    private lateinit var adapter: ListaCentrosHistoricosAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val model: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding = binding
        setUpRecyclerView()
        setUpObservers()
        return binding.root

    }

    private fun setUpRecyclerView() {
        adapter = ListaCentrosHistoricosAdapter(binding.root.context, this)
        binding.centrosHistoricosRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.centrosHistoricosRecyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        model.getCentrosHistoricos().observe(viewLifecycleOwner, { centroHistoricos ->
            if(centroHistoricos != null){
                adapter.setItems(centroHistoricos as ArrayList<CentroHistorico>)
            } else{
                adapter.setItems(ArrayList())
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onClickedCentroHistorico(centroHistorico: CentroHistorico, textView: TextView) {
        val action  = HomeFragmentDirections.actionNavigationHomeToDetalleCentroFragment2(centroHistorico)
        val extras = FragmentNavigatorExtras(
            textView to centroHistorico.descripcion
        )
        NavHostFragment.findNavController(this).navigate(action, extras)
    }

    //SI SIRVE
    /*override fun onClickedCentro(centro: CentroHistorico, textView: TextView) {
        val action  = HomeFragmentDirections.actionHomeFragmentToDetalleCentroFragment(centro)
        val extras = FragmentNavigatorExtras(
            textView to centro.description
        )
        NavHostFragment.findNavController(this).navigate(action, extras)
    }*/

}