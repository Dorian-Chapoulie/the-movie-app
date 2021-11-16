package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Discover
import com.gmail.eamosse.imdb.databinding.DiscoverItemListBinding


class DiscoverAdapter(private val items: List<Discover>, val handler: (discover:Discover) -> Unit) :
    RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DiscoverItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Discover) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DiscoverItemListBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}