package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.ItemCentroHistoricoBinding
class ListaCentrosHistoricosAdapter(
    private val items: MutableList<CentroHistorico>,
    private val listener : CentroHistoricoListener
) : RecyclerView.Adapter<ListaCentrosHistoricosAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCentroHistoricoBinding.inflate(inflater, parent,false)
        return ViewHolder(binding, listener)
    }
    interface CentroHistoricoListener {
        fun onClickedCentro(centro:CentroHistorico, textView :TextView)
    }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(
        val binding: ItemCentroHistoricoBinding,
        private val listener : CentroHistoricoListener
                           ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
       private lateinit var centro : CentroHistorico
       init {
           binding.root.setOnClickListener(this)
       }
        fun bind(item: CentroHistorico) {
            this.centro = item
            binding.centrohistoricoDescriptionTextView.text = item.description
            binding.centrohistoricoTitleTextView.text = item.name
            //binding.executePendingBindings()
        }
        override fun onClick(v: View?) {
            listener.onClickedCentro(centro, binding.centrohistoricoDescriptionTextView)
        }
    } /*{
        fun bind(item: CentroHistorico) {
            binding.centrohistoricoDescriptionTextView.text = item.description
            binding.centrohistoricoTitleTextView.text = item.name
            //binding.executePendingBindings()
        }
    }*/

}