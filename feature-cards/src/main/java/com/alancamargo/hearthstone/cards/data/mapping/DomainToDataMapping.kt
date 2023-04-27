package com.alancamargo.hearthstone.cards.data.mapping

import com.alancamargo.hearthstone.core.domain.FilterType

internal fun FilterType.toData() = when (this) {
    FilterType.PLAYER_CLASS -> "classes"
    FilterType.QUALITY -> "qualities"
    FilterType.RACE -> "races"
    FilterType.TYPE -> "types"
    FilterType.FACTION -> "factions"
}
