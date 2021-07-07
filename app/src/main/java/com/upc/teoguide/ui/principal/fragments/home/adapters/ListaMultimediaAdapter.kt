package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.teoguide.data.entities.Multimedia
import com.upc.teoguide.databinding.ItemMultimediaBinding

class ListaMultimediaAdapter(val context: Context): RecyclerView.Adapter<ListaMultimediaAdapter.ViewHolder>() {

    private val items: MutableList<Multimedia> = ArrayList()

    fun setItems(items: ArrayList<Multimedia>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMultimediaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMultimediaBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var multimedia: Multimedia

        fun bind(item: Multimedia) {
            this.multimedia = item
            Glide.with(context).load(this.multimedia.contendioUrl).into(binding.ivMultimedia)
        }
    }
}