package com.alancamargo.hearthstone.filters.data.repository

import com.alancamargo.hearthstone.core.log.Logger
import com.alancamargo.hearthstone.filters.data.local.FiltersLocalDataSource
import com.alancamargo.hearthstone.filters.data.remote.FiltersRemoteDataSource
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

internal class FiltersRepositoryImpl @Inject constructor(
    private val remoteDataSource: FiltersRemoteDataSource,
    private val localDataSource: FiltersLocalDataSource,
    private val logger: Logger
) : FiltersRepository {

    override fun getFilters(): Flow<FiltersResult> = flow {
        val remoteResult = try {
            remoteDataSource.getFilters()
        } catch (e: IOException) {
            FiltersResult.NetworkError
        }

        if (remoteResult is FiltersResult.Success) {
            logger.debug("Saving filters...")
            localDataSource.saveFilters(remoteResult.filters)
            emit(remoteResult)
        } else {
            logger.debug("Fetching filters from database...")
            val localResult = localDataSource.getFilters()

            if (localResult is FiltersResult.Success) {
                emit(localResult)
            } else {
                logger.debug("No filters in database")
                emit(remoteResult)
            }
        }
    }

    override fun clearCache(): Flow<Unit> = flow {
        logger.debug("Clearing filters cache...")
        val task = localDataSource.deleteFilters()
        emit(task)
    }
}
