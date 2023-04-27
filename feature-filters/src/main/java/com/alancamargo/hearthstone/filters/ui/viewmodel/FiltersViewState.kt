package com.alancamargo.hearthstone.filters.ui.viewmodel

import com.alancamargo.hearthstone.filters.ui.model.UiFilterBlock
import com.alancamargo.hearthstone.filters.ui.model.UiFiltersError

internal data class FiltersViewState(
    val isLoading: Boolean = false,
    val filters: List<UiFilterBlock>? = null,
    val error: UiFiltersError? = null
) {

    fun onLoading() = copy(
        isLoading = true,
        error = null
    )

    fun onFinishedLoading() = copy(isLoading = false)

    fun onFiltersReceived(filters: List<UiFilterBlock>) = copy(
        filters = filters,
        error = null
    )

    fun onError(error: UiFiltersError) = copy(error = error)
}
