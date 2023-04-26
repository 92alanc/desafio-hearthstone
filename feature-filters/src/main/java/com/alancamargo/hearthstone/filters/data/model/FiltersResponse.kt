package com.alancamargo.hearthstone.filters.data.model

import com.alancamargo.hearthstone.core.data.remote.CardFactionResponse
import com.alancamargo.hearthstone.core.data.remote.CardQualityResponse
import com.alancamargo.hearthstone.core.data.remote.CardRaceResponse
import com.alancamargo.hearthstone.core.data.remote.CardTypeResponse
import com.alancamargo.hearthstone.core.data.remote.PlayerClassResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FiltersResponse(
    @SerialName("factions") val factions: List<CardFactionResponse>,
    @SerialName("qualities") val qualities: List<CardQualityResponse>,
    @SerialName("races") val races: List<CardRaceResponse>,
    @SerialName("types") val types: List<CardTypeResponse>,
    @SerialName("classes") val playerClasses: List<PlayerClassResponse>
)
