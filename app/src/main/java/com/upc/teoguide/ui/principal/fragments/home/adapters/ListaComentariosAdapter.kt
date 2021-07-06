package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.upc.teoguide.data.entities.Comentario
import com.upc.teoguide.databinding.ItemComentarioBinding

class ListaComentariosAdapter(val context: Context): RecyclerView.Adapter<ListaComentariosAdapter.ViewHolder>() {

    private val items: MutableList<Comentario> = ArrayList()

    fun setItems(items: ArrayList<Comentario>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemComentarioBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemComentarioBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var comentario: Comentario

        fun bind(item: Comentario) {
            this.comentario = item
            binding.tvComment.text = this.comentario.texto
            binding.tvUsuarioComment.text = this.comentario.nombreUsuario
            if(this.comentario.imagenUrlUsuario != "url") {
                Glide.with(context)
                    .load(this.comentario.imagenUrlUsuario)
                    .transform(CircleCrop())
                    .into(binding.ivCommentUser)
            }
        }
    }
}