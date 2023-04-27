package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.filters.data.model.DbFilters
import com.alancamargo.hearthstone.filters.domain.model.Filters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun Filters.toDb(): DbFilters {
    val factionsResponse = factions
    val qualitiesResponse = qualities
    val racesResponse = races
    val typesResponse = types
    val playerClassesResponse = playerClasses

    return DbFilters(
        factionsJson = Json.encodeToString(factionsResponse),
        qualitiesJson = Json.encodeToString(qualitiesResponse),
        racesJson = Json.encodeToString(racesResponse),
        typesJson = Json.encodeToString(typesResponse),
        playerClassesJson = Json.encodeToString(playerClassesResponse)
    )
}
