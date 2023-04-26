package com.alancamargo.hearthstone.filters.data.remote

import com.alancamargo.hearthstone.filters.domain.model.FiltersResult

internal interface FiltersRemoteDataSource {

    suspend fun getFilters(): FiltersResult
}
