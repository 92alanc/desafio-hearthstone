package com.alancamargo.hearthstone.filters.domain.model

internal data class Filters(
    val factions: List<String>,
    val qualities: List<String>,
    val races: List<String>,
    val types: List<String>,
    val playerClasses: List<String>
)
