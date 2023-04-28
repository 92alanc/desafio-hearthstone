package com.alancamargo.hearthstone.filters.domain.usecase

import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ClearFiltersCacheUseCaseImpl @Inject constructor(
    private val repository: FiltersRepository
) : ClearFiltersCacheUseCase {

    override fun invoke(): Flow<Unit> = repository.clearCache()
}
