package com.alancamargo.hearthstone.filters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.filters.databinding.ItemFilterBinding

internal class FilterAdapter(
    private val filterType: FilterType,
    private val onItemClicked: (String, FilterType) -> Unit
) : ListAdapter<String, FilterViewHolder>(FilterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterBinding.inflate(inflater, parent, false)
        return FilterViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = getItem(position)
        holder.bindTo(filter, filterType)
    }
}
