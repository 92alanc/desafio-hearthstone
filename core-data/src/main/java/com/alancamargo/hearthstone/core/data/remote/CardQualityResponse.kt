package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardQualityResponse {

    @SerialName("Common")
    COMMON,

    @SerialName("Free")
    FREE,

    @SerialName("Rare")
    RARE,

    @SerialName("Epic")
    EPIC,

    @SerialName("Legendary")
    LEGENDARY
}
