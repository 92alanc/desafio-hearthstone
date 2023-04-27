package com.alancamargo.hearthstone.filters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.filters.databinding.ItemFilterBlockBinding
import com.alancamargo.hearthstone.filters.ui.model.UiFilterBlock

internal class FilterBlockAdapter(
    private val onItemClickListener: (String, FilterType) -> Unit
) : ListAdapter<UiFilterBlock, FilterBlockViewHolder>(FilterBlockDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterBlockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterBlockBinding.inflate(inflater, parent, false)
        return FilterBlockViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: FilterBlockViewHolder, position: Int) {
        val block = getItem(position)
        holder.bindTo(block)
    }
}
