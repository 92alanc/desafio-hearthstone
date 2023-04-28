package com.alancamargo.hearthstone.cards.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.hearthstone.core.domain.Card

internal object CardDiffCallback : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}
