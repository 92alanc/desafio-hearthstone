package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardTypeResponse {

    @SerialName("Hero")
    HERO,

    @SerialName("Minion")
    MINION,

    @SerialName("Spell")
    SPELL,

    @SerialName("Enchantment")
    ENCHANTMENT,

    @SerialName("Weapon")
    WEAPON,

    @SerialName("Hero Power")
    HERO_POWER,

    @SerialName("Location")
    LOCATION
}
