package com.alancamargo.hearthstone.core.domain

data class Card(
    val name: String,
    val type: String,
    val quality: String,
    val faction: String,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val race: String,
    val playerClass: String,
    val imageUrl: String
)
