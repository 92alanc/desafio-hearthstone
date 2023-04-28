package com.alancamargo.hearthstone.filters.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.filters.R
import com.alancamargo.hearthstone.filters.databinding.ItemFilterBlockBinding
import com.alancamargo.hearthstone.filters.ui.model.UiFilterBlock

internal class FilterBlockViewHolder(
    private val binding: ItemFilterBlockBinding,
    private val onItemClickListener: (String, FilterType) -> Unit
) : ViewHolder(binding.root) {

    fun bindTo(block: UiFilterBlock) {
        val adapter = FilterAdapter(block.type) { filter, type ->
            onItemClickListener(filter, type)
        }

        val headerTextRes = getHeaderTextRes(block.type)
        binding.txtHeader.setText(headerTextRes)

        binding.recyclerView.adapter = adapter
        adapter.submitList(block.filters)
    }

    private fun getHeaderTextRes(type: FilterType): Int = when (type) {
        FilterType.PLAYER_CLASS -> R.string.player_classes
        FilterType.QUALITY -> R.string.qualities
        FilterType.FACTION -> R.string.factions
        FilterType.RACE -> R.string.races
        FilterType.TYPE -> R.string.types
    }
}
