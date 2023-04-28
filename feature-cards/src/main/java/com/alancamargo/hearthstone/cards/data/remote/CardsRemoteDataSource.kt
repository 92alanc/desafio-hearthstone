package com.alancamargo.hearthstone.cards.data.remote

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.domain.FilterType

internal interface CardsRemoteDataSource {

    suspend fun getCards(type: FilterType, filter: String): CardListResult
}
