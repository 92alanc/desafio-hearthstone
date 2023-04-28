package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    @SerialName("name") val name: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("rarity") val quality: String? = null,
    @SerialName("faction") val faction: String? = null,
    @SerialName("cost") val cost: Int? = null,
    @SerialName("attack") val attack: Int? = null,
    @SerialName("health") val health: Int? = null,
    @SerialName("text") val text: String? = null,
    @SerialName("race") val race: String? = null,
    @SerialName("playerClass") val playerClass: String? = null,
    @SerialName("img") val imageUrl: String? = null
)
