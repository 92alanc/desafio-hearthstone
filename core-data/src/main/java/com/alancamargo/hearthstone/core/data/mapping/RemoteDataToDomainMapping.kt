package com.alancamargo.hearthstone.core.data.mapping

import com.alancamargo.hearthstone.core.data.remote.CardFactionResponse
import com.alancamargo.hearthstone.core.data.remote.CardQualityResponse
import com.alancamargo.hearthstone.core.data.remote.CardRaceResponse
import com.alancamargo.hearthstone.core.data.remote.CardResponse
import com.alancamargo.hearthstone.core.data.remote.CardTypeResponse
import com.alancamargo.hearthstone.core.data.remote.PlayerClassResponse
import com.alancamargo.hearthstone.core.domain.Card
import com.alancamargo.hearthstone.core.domain.CardFaction
import com.alancamargo.hearthstone.core.domain.CardQuality
import com.alancamargo.hearthstone.core.domain.CardRace
import com.alancamargo.hearthstone.core.domain.CardType
import com.alancamargo.hearthstone.core.domain.PlayerClass

fun CardResponse.toDomain() = Card(
    id = id,
    name = name,
    type = type.toDomain(),
    cost = cost,
    attack = attack,
    health = health,
    text = text,
    race = race.toDomain(),
    playerClass = playerClass.toDomain(),
    imageUrl = imageUrl
)

fun CardTypeResponse.toDomain() = when (this) {
    CardTypeResponse.WEAPON -> CardType.WEAPON
    CardTypeResponse.ENCHANTMENT -> CardType.ENCHANTMENT
    CardTypeResponse.HERO -> CardType.HERO
    CardTypeResponse.HERO_POWER -> CardType.HERO_POWER
    CardTypeResponse.LOCATION -> CardType.LOCATION
    CardTypeResponse.MINION -> CardType.MINION
    CardTypeResponse.SPELL -> CardType.SPELL
}

fun CardRaceResponse.toDomain() = when (this) {
    CardRaceResponse.ALL -> CardRace.ALL
    CardRaceResponse.BEAST -> CardRace.BEAST
    CardRaceResponse.DEMON -> CardRace.DEMON
    CardRaceResponse.DRAGON -> CardRace.DRAGON
    CardRaceResponse.ELEMENTAL -> CardRace.ELEMENTAL
    CardRaceResponse.MECH -> CardRace.MECH
    CardRaceResponse.MURLOC -> CardRace.MURLOC
    CardRaceResponse.NAGA -> CardRace.NAGA
    CardRaceResponse.ORC -> CardRace.ORC
    CardRaceResponse.PIRATE -> CardRace.PIRATE
    CardRaceResponse.TOTEM -> CardRace.TOTEM
    CardRaceResponse.QUILBOAR -> CardRace.QUILBOAR
    CardRaceResponse.UNDEAD -> CardRace.UNDEAD
}

fun PlayerClassResponse.toDomain() = when (this) {
    PlayerClassResponse.DEATH_KNIGHT -> PlayerClass.DEATH_KNIGHT
    PlayerClassResponse.DEMON_HUNTER -> PlayerClass.DEMON_HUNTER
    PlayerClassResponse.DREAM -> PlayerClass.DREAM
    PlayerClassResponse.DRUID -> PlayerClass.DRUID
    PlayerClassResponse.HUNTER -> PlayerClass.HUNTER
    PlayerClassResponse.MAGE -> PlayerClass.MAGE
    PlayerClassResponse.NEUTRAL -> PlayerClass.NEUTRAL
    PlayerClassResponse.PALADIN -> PlayerClass.PALADIN
    PlayerClassResponse.PRIEST -> PlayerClass.PRIEST
    PlayerClassResponse.ROGUE -> PlayerClass.ROGUE
    PlayerClassResponse.SHAMAN -> PlayerClass.SHAMAN
    PlayerClassResponse.WARLOCK -> PlayerClass.WARLOCK
    PlayerClassResponse.WARRIOR -> PlayerClass.WARRIOR
    PlayerClassResponse.WHIZBANG -> PlayerClass.WHIZBANG
}

fun CardFactionResponse.toDomain() = when (this) {
    CardFactionResponse.ALLIANCE -> CardFaction.ALLIANCE
    CardFactionResponse.HORDE -> CardFaction.HORDE
    CardFactionResponse.NEUTRAL -> CardFaction.NEUTRAL
}

fun CardQualityResponse.toDomain() = when (this) {
    CardQualityResponse.COMMON -> CardQuality.COMMON
    CardQualityResponse.EPIC -> CardQuality.EPIC
    CardQualityResponse.FREE -> CardQuality.FREE
    CardQualityResponse.LEGENDARY -> CardQuality.LEGENDARY
    CardQualityResponse.RARE -> CardQuality.RARE
}
