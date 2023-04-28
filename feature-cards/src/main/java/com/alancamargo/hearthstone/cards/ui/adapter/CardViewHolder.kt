package com.alancamargo.hearthstone.cards.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alancamargo.hearthstone.cards.databinding.ItemCardBinding
import com.alancamargo.hearthstone.core.domain.Card

internal class CardViewHolder(
    private val binding: ItemCardBinding,
    private val onItemClicked: (Card) -> Unit
) : ViewHolder(binding.root) {

    fun bindTo(card: Card) = with(binding) {
        txtName.text = card.name
        root.setOnClickListener { onItemClicked(card) }
    }
}
