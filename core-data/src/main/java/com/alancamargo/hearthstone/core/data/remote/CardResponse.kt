package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    @SerialName("cardId") val id: String,
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("rarity") val quality: String,
    @SerialName("cost") val cost: Int,
    @SerialName("attack") val attack: Int,
    @SerialName("health") val health: Int,
    @SerialName("text") val text: String,
    @SerialName("race") val race: String,
    @SerialName("playerClass") val playerClass: String,
    @SerialName("img") val imageUrl: String? = null
)
