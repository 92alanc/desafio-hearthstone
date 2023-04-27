package com.alancamargo.hearthstone.filters.domain.repository

import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import kotlinx.coroutines.flow.Flow

internal interface FiltersRepository {

    fun getFilters(): Flow<FiltersResult>

    fun clearCache(): Flow<Unit>
}
