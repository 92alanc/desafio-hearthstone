package com.alancamargo.hearthstone.filters.testtools

import com.alancamargo.hearthstone.filters.data.model.FiltersResponse

internal fun stubFiltersResponse() = FiltersResponse(
    factions = emptyList(),
    qualities = emptyList(),
    races = emptyList(),
    types = emptyList(),
    playerClasses = emptyList()
)
