package com.alancamargo.hearthstone.filters.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FiltersResponse(
    @SerialName("factions") val factions: List<String>,
    @SerialName("qualities") val qualities: List<String>,
    @SerialName("races") val races: List<String>,
    @SerialName("types") val types: List<String>,
    @SerialName("classes") val playerClasses: List<String>
)
