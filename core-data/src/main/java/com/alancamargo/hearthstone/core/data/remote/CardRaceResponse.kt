package com.alancamargo.hearthstone.core.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardRaceResponse {

    @SerialName("Orc")
    ORC,

    @SerialName("Undead")
    UNDEAD,

    @SerialName("Murloc")
    MURLOC,

    @SerialName("Demon")
    DEMON,

    @SerialName("Mech")
    MECH,

    @SerialName("Elemental")
    ELEMENTAL,

    @SerialName("Beast")
    BEAST,

    @SerialName("Totem")
    TOTEM,

    @SerialName("Pirate")
    PIRATE,

    @SerialName("Dragon")
    DRAGON,

    @SerialName("All")
    ALL,

    @SerialName("Quilobar")
    QUILBOAR,

    @SerialName("Naga")
    NAGA
}
