package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.core.data.mapping.toResponse
import com.alancamargo.hearthstone.filters.data.model.DbFilters
import com.alancamargo.hearthstone.filters.domain.model.Filters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun Filters.toDb(): DbFilters {
    val factionsResponse = factions.map { it.toResponse() }
    val qualitiesResponse = qualities.map { it.toResponse() }
    val racesResponse = races.map { it.toResponse() }
    val typesResponse = types.map { it.toResponse() }
    val playerClassesResponse = playerClasses.map { it.toResponse() }

    return DbFilters(
        factionsJson = Json.encodeToString(factionsResponse),
        qualitiesJson = Json.encodeToString(qualitiesResponse),
        racesJson = Json.encodeToString(racesResponse),
        typesJson = Json.encodeToString(typesResponse),
        playerClassesJson = Json.encodeToString(playerClassesResponse)
    )
}
