package com.alancamargo.hearthstone.filters.domain.usecase

import kotlinx.coroutines.flow.Flow

internal interface ClearFiltersCacheUseCase {

    operator fun invoke(): Flow<Unit>
}
