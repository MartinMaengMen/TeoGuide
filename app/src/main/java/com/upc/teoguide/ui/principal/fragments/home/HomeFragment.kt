package com.upc.teoguide.ui.principal.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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
        /*var listaCentrosHistoricos = Stack<CentroHistorico>()
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo de Lima", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo de la Nación", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo del Arte", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo metropolitano de Lima", "Lima", "Un Museo en Lima"))
        binding.centrosHistoricosRecyclerView.adapter = ListaCentrosHistoricosAdapter(listaCentrosHistoricos)
        binding.centrosHistoricosRecyclerView.layoutManager = LinearLayoutManager(this.context)
        /*homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        */
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        //return root*/
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

    override fun onClickedCentroHistorico(centroHistorico: CentroHistorico, imageView: ImageView) {
        TODO("Not yet implemented")
    }
}