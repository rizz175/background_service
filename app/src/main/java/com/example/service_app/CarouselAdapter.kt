package com.example.service_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.service_app.databinding.ItemCarouselBinding

class CarouselAdapter(
    private val items: List<CarouselItem>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            iconImageView.setImageResource(item.iconResId)
            numberTextView.text = item.number.toString()
            root.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(item: CarouselItem)
    }
}
