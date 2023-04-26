package com.alancamargo.hearthstone.core.domain

data class Card(
    val id: String,
    val name: String,
    val type: CardType,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val race: CardRace,
    val playerClass: PlayerClass,
    val imageUrl: String?
)
