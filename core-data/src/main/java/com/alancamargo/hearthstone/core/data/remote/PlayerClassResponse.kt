package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PlayerClassResponse {

    @SerialName("Death Knight")
    DEATH_KNIGHT,

    @SerialName("Druid")
    DRUID,

    @SerialName("Hunter")
    HUNTER,

    @SerialName("Mage")
    MAGE,

    @SerialName("Paladin")
    PALADIN,

    @SerialName("Priest")
    PRIEST,

    @SerialName("Rogue")
    ROGUE,

    @SerialName("Shaman")
    SHAMAN,

    @SerialName("Warlock")
    WARLOCK,

    @SerialName("Warrior")
    WARRIOR,

    @SerialName("Dream")
    DREAM,

    @SerialName("Neutral")
    NEUTRAL,

    @SerialName("Whizbang")
    WHIZBANG,

    @SerialName("Demon Hunter")
    DEMON_HUNTER
}
