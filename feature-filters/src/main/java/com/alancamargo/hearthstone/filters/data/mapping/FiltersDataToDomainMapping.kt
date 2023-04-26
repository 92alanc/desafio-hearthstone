package com.alancamargo.hearthstone.filters.data.mapping

import com.alancamargo.hearthstone.core.data.mapping.toDomain
import com.alancamargo.hearthstone.core.data.remote.CardFactionResponse
import com.alancamargo.hearthstone.core.data.remote.CardQualityResponse
import com.alancamargo.hearthstone.core.data.remote.CardRaceResponse
import com.alancamargo.hearthstone.core.data.remote.CardTypeResponse
import com.alancamargo.hearthstone.core.data.remote.PlayerClassResponse
import com.alancamargo.hearthstone.filters.data.model.DbFilters
import com.alancamargo.hearthstone.filters.data.model.FiltersResponse
import com.alancamargo.hearthstone.filters.domain.model.Filters
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal fun FiltersResponse.toDomain() = Filters(
    factions = factions.map { it.toDomain() },
    qualities = qualities.map { it.toDomain() },
    races = races.map { it.toDomain() },
    types = types.map { it.toDomain() },
    playerClasses = playerClasses.map { it.toDomain() }
)

internal fun DbFilters.toDomain(): Filters {
    val factionsResponse = Json.decodeFromString<List<CardFactionResponse>>(factionsJson)
    val qualitiesResponse = Json.decodeFromString<List<CardQualityResponse>>(qualitiesJson)
    val racesResponse = Json.decodeFromString<List<CardRaceResponse>>(racesJson)
    val typesResponse = Json.decodeFromString<List<CardTypeResponse>>(typesJson)
    val playerClassesResponse = Json.decodeFromString<List<PlayerClassResponse>>(playerClassesJson)

    return Filters(
        factions = factionsResponse.map { it.toDomain() },
        qualities = qualitiesResponse.map { it.toDomain() },
        races = racesResponse.map { it.toDomain() },
        types = typesResponse.map { it.toDomain() },
        playerClasses = playerClassesResponse.map { it.toDomain() }
    )
}
