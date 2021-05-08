package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.ItemCentroHistoricoBinding
class ListaCentrosHistoricosAdapter(val context: Context, private val listener: CentrosHistoricosListener) : RecyclerView.Adapter<ListaCentrosHistoricosAdapter.ViewHolder>() {

    private val items: MutableList<CentroHistorico> = ArrayList()

    interface CentrosHistoricosListener{
        fun onClickedCentroHistorico(centroHistorico: CentroHistorico, imageView: ImageView)
    }

    fun setItems(items: ArrayList<CentroHistorico>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCentroHistoricoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ItemCentroHistoricoBinding, private val listener: CentrosHistoricosListener)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var centroHistorico: CentroHistorico

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: CentroHistorico) {
            this.centroHistorico = item
            binding.centrohistoricoImageView.transitionName = this.centroHistorico.id.toString()
            binding.centrohistoricoDescriptionTextView.text = this.centroHistorico.descripcion
            binding.centrohistoricoTitleTextView.text = this.centroHistorico.nombre
            Glide.with(context).load(this.centroHistorico.imgUrl).into(binding.centrohistoricoImageView)
            //binding.executePendingBindings()
        }

        override fun onClick(v: View?){
            listener.onClickedCentroHistorico(centroHistorico, binding.centrohistoricoImageView)
        }
    }
}