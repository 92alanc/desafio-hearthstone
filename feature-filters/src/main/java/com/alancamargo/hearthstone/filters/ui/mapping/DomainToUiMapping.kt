package com.alancamargo.hearthstone.filters.ui.mapping

import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.filters.domain.model.Filters
import com.alancamargo.hearthstone.filters.ui.model.UiFilterBlock

internal fun Filters.toFilterBlockList() = listOf(
    UiFilterBlock(type = FilterType.FACTION, filters = factions),
    UiFilterBlock(type = FilterType.TYPE, filters = types),
    UiFilterBlock(type = FilterType.RACE, filters = races),
    UiFilterBlock(type = FilterType.QUALITY, filters = qualities),
    UiFilterBlock(type = FilterType.PLAYER_CLASS, filters = playerClasses)
)
