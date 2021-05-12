package com.darshan.roomcoroutine.ui.detail.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.databinding.ItemPageBinding

class ViewPagerAdapter(private val images: ArrayList<RestImage>) :
    RecyclerView.Adapter<ViewPagerAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: ItemPageBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var itemBinding: ItemPageBinding = itemView
        fun bind(restImage: RestImage) {
            itemBinding.model = restImage
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(images[position])

    fun addRestImages(restImages: List<RestImage>) {
        this.images.apply {
            clear()
            addAll(restImages)
        }
    }
}