package com.alancamargo.hearthstone.filters.ui.viewmodel

internal sealed class FiltersViewAction {

    data class NavigateToCardsList(val filter: String) : FiltersViewAction()

    object ShowFiltersCacheClearedToast : FiltersViewAction()

    object ShowErrorClearingFiltersCacheToast : FiltersViewAction()

    object ShowAppInfo : FiltersViewAction()
}
