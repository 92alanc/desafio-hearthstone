package com.alancamargo.hearthstone.cards.testtools

import com.alancamargo.hearthstone.core.data.mapping.toDb
import com.alancamargo.hearthstone.core.data.remote.CardResponse
import com.alancamargo.hearthstone.core.domain.Card

fun stubCard() = Card(
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
    imageUrl = ""
)

fun stubCardResponseList() = listOf(CardResponse(imageUrl = ""))

fun stubDbCardList() = listOf(stubCard().toDb())
