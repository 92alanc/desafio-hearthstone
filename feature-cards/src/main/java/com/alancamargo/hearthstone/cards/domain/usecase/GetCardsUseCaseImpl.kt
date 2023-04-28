package com.alancamargo.hearthstone.cards.domain.usecase

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import com.alancamargo.hearthstone.core.domain.FilterType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetCardsUseCaseImpl @Inject constructor(
    private val repository: CardsRepository
) : GetCardsUseCase {

    override fun invoke(type: FilterType, filter: String): Flow<CardListResult> {
        return repository.getCards(type, filter)
    }
}
