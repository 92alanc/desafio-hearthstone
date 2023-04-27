package com.alancamargo.hearthstone.filters.ui.viewmodel

import com.alancamargo.hearthstone.core.domain.FilterType

internal sealed class FiltersViewAction {

    data class NavigateToCardsList(
        val filter: String,
        val type: FilterType
    ) : FiltersViewAction()

    object ShowFiltersCacheClearedToast : FiltersViewAction()

    object ShowErrorClearingFiltersCacheToast : FiltersViewAction()

    object ShowAppInfo : FiltersViewAction()
}
