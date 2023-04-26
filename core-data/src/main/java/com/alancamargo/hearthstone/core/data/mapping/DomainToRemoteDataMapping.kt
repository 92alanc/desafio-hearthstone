package com.alancamargo.hearthstone.core.data.mapping

import com.alancamargo.hearthstone.core.data.remote.CardFactionResponse
import com.alancamargo.hearthstone.core.data.remote.CardQualityResponse
import com.alancamargo.hearthstone.core.data.remote.CardRaceResponse
import com.alancamargo.hearthstone.core.data.remote.CardTypeResponse
import com.alancamargo.hearthstone.core.data.remote.PlayerClassResponse
import com.alancamargo.hearthstone.core.domain.CardFaction
import com.alancamargo.hearthstone.core.domain.CardQuality
import com.alancamargo.hearthstone.core.domain.CardRace
import com.alancamargo.hearthstone.core.domain.CardType
import com.alancamargo.hearthstone.core.domain.PlayerClass

fun CardFaction.toResponse() = when (this) {
    CardFaction.NEUTRAL -> CardFactionResponse.NEUTRAL
    CardFaction.HORDE -> CardFactionResponse.HORDE
    CardFaction.ALLIANCE -> CardFactionResponse.ALLIANCE
}

fun CardQuality.toResponse() = when (this) {
    CardQuality.COMMON -> CardQualityResponse.COMMON
    CardQuality.EPIC -> CardQualityResponse.EPIC
    CardQuality.FREE -> CardQualityResponse.FREE
    CardQuality.LEGENDARY -> CardQualityResponse.LEGENDARY
    CardQuality.RARE -> CardQualityResponse.RARE
}

fun CardRace.toResponse() = when (this) {
    CardRace.ALL -> CardRaceResponse.ALL
    CardRace.BEAST -> CardRaceResponse.BEAST
    CardRace.DEMON -> CardRaceResponse.DEMON
    CardRace.DRAGON -> CardRaceResponse.DRAGON
    CardRace.ELEMENTAL -> CardRaceResponse.ELEMENTAL
    CardRace.MECH -> CardRaceResponse.MECH
    CardRace.MURLOC -> CardRaceResponse.MURLOC
    CardRace.NAGA -> CardRaceResponse.NAGA
    CardRace.ORC -> CardRaceResponse.ORC
    CardRace.PIRATE -> CardRaceResponse.PIRATE
    CardRace.TOTEM -> CardRaceResponse.TOTEM
    CardRace.QUILBOAR -> CardRaceResponse.QUILBOAR
    CardRace.UNDEAD -> CardRaceResponse.UNDEAD
}

fun CardType.toResponse() = when (this) {
    CardType.WEAPON -> CardTypeResponse.WEAPON
    CardType.ENCHANTMENT -> CardTypeResponse.ENCHANTMENT
    CardType.HERO -> CardTypeResponse.HERO
    CardType.HERO_POWER -> CardTypeResponse.HERO_POWER
    CardType.LOCATION -> CardTypeResponse.LOCATION
    CardType.MINION -> CardTypeResponse.MINION
    CardType.SPELL -> CardTypeResponse.SPELL
}

fun PlayerClass.toResponse() = when (this) {
    PlayerClass.DEATH_KNIGHT -> PlayerClassResponse.DEATH_KNIGHT
    PlayerClass.DEMON_HUNTER -> PlayerClassResponse.DEMON_HUNTER
    PlayerClass.DREAM -> PlayerClassResponse.DREAM
    PlayerClass.DRUID -> PlayerClassResponse.DRUID
    PlayerClass.HUNTER -> PlayerClassResponse.HUNTER
    PlayerClass.MAGE -> PlayerClassResponse.MAGE
    PlayerClass.NEUTRAL -> PlayerClassResponse.NEUTRAL
    PlayerClass.PALADIN -> PlayerClassResponse.PALADIN
    PlayerClass.PRIEST -> PlayerClassResponse.PRIEST
    PlayerClass.ROGUE -> PlayerClassResponse.ROGUE
    PlayerClass.SHAMAN -> PlayerClassResponse.SHAMAN
    PlayerClass.WARLOCK -> PlayerClassResponse.WARLOCK
    PlayerClass.WARRIOR -> PlayerClassResponse.WARRIOR
    PlayerClass.WHIZBANG -> PlayerClassResponse.WHIZBANG
}
