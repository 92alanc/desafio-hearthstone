package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.core.data.mapping.toDomain
import com.alancamargo.hearthstone.filters.data.model.FiltersResponse
import com.alancamargo.hearthstone.filters.domain.model.Filters

internal fun FiltersResponse.toDomain() = Filters(
    factions = factions.map { it.toDomain() },
    qualities = qualities.map { it.toDomain() },
    races = races.map { it.toDomain() },
    types = types.map { it.toDomain() },
    playerClasses = playerClasses.map { it.toDomain() }
)
