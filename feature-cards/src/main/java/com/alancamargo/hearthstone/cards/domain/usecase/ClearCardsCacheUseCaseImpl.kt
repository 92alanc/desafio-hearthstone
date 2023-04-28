package com.alancamargo.hearthstone.cards.domain.usecase

import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ClearCardsCacheUseCaseImpl @Inject constructor(
    private val repository: CardsRepository
) : ClearCardsCacheUseCase {

    override fun invoke(): Flow<Unit> = repository.clearCache()
}
