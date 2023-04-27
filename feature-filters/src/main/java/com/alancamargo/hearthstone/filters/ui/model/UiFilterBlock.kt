package com.alancamargo.hearthstone.filters.ui.model

import com.alancamargo.hearthstone.core.domain.FilterType

internal data class UiFilterBlock(val type: FilterType, val filters: List<String>)
