package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.filters.data.model.FiltersResponse
import com.alancamargo.hearthstone.filters.domain.model.Filters

internal fun FiltersResponse.toDomain() = Filters(
    factions = factions,
    qualities = qualities,
    races = races,
    types = types,
    playerClasses = playerClasses
)
