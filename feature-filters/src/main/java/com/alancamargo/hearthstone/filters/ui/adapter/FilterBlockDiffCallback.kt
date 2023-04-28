package com.alancamargo.hearthstone.filters.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.hearthstone.filters.ui.model.UiFilterBlock

internal object FilterBlockDiffCallback : DiffUtil.ItemCallback<UiFilterBlock>() {

    override fun areItemsTheSame(oldItem: UiFilterBlock, newItem: UiFilterBlock): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItem: UiFilterBlock, newItem: UiFilterBlock): Boolean {
        return oldItem == newItem
    }
}
