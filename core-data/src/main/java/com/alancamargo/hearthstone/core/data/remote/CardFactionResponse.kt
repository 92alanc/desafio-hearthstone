package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardFactionResponse {

    @SerialName("Horde")
    HORDE,

    @SerialName("Alliance")
    ALLIANCE,

    @SerialName("Neutral")
    NEUTRAL
}
