package com.upc.teoguide.ui.principal.fragments.dashboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upc.teoguide.data.entities.Plan
import com.upc.teoguide.databinding.ItemPlanBinding
import java.text.SimpleDateFormat

class ListaPlanesAdapter(val context: Context): RecyclerView.Adapter<ListaPlanesAdapter.ViewHolder>() {

    private val items: MutableList<Plan> = ArrayList()

    fun setItems(items: ArrayList<Plan>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlanBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemPlanBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var plan: Plan

        fun bind(item: Plan) {
            this.plan = item
            binding.tvTitlePlan.text = this.plan.titulo
            binding.tvDescripcionPlan.text = this.plan.descripcion
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("d MMM yyyy")
            val fechaFormateada = formatter.format(parser.parse(this.plan.fechaPlan.toString()))
            binding.tvFechaPlan.text = fechaFormateada
        }
    }
}