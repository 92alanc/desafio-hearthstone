package com.alancamargo.hearthstone.cards.ui.mapping

import com.alancamargo.hearthstone.cards.ui.model.UiCard
import com.alancamargo.hearthstone.core.domain.Card

internal fun Card.toUi() = UiCard(
    id = id,
    name = name,
    type = type,
    quality = quality,
    faction = faction,
    cost = cost,
    attack = attack,
    health = health,
    text = text,
    race = race,
    playerClass = playerClass,
    imageUrl = imageUrl
)
