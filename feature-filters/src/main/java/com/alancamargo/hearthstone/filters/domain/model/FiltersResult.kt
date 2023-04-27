package com.alancamargo.hearthstone.filters.domain.model

internal sealed class FiltersResult {

    data class Success(val filters: Filters) : FiltersResult()

    object NetworkError : FiltersResult()

    object GenericError : FiltersResult()
}
