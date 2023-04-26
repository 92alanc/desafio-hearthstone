package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    @SerialName("cardId") val id: String,
    @SerialName("name") val name: String,
    @SerialName("type") val type: CardTypeResponse,
    @SerialName("cost") val cost: Int,
    @SerialName("attack") val attack: Int,
    @SerialName("health") val health: Int,
    @SerialName("text") val text: String,
    @SerialName("race") val race: CardRaceResponse,
    @SerialName("playerClass") val playerClass: PlayerClassResponse,
    @SerialName("img") val imageUrl: String? = null
)
