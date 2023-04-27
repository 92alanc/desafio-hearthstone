package com.alancamargo.hearthstone.filters.domain.usecase

import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetFiltersUseCaseImpl @Inject constructor(
    private val repository: FiltersRepository
) : GetFiltersUseCase {

    override fun invoke(): Flow<FiltersResult> = repository.getFilters()
}
