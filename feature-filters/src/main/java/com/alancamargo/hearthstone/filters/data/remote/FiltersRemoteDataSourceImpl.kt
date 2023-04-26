package com.alancamargo.hearthstone.filters.data.remote

import com.alancamargo.hearthstone.filters.data.api.FiltersApi
import com.alancamargo.hearthstone.filters.data.mapping.toDomain
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import javax.inject.Inject

internal class FiltersRemoteDataSourceImpl @Inject constructor(
    private val api: FiltersApi
) : FiltersRemoteDataSource {

    override suspend fun getFilters(): FiltersResult {
        val response = api.getFilters()

        return if (response.isSuccessful) {
            response.body()?.let {
                val filters = it.toDomain()
                FiltersResult.Success(filters)
            } ?: FiltersResult.GenericError
        } else {
            FiltersResult.GenericError
        }
    }
}
