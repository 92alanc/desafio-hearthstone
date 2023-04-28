package com.alancamargo.hearthstone.cards.domain.repository

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.domain.FilterType
import kotlinx.coroutines.flow.Flow

internal interface CardsRepository {

    fun getCards(type: FilterType, filter: String): Flow<CardListResult>

    fun clearCache(): Flow<Unit>
}
