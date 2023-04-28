package com.alancamargo.hearthstone.filters.data.local

import com.alancamargo.hearthstone.filters.data.db.FiltersDao
import com.alancamargo.hearthstone.filters.data.mapping.toDb
import com.alancamargo.hearthstone.filters.data.mapping.toDomain
import com.alancamargo.hearthstone.filters.domain.model.Filters
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import javax.inject.Inject

internal class FiltersLocalDataSourceImpl @Inject constructor(
    private val dao: FiltersDao
) : FiltersLocalDataSource {

    override suspend fun getFilters(): FiltersResult {
        val databaseResponse = dao.getFilters()

        return databaseResponse?.let {
            val filters = it.toDomain()
            FiltersResult.Success(filters)
        } ?: FiltersResult.GenericError
    }

    override suspend fun saveFilters(filters: Filters) {
        val dbFilters = filters.toDb()

        if (dao.getFiltersCount(dbFilters.id) > 0) {
            dao.updateFilters(dbFilters)
        } else {
            dao.insertFilters(dbFilters)
        }
    }

    override suspend fun deleteFilters() {
        dao.deleteFilters()
    }
}
