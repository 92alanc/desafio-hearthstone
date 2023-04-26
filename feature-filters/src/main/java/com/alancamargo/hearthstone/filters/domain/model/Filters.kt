package com.alancamargo.hearthstone.filters.domain.model

import com.alancamargo.hearthstone.core.domain.CardFaction
import com.alancamargo.hearthstone.core.domain.CardQuality
import com.alancamargo.hearthstone.core.domain.CardRace
import com.alancamargo.hearthstone.core.domain.CardType
import com.alancamargo.hearthstone.core.domain.PlayerClass

internal data class Filters(
    val factions: List<CardFaction>,
    val qualities: List<CardQuality>,
    val races: List<CardRace>,
    val types: List<CardType>,
    val playerClasses: List<PlayerClass>
)
