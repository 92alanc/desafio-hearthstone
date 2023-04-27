package com.alancamargo.hearthstone.cards.data.local

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.domain.Card
import com.alancamargo.hearthstone.core.domain.FilterType

internal interface CardsLocalDataSource {

    suspend fun getCards(type: FilterType, filter: String): CardListResult

    suspend fun saveCard(card: Card)

    suspend fun deleteCards()
}
