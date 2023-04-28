package com.alancamargo.hearthstone.cards.domain.usecase

import kotlinx.coroutines.flow.Flow

internal interface ClearCardsCacheUseCase {

    operator fun invoke(): Flow<Unit>
}
