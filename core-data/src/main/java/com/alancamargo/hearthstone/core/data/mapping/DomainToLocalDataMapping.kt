package com.alancamargo.hearthstone.core.data.mapping

import com.alancamargo.hearthstone.core.data.local.DbCard
import com.alancamargo.hearthstone.core.domain.Card

fun Card.toDb() = DbCard(
    id = id,
    name = name,
    type = type,
    cost = cost,
    attack = attack,
    health = health,
    text = text,
    race = race,
    playerClass = playerClass,
    imageUrl = imageUrl
)
