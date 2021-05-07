package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.ItemCentroHistoricoBinding
class ListaCentrosHistoricosAdapter(private val items: List<CentroHistorico>) : RecyclerView.Adapter<ListaCentrosHistoricosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCentroHistoricoBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: ItemCentroHistoricoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CentroHistorico) {
            binding.centrohistoricoDescriptionTextView.text = item.description
            binding.centrohistoricoTitleTextView.text = item.name
            //binding.executePendingBindings()
        }
    }
}