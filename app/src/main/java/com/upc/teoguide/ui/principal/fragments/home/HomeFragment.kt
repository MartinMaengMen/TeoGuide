package com.upc.teoguide.ui.principal.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.R
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.FragmentHomeBinding
import com.upc.teoguide.ui.login.fragments.SignInFragmentDirections
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaCentrosHistoricosAdapter
import java.util.*

class HomeFragment : Fragment(), ListaCentrosHistoricosAdapter.CentroHistoricoListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding = binding
        var listaCentrosHistoricos = Stack<CentroHistorico>()
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo de Lima", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo de la Nación", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo del Arte", "Lima", "Un Museo en Lima"))
        listaCentrosHistoricos.add(CentroHistorico(0, "Museo metropolitano de Lima", "Lima", "Un Museo en Lima"))
        binding.centrosHistoricosRecyclerView.adapter = ListaCentrosHistoricosAdapter(listaCentrosHistoricos, this)
        binding.centrosHistoricosRecyclerView.layoutManager = LinearLayoutManager(this.context)
        /*homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        */
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        //return root
        return binding.root
    }

    override fun onClickedCentro(centro: CentroHistorico, textView: TextView) {
        val action  = HomeFragmentDirections.actionHomeFragmentToDetalleCentroFragment(centro)
        val extras = FragmentNavigatorExtras(
            textView to centro.description
        )
        NavHostFragment.findNavController(this).navigate(action, extras)
    }

}