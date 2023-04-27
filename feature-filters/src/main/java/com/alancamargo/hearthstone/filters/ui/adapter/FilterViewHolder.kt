package com.alancamargo.hearthstone.filters.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.filters.databinding.ItemFilterBinding

internal class FilterViewHolder(
    private val binding: ItemFilterBinding,
    private val onItemClicked: (String, FilterType) -> Unit
) : ViewHolder(binding.root) {

    fun bindTo(filter: String, type: FilterType) = with(binding) {
        txtFilter.text = filter
        root.setOnClickListener { onItemClicked(filter, type) }
    }
}
