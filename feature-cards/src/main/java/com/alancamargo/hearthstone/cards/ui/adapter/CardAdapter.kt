package com.alancamargo.hearthstone.cards.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.hearthstone.cards.databinding.ItemCardBinding
import com.alancamargo.hearthstone.core.domain.Card

internal class CardAdapter(
    private val onItemClicked: (Card) -> Unit
) : ListAdapter<Card, CardViewHolder>(CardDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bindTo(card)
    }
}
