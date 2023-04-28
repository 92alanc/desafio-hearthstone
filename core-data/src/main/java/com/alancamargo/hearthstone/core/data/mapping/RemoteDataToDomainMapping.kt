package com.alancamargo.hearthstone.core.data.mapping

import com.alancamargo.hearthstone.core.data.remote.CardResponse
import com.alancamargo.hearthstone.core.domain.Card
import com.alancamargo.hearthstone.core.extensions.orZero

fun CardResponse.toDomain() = Card(
    name = name.orEmpty(),
    type = type.orEmpty(),
    quality = quality.orEmpty(),
    faction = faction.orEmpty(),
    cost = cost.orZero(),
    attack = attack.orZero(),
    health = health.orZero(),
    text = text.orEmpty(),
    race = race.orEmpty(),
    playerClass = playerClass.orEmpty(),
    imageUrl = imageUrl.orEmpty()
)
