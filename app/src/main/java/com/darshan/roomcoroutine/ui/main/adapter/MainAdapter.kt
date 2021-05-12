package com.darshan.roomcoroutine.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darshan.roomcoroutine.data.local.entity.Restaurant
import com.darshan.roomcoroutine.databinding.ItemLayoutBinding
import com.darshan.roomcoroutine.utils.ItemClickListener

class MainAdapter(
    private val restaurants: ArrayList<Restaurant>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: ItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var itemBinding: ItemLayoutBinding = itemView
        fun bind(restaurant: Restaurant, clickListener: ItemClickListener) {
            itemBinding.model = restaurant
            itemBinding.clickListener = clickListener
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = restaurants.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(restaurants[position], itemClickListener)

    fun addRestaurants(restaurants: List<Restaurant>) {
        this.restaurants.apply {
            clear()
            addAll(restaurants)
        }
    }
}