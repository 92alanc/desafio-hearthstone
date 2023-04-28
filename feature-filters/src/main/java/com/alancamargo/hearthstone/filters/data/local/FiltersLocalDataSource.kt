package com.alancamargo.hearthstone.filters.data.local

import com.alancamargo.hearthstone.filters.domain.model.Filters
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult

internal interface FiltersLocalDataSource {

    suspend fun getFilters(): FiltersResult

    suspend fun saveFilters(filters: Filters)

    suspend fun deleteFilters()
}
