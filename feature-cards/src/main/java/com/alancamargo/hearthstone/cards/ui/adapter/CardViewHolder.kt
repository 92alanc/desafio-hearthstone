package com.alancamargo.hearthstone.cards.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.alancamargo.hearthstone.cards.databinding.ItemCardBinding
import com.alancamargo.hearthstone.core.design.R
import com.alancamargo.hearthstone.core.domain.Card

internal class CardViewHolder(private val binding: ItemCardBinding) : ViewHolder(binding.root) {

    fun bindTo(card: Card) = with(binding.root) {
        contentDescription = card.name
        load(card.imageUrl) {
            placeholder(R.drawable.ic_image)
        }
    }
}
