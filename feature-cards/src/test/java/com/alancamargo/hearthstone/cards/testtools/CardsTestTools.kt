package com.alancamargo.hearthstone.cards.testtools

import com.alancamargo.hearthstone.core.domain.Card

fun stubCard(imageUrl: String? = null) = Card(
    id = "",
    name = "",
    type = "",
    quality = "",
    faction = "",
    cost = 0,
    attack = 0,
    health = 0,
    text = "",
    race = "",
    playerClass = "",
    imageUrl = imageUrl
)
