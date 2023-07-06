package com.example.interestingfactsaboutnumbers.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interestingfactsaboutnumbers.databinding.ItemSearchHistoryBinding
import com.example.interestingfactsaboutnumbers.model.data.NumberData

class MainActivityAdapter(private val listener: Listener): ListAdapter<NumberData, MainActivityAdapter.NumberViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class NumberViewHolder(private val binding: ItemSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val num = getItem(position)
                        listener.onClick(num)
                    }
                }
            }
        }

        fun bind(num: NumberData) {
            binding.textViewNumberHistory.text = num.number.toString()
            binding.textViewFactHistory.text = num.text
        }
    }


    interface Listener {
        fun onClick(num: NumberData)
    }

    class DiffCallback : DiffUtil.ItemCallback<NumberData>() {
        override fun areItemsTheSame(oldItem: NumberData, newItem: NumberData) =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: NumberData, newItem: NumberData) =
            oldItem == newItem
    }
}