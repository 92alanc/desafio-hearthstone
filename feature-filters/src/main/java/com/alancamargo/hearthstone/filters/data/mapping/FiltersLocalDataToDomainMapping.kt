package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.filters.data.model.DbFilters
import com.alancamargo.hearthstone.filters.domain.model.Filters
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal fun DbFilters.toDomain(): Filters {
    val factions = Json.decodeFromString<List<String>>(factionsJson)
    val qualities = Json.decodeFromString<List<String>>(qualitiesJson)
    val races = Json.decodeFromString<List<String>>(racesJson)
    val types = Json.decodeFromString<List<String>>(typesJson)
    val playerClasses = Json.decodeFromString<List<String>>(playerClassesJson)

    return Filters(
        factions = factions,
        qualities = qualities,
        races = races,
        types = types,
        playerClasses = playerClasses
    )
}
