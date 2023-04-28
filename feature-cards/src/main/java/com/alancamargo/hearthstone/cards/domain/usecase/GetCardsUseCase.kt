package com.alancamargo.hearthstone.cards.domain.usecase

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.domain.FilterType
import kotlinx.coroutines.flow.Flow

internal interface GetCardsUseCase {

    operator fun invoke(type: FilterType, filter: String): Flow<CardListResult>
}
