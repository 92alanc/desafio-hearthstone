package com.alancamargo.hearthstone.filters.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alancamargo.hearthstone.filters.databinding.ItemFilterBinding

internal class FilterViewHolder(
    private val binding: ItemFilterBinding,
    private val onItemClicked: (String) -> Unit
) : ViewHolder(binding.root) {

    fun bindTo(filter: String) = with(binding) {
        txtFilter.text = filter
        root.setOnClickListener { onItemClicked(filter) }
    }
}
