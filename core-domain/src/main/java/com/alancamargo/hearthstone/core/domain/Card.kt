package com.alancamargo.hearthstone.core.domain

data class Card(
    val id: String,
    val name: String,
    val type: String,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val race: String,
    val playerClass: String,
    val imageUrl: String?
)
