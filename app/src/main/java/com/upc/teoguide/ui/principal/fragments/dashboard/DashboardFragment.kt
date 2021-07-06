package com.upc.teoguide.ui.principal.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.R
import com.upc.teoguide.data.base.Global
import com.upc.teoguide.data.entities.Plan
import com.upc.teoguide.databinding.FragmentDashboardBinding
import com.upc.teoguide.ui.principal.fragments.dashboard.adapters.ListaPlanesAdapter

class DashboardFragment : Fragment() {

    private lateinit var adapter: ListaPlanesAdapter
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val model: DashboardViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setUpRecycleView()
        setUpListeners()
        setUpObservers()
        return binding.root
    }

    private fun setUpRecycleView() {
        adapter = ListaPlanesAdapter(binding.root.context)
        binding.rvPlanes.layoutManager = LinearLayoutManager(this.context)
        binding.rvPlanes.adapter = adapter
    }

    private fun setUpObservers() {
        val usuarioId = Global.usuarioId
        model.getPlanes(usuarioId!!).observe(viewLifecycleOwner, { planes ->
            if(planes != null) {
                adapter.setItems(planes as ArrayList<Plan>)
            } else {
                adapter.setItems(ArrayList())
            }
        })
    }

    private fun setUpListeners() {
        binding.fabAddPlan.setOnClickListener {
            val action = DashboardFragmentDirections.actionNavigationDashboardToCreatePlanFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}