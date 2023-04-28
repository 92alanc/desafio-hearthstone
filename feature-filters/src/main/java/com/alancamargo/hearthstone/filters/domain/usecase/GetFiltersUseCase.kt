package com.alancamargo.hearthstone.filters.domain.usecase

import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import kotlinx.coroutines.flow.Flow

internal interface GetFiltersUseCase {

    operator fun invoke(): Flow<FiltersResult>
}
