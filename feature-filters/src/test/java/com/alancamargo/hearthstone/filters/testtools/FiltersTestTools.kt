package com.alancamargo.hearthstone.filters.testtools

import com.alancamargo.hearthstone.filters.data.model.DbFilters
import com.alancamargo.hearthstone.filters.data.model.FiltersResponse
import com.alancamargo.hearthstone.filters.domain.model.Filters

internal fun stubFiltersResponse() = FiltersResponse(
    factions = emptyList(),
    qualities = emptyList(),
    races = emptyList(),
    types = emptyList(),
    playerClasses = emptyList()
)

internal fun stubDbFilters() = DbFilters(
    factionsJson = "[]",
    qualitiesJson = "[]",
    racesJson = "[]",
    typesJson = "[]",
    playerClassesJson = "[]"
)

internal fun stubFilters() = Filters(
    factions = emptyList(),
    qualities = emptyList(),
    races = emptyList(),
    types = emptyList(),
    playerClasses = emptyList()
)
